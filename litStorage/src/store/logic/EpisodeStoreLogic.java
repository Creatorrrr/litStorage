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

import domain.Episode;
import store.facade.EpisodeStore;
import store.mapper.EpisodeMapper;
import store.utils.JdbcUtils;

public class EpisodeStoreLogic implements EpisodeStore {
	
	private static final String ROOT_PATH = "C:/Users/kosta/litStorageGit";	// need to make constants package

	private SqlSessionFactory factory;

	public EpisodeStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public boolean insertEpisode(Episode episode) {		
		SqlSession session = factory.openSession();	// open Session
		
		boolean checkDb = false;
		boolean checkGit = false;
		
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			checkDb = mapper.insertEpisode(episode);	// check insert to db
			
			checkGit = insertEpisodeToGit(episode);	// insert in to git repository
			
			if (checkDb && checkGit) {
				session.commit();
			} else {
				session.rollback();
			}
		} finally {
			JdbcUtils.close(session);
		}
		
		return checkDb && checkGit;
	}
	
	public boolean insertEpisodeToGit(Episode episode) {
		Git git = null;
		
		File repoDir = new File(buildLitStorageRepoPath(episode));
		
		if(!repoDir.exists()) {
			throw new RuntimeException("Git repository path does not exists");
		}
		
		File episodeFile = new File(buildEpisodeFilePath(episode));
		
		if(episodeFile.exists()) {
			throw new RuntimeException("Episode already exists in Git repository");
		}
		
		String episodeFileName = buildEpisodeFileName(episode);
		
		try {
			createEpisodeFile(episode, episodeFile);	// create episode file "Path : {repoDir}/{episodeId}.txt
			
			git = Git.open(repoDir);	// open repository
			DirCache index = git.add().addFilepattern(episodeFileName).call();	// add to index
	        
	        if (index.getEntryCount() > 0) {
				git.commit().setMessage("ID : " + episode.getWriter().getId() + " modified episode file '" + episode.getId() + ".txt'").call();
				return true;
	        } else {
				git.rm().addFilepattern(episodeFileName).setCached(true).call();	// if checkDb is false then remove file from index
				episodeFile.delete();
				return false;
			}
		} catch (IOException e) {
			throw new RuntimeException("Can not open Git Repository");
		} catch (NoFilepatternException e) {
			throw new RuntimeException("Git Repository does not tracking such file");
		} catch (GitAPIException e) {
			throw new RuntimeException("GitAPIException");
		} finally {
			JdbcUtils.close(git);
		}
	}
	
	private void createEpisodeFile(Episode episode, File episodeFile) {
		BufferedWriter bWriter = null;
		BufferedReader bReader = null;
		File tempFile = null;
		
		try {
			tempFile = new File(episodeFile.getName().substring(0, episodeFile.getName().lastIndexOf(".")) + ".tmp");	// filename.tmp 생성
			
			bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), "UTF-8"));
			
			bWriter.write(episode.getTitle());
			bWriter.write(episode.getContent());
			bWriter.flush();
			
			JdbcUtils.close(bWriter);
			
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
			JdbcUtils.close(bWriter, bReader);
		}
	}

	@Override
	public boolean updateEpisode(Episode episode) {
		SqlSession session = factory.openSession();	// open Session
		
		String content = episode.getContent().substring(0);	// copy episode content
		
		String episodeFileName = buildEpisodeFileName(episode);	// episode file name
		episode.setContent(episodeFileName);
		
		boolean checkDb = false;
		boolean checkGit = false;
		
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			checkDb = mapper.updateEpisode(episode);	// check insert to db

			checkGit = updateEpisodeToGit(episode, content);	// insert in to git repository

			if (checkDb && checkGit) {
				session.commit();
			} else {
				session.rollback();
			}
		} finally {
			JdbcUtils.close(session);
		}
		
		return checkDb && checkGit;
	}
	
	public boolean updateEpisodeToGit(Episode episode, String content) {
		Git git = null;
		
		File repoDir = new File(buildLitStorageRepoPath(episode));
		
		if(!repoDir.exists()) {
			throw new RuntimeException("Git repository path does not exists");
		}
		
		File episodeFile = new File(buildEpisodeFilePath(episode));
		
		String episodeFileName = buildEpisodeFileName(episode);
		
		try {
			createEpisodeFile(episode, episodeFile);	// create episode file "Path : {repoDir}/{episodeId}.txt
			
			git = Git.open(repoDir);	// open repository
			DirCache index = git.add().addFilepattern(episodeFileName).call();	// add to index
	        
	        if (index.getEntryCount() > 0) {
				git.commit().setMessage("ID : " + episode.getWriter().getId() + " created episode file '" + episode.getId() + ".txt'").call();
				return true;
	        } else {
				git.rm().addFilepattern(episodeFileName).setCached(true).call();	// if checkDb is false then remove file from index
				episodeFile.delete();
				return false;
			}
		} catch (IOException e) {
			throw new RuntimeException("Can not open Git Repository");
		} catch (NoFilepatternException e) {
			throw new RuntimeException("Git Repository does not tracking such file");
		} catch (GitAPIException e) {
			throw new RuntimeException("GitAPIException");
		} finally {
			JdbcUtils.close(git);
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
	
	private String buildLitStorageRepoPath(Episode episode) {
		StringBuilder strBuilder = new StringBuilder();
		
		String litStorageId = episode.getLiterature().getLitStorage().getId();
		
		strBuilder.append(ROOT_PATH);
		strBuilder.append("/");
		strBuilder.append(litStorageId);	// litStorage ID
		
		return strBuilder.toString();
	}
	
	private String buildEpisodeFilePath(Episode episode) {
		StringBuilder strBuilder = new StringBuilder();
		
		String litStorageId = episode.getLiterature().getLitStorage().getId();
		String literatureId = episode.getLiterature().getId();
		String episodeId = episode.getId();
		
		strBuilder.append(ROOT_PATH);
		strBuilder.append("/");
		strBuilder.append(litStorageId);	// litStorage ID
		strBuilder.append("/");
		strBuilder.append(literatureId);	// literatureId ID
		strBuilder.append("/");
		strBuilder.append(episodeId + ".txt");	// episode file name
		
		return strBuilder.toString();
	}
	
	private String buildEpisodeFileName(Episode episode) {
		StringBuilder strBuilder = new StringBuilder();
		
		String literatureId = episode.getLiterature().getId();
		String episodeId = episode.getId();
		
		strBuilder.append(literatureId);	// literatureId ID
		strBuilder.append("/");
		strBuilder.append(episodeId + ".txt");	// episode file name
		
		return strBuilder.toString();
	}

}
