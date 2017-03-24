package store.logic;

import java.io.File;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Literature;
import store.facade.LiteratureStore;
import store.mapper.LiteratureMapper;
import utils.PathBuilder;

public class LiteratureStoreLogic implements LiteratureStore{
	
	private SqlSessionFactory factory;
	
	public LiteratureStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public boolean insertLiterature(Literature literature) {
		SqlSession session = factory.openSession();
		
		boolean check = false;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			
			if(check = mapper.insertLiterature(literature) > 0) {
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
	public boolean insertLiteratureToGit(Literature literature) {
		File literatureDir = new File(PathBuilder.buildLiteraturePath(literature));
		
		if(literatureDir.exists()) {
			return false;
		}
		
		literatureDir.mkdir();
		
		return true;
	}

	@Override
	public boolean deleteLiterature(String literatureId) {
		SqlSession session = factory.openSession();
		
		boolean check = false;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			
			if(check = mapper.deleteLiterature(literatureId) > 0) {
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
	public boolean deleteLiteratureFromGit(String path) {
		File file = new File(path);
		
		if(!file.exists()) {
			return false;
		}
		
		File[] tempFile = file.listFiles();
		
		if(tempFile.length > 0){
			for (int i = 0; i < tempFile.length; i++){ 
				if(tempFile[i].isFile()){ 
					tempFile[i].delete(); 
				} else {
					deleteLiteratureFromGit(tempFile[i].getPath()); 
				} 
				tempFile[i].delete(); 
			}
			file.delete(); 
		}
		
		return true;
	}

	@Override
	public Literature selectLiteraturesById(String literatureId) {
		SqlSession session =factory.openSession();
		Literature literature = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			literature = mapper.selectLiteraturesById(literatureId);
			session.commit();
		} finally {
			session.close();
		}
		return literature;
	}

	@Override
	public List<Literature> selectLiteraturesByLitStorageId(String litstorageId) {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteraturesByLitStorageId(litstorageId);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Literature> selectLiteraturesByName(String name) {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteraturesByName(name);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Literature> selectLiteraturesByGenreOrderByHits() {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteraturesByGenreOrderByHits();
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Literature> selectLiteratureByGenreOrderById(String Id) {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteratureByGenreOrderById(Id);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Literature> selectLiteraturesByMemberId(String memberId) {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteraturesByMemberId(memberId);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

}
