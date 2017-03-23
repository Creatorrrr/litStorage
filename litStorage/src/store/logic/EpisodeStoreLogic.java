package store.logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
	
	private static String rootPath = "C:/Users/kosta/litStorageGit";	// need to make constants package

	private SqlSessionFactory factory;

	public EpisodeStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public boolean insertEpisode(Episode episode) {		
		SqlSession session = factory.openSession();	// open Session
		
		String literatureId = episode.getLiterature().getId();
		String episodeId = episode.getId();
		
		String title = episode.getTitle().substring(0);	// copy episode title
		String content = episode.getContent().substring(0);	// copy episode content
		
		String episodeFileName = literatureId + "/" + episodeId + ".txt";	// episode file name
		episode.setContent(buildEpisodeFilePath(episode, episodeFileName));	// ************************* 전체 경로가 아닌 episodeFileName
		
		boolean checkDb = false;
		boolean checkGit = false;
		
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			checkDb = mapper.insertEpisode(episode);	// check insert to db
			
			checkGit = insertEpisodeToGit(episode, title, content);	// insert in to git repository
			
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
	
	public boolean insertEpisodeToGit(Episode episode, String title, String content) {
		Git git = null;
		
		String literatureId = episode.getLiterature().getId();
		String episodeId = episode.getId();
		
		File repoDir = new File(buildLitStorageRepoPath(episode));
		
		if(!repoDir.exists()) {
			throw new RuntimeException("Git repository path does not exists");
		}
		
		String episodeFileName = literatureId + "/" + episodeId + ".txt";	// episode file name
		
		File episodeFile = new File(buildEpisodeFilePath(episode, episodeFileName));
		
		if(episodeFile.exists()) {
			throw new RuntimeException("Episode already exists in Git repository");
		}
		
		try {
			createEpisodeFile(title, content, episodeFile);	// create episode file "Path : {repoDir}/{episodeId}.txt
			
			git = Git.open(repoDir);	// open repository
			DirCache index = git.add().addFilepattern(episodeFileName).call();	// add to index
	        
	        if (index.getEntryCount() > 0) {
				git.commit().setMessage("ID : " + episode.getWriter().getId() + " created episode file '" + episode.getId() + ".txt'").call();
				return true;
	        } else {
				git.rm().addFilepattern(episodeFileName).call();	// if checkDb is false then remove file from index
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
	
	private String buildLitStorageRepoPath(Episode episode) {
		StringBuilder strBuilder = new StringBuilder();
		
		String litStorageId = episode.getLiterature().getLitStorage().getId();
		
		strBuilder.append(rootPath);
		strBuilder.append("/");
		strBuilder.append(litStorageId);	// litStorage ID
		
		return strBuilder.toString();
	}
	
	private String buildEpisodeFilePath(Episode episode, String episodeFileName) {
		StringBuilder strBuilder = new StringBuilder();
		
		String litStorageId = episode.getLiterature().getLitStorage().getId();
		
		strBuilder.append(rootPath);
		strBuilder.append("/");
		strBuilder.append(litStorageId);	// litStorage ID
		strBuilder.append("/");
		strBuilder.append(episodeFileName);	// episode file name
		
		return strBuilder.toString();
	}
	
	private void createEpisodeFile(String title, String content, File episodeFile) {
		BufferedWriter bWriter = null;
		
		try {
			bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(episodeFile), "UTF-8"));

			bWriter.write(title);
			bWriter.write(content);
			bWriter.flush();
		} catch (IOException e) {
			throw new RuntimeException("Can not create episode file 'Path : " + episodeFile.getPath() + "'");
		} finally {
			JdbcUtils.close(bWriter);
		}
	}

	@Override
	public boolean updateEpisode(Episode episode) {
		SqlSession session = factory.openSession();
		boolean check = false;
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.updateEpisode(episode);
			if (check) {
				session.commit();
			}
		} finally {
			session.close();
		}
		return check;
	}

	@Override
	public boolean deleteEpisode(Episode episode) {
		SqlSession session = factory.openSession();
		boolean check = false;
		try {
			EpisodeMapper mapper = session.getMapper(EpisodeMapper.class);
			check = mapper.deleteEpisode(episode);
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

}
