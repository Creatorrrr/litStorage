package store.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.dircache.DirCache;

import domain.ChangeHistory;
import domain.Episode;
import store.facade.EpisodeStore;
import store.mapper.ChangeHistoryMapper;
import store.mapper.EpisodeMapper;
import utils.AutoCloser;
import utils.PathBuilder;

public class EpisodeStoreLogic implements EpisodeStore {
	private SqlSessionFactory factory;

	public EpisodeStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public boolean insertEpisode(Episode episode) {		
		SqlSession session = factory.openSession();	// open Session
		
		boolean check = false;
		
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.insertEpisode(episode);	// check insert to db
			
			if (check) {
				session.commit();
			} else {
				session.rollback();
			}
		} finally {
			AutoCloser.close(session);
		}
		
		return check;
	}
	
	@Override
	public String insertEpisodeToGit(Episode episode, String message) {
		Git git = null;
		
		File repoDir = new File(PathBuilder.buildLitStoragePath(episode.getLiterature().getLitStorage()));
		
		if(!repoDir.exists()) {
			throw new RuntimeException("Git repository path does not exists");
		}
		
		File episodeFile = new File(PathBuilder.buildEpisodeFilePath(episode));
		
		if(episodeFile.exists()) {
			throw new RuntimeException("Episode already exists in Git repository");
		}
		
		String episodeFileName = PathBuilder.buildEpisodeFileName(episode);
		
		try {
			createEpisodeFile(episode, episodeFile);	// create episode file "Path : {repoDir}/{episodeId}.txt
			
			git = Git.open(repoDir);	// open repository
			DirCache index = git.add().addFilepattern(episodeFileName).call();	// add to index
	        
	        if (index.getEntryCount() > 0) {
				git.commit().setMessage(message).call();
				return git.getRepository().resolve("HEAD^{tree}").getName();	// return HEAD's SHA-1 hash
	        } else {
				git.rm().addFilepattern(episodeFileName).setCached(true).call();	// if checkDb is false then remove file from index
				episodeFile.delete();
				return null;
			}
		} catch (IOException e) {
			throw new RuntimeException("Can not open Git Repository");
		} catch (NoFilepatternException e) {
			throw new RuntimeException("Git Repository does not tracking such file");
		} catch (GitAPIException e) {
			throw new RuntimeException("GitAPIException");
		} finally {
			AutoCloser.close(git);
		}
	}
	
	private void createEpisodeFile(Episode episode, File episodeFile) {
		BufferedWriter bWriter = null;
		BufferedReader bReader = null;
		File tempFile = null;
		
		try {
			tempFile = new File(episodeFile.getCanonicalPath().substring(0, episodeFile.getName().lastIndexOf(".")) + ".tmp");	// filename.tmp �깮�꽦
			
			bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), "UTF-8"));
			
			bWriter.write(episode.getTitle());
			bWriter.write("\r\n");
			bWriter.write(episode.getContent());
			bWriter.flush();
			
			AutoCloser.close(bWriter);
			
			bReader = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile), "UTF-8"));
			bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(episodeFile), "UTF-8"));
			
			char[] buffer = new char[256];
			int readCount = 0;

			while((readCount = bReader.read(buffer)) != -1) {
				bWriter.write(buffer, 0, readCount);
			}
			
			tempFile.delete();
		} catch (IOException e) {
			throw new RuntimeException("Can not create episode file 'Path : " + episodeFile.getPath() + "'");
		} finally {
			AutoCloser.close(bWriter, bReader);
		}
	}

	@Override
	public boolean updateEpisode(Episode episode) {
		SqlSession session = factory.openSession();	// open Session
		
		boolean check = false;
		
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.updateEpisode(episode);	// check insert to db

			if (check) {
				session.commit();
			} else {
				session.rollback();
			}
		} finally {
			AutoCloser.close(session);
		}
		
		return check;
	}
	
	@Override
	public String updateEpisodeToGit(Episode episode, String message) {
		Git git = null;
		
		File repoDir = new File(PathBuilder.buildLitStoragePath(episode.getLiterature().getLitStorage()));
		
		if(!repoDir.exists()) {
			throw new RuntimeException("Git repository path does not exists");
		}
		
		File episodeFile = new File(PathBuilder.buildEpisodeFilePath(episode));
		
		String episodeFileName = PathBuilder.buildEpisodeFileName(episode);
		
		try {
			createEpisodeFile(episode, episodeFile);	// create episode file "Path : {repoDir}/{episodeId}.txt
			
			git = Git.open(repoDir);	// open repository
			DirCache index = git.add().addFilepattern(episodeFileName).call();	// add to index
	        
	        if (index.getEntryCount() > 0) {
				git.commit().setMessage(message).call();
				return git.getRepository().resolve("HEAD^{tree}").getName();	// return HEAD's SHA-1 hash
	        } else {
				git.rm().addFilepattern(episodeFileName).setCached(true).call();	// if checkDb is false then remove file from index
				episodeFile.delete();
				return null;
			}
		} catch (IOException e) {
			throw new RuntimeException("Can not open Git Repository");
		} catch (NoFilepatternException e) {
			throw new RuntimeException("Git Repository does not tracking such file");
		} catch (GitAPIException e) {
			throw new RuntimeException("GitAPIException");
		} finally {
			AutoCloser.close(git);
		}
	}

	@Override
	public boolean deleteEpisode(String episodeId) {
		SqlSession session = factory.openSession();
		
		boolean check = false;
		
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.deleteEpisode(episodeId);
			
			if (check) {
				session.commit();
			} else {
				session.rollback();
			}
		} finally {
			session.close();
		}
		return check;
	}
	
	@Override
	public String deleteEpisodeFromGit(Episode episode, String message) {
		Git git = null;
		
		File repoDir = new File(PathBuilder.buildLitStoragePath(episode.getLiterature().getLitStorage()));
		
		if(!repoDir.exists()) {
			throw new RuntimeException("Git repository path does not exists");
		}
		
		File episodeFile = new File(PathBuilder.buildEpisodeFilePath(episode));
		
		String episodeFileName = PathBuilder.buildEpisodeFileName(episode);
		
		try {
			if(!episodeFile.delete()) {
				return null;
			}

			git = Git.open(repoDir);	// open repository
			git.rm().addFilepattern(episodeFileName).call();	// remove from index
			git.commit().setMessage(message).call();
			return git.getRepository().resolve("HEAD^{tree}").getName();	// return HEAD's SHA-1 hash
		} catch (IOException e) {
			throw new RuntimeException("Can not open Git Repository");
		} catch (NoFilepatternException e) {
			throw new RuntimeException("Git Repository does not tracking such file");
		} catch (GitAPIException e) {
			throw new RuntimeException("GitAPIException");
		} finally {
			AutoCloser.close(git);
		}
	}
	
	@Override
	public boolean deleteEpisodesByLiteratureId(String literatureId) {
		SqlSession session = factory.openSession();
		
		boolean check = false;
		
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.deleteEpisodesByLiteratureId(literatureId);
			
			if (check) {
				session.commit();
			} else {
				session.rollback();
			}
		} finally {
			session.close();
		}
		return check;
	}

	@Override
	public Episode selectEpisodeById(String id) {
		SqlSession session = factory.openSession();
		Episode episode = null;
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			episode = mapper.selectEpisodeById(id);
		} finally {
			session.close();
		}
		return episode;
	}

	@Override
	public List<Episode> selectEpisodesByLiteratureId(String literatureId) {
		SqlSession session = factory.openSession();
		List<Episode> list = new ArrayList<>();
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			list = mapper.selectEpisodesByLiteratureId(literatureId);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public boolean updateBound(String bound) {
		SqlSession session = factory.openSession();
		boolean check = false;
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.updateBound(bound);
			if (check) {
				session.commit();
			}
		} finally {
			session.close();
		}
		return check;
	}
	
	@Override
	public boolean insertChangeHistory(ChangeHistory history) {
		SqlSession session = factory.openSession();
		
		boolean check = false;
		
		try {
			ChangeHistoryMapper mapper = session.getMapper(ChangeHistoryMapper.class);
			
			if (check = mapper.insertChangeHistory(history) > 0) {
				session.commit();
			}
		} finally {
			session.close();
		}
		
		return check;
	}
}
