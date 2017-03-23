package store.logic;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.StoredConfig;

import domain.LitStorage;
import store.facade.LitStorageStore;
import store.mapper.LitStorageMapper;
import utils.AutoCloser;
import utils.PathBuilder;

public class LitStorageStoreLogic implements LitStorageStore {
	private SqlSessionFactory factory;
	
	public LitStorageStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}
	
	@Override
	public boolean insertLitStorage(LitStorage litStorage) {
		SqlSession session = factory.openSession();
		boolean result = false;

		try {
			LitStorageMapper mapper = session.getMapper(LitStorageMapper.class);
			if(result = mapper.insertLitStorage(litStorage) > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} finally {
			session.close();
		}
		
		return result;
	}
	
	@Override
	public boolean insertLitStorageToGit(LitStorage litStorage) {
		Git git = null;
		
		File repoDir = new File(PathBuilder.buildLitStoragePath(litStorage));
		
		if(repoDir.exists()) {
			return false;
		}
		
		StoredConfig config = null;
		
		try {
			git = Git.init().setDirectory(repoDir).call();	// open repository
			
			config = git.getRepository().getConfig();
			config.setString("user", null, "name", litStorage.getCreator().getId());
			config.setString("user", null, "email", litStorage.getCreator().getEmail());			
			config.save();
			
			return true;
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
	public boolean deleteLitStorage(String litStorageId) {
		SqlSession session = factory.openSession();
		boolean result = false;

		try {
			LitStorageMapper mapper = session.getMapper(LitStorageMapper.class);
			if(result = mapper.deleteLitStorage(litStorageId) > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} finally {
			session.close();
		}
		
		return result;
	}
	
	@Override
	public boolean deleteLitStorageFromGit(String path) {
		File file = new File(path);
		
		if(!file.exists()) {
			return false;
		}
		
		File[] tempFile = file.listFiles();
		
		if(tempFile.length >0){
			for (int i = 0; i < tempFile.length; i++){ 
				if(tempFile[i].isFile()){ 
					tempFile[i].delete(); 
				} else {
					deleteLitStorageFromGit(tempFile[i].getPath()); 
				} 
				tempFile[i].delete(); 
			}
			file.delete(); 
		}
		
		return true;
	}

	@Override
	public LitStorage selectLitStorageById(String id) {
		SqlSession session = factory.openSession();
		LitStorage litStorage = null;

		try {
			LitStorageMapper mapper = session.getMapper(LitStorageMapper.class);
			litStorage = mapper.selectLitStorageById(id);
		} finally {
			session.close();
		}
		
		return litStorage;
	}

	@Override
	public List<LitStorage> selectLitStoragesByMemberId(String id) {
		SqlSession session = factory.openSession();
		List<LitStorage> litStorageList = null;

		try {
			LitStorageMapper mapper = session.getMapper(LitStorageMapper.class);
			litStorageList = mapper.selectLitStoragesByMemberId(id);
		} finally {
			session.close();
		}
		
		return litStorageList;
	}

	@Override
	public List<LitStorage> selectLitStoragesByName(String name) {
		SqlSession session = factory.openSession();
		List<LitStorage> litStorageList = null;

		try {
			LitStorageMapper mapper = session.getMapper(LitStorageMapper.class);
			litStorageList = mapper.selectLitStoragesByName(name);
		} finally {
			session.close();
		}
		
		return litStorageList;
	}

	@Override
	public List<LitStorage> selectAll() {
		SqlSession session = factory.openSession();
		List<LitStorage> litStorageList = null;

		try {
			LitStorageMapper mapper = session.getMapper(LitStorageMapper.class);
			litStorageList = mapper.selectAll();
		} finally {
			session.close();
		}
		
		return litStorageList;
	}

}
