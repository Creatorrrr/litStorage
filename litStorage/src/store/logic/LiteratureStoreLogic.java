package store.logic;

import java.io.File;
import java.util.HashMap;
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
		} else {
			file.delete();
		}
		
		return true;
	}
	
	public boolean updateLiteratureHitByLiteratureId(String literatureId) {
		SqlSession session = factory.openSession();
		boolean check = false;
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			
			if(check = mapper.updateLiteratureHitByLiteratureId(literatureId) > 0) {
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
	public Literature selectLiteraturesById(String literatureId) {
		SqlSession session =factory.openSession();
		Literature literature = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			literature = mapper.selectLiteraturesById(literatureId);
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
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Literature> selectLiteraturesByGenreOrderByHits(String genre) {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteraturesByGenreOrderByHits(genre);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Literature> selectLiteraturesByGenreOrderByHitsForMain(String genre) {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteraturesByGenreOrderByHitsForMain(genre);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Literature> selectLiteraturesByGenreOrderById(String genre) {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteraturesByGenreOrderById(genre);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Literature> selectLiteraturesByGenreOrderByIdForMain(String genre) {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteraturesByGenreOrderByIdForMain(genre);
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
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Literature> selectLiteraturesByGenreWithPage(String genre, String begin, String end) {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		HashMap<String, String> map = new HashMap<>();
		map.put("genre", genre);
		map.put("begin", begin);
		map.put("end", end);
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteraturesByGenreWithPage(map);
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public String selectRowsByGenre(String genre) {
		SqlSession session = factory.openSession();
		String rows = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			rows = mapper.selectRowsByGenre(genre);
		} finally {
			session.close();
		}
		return rows;
	}

}
