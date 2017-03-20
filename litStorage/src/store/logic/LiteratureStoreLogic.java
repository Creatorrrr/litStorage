package store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Literature;
import store.facde.LiteratureStore;
import store.mapper.LiteratureMapper;

public class LiteratureStoreLogic implements LiteratureStore{
	
	private SqlSessionFactory factory;
	
	public LiteratureStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public boolean insertLiterature(Literature literature) {
		SqlSession session = factory.openSession();
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			mapper.insertLiterature(literature);
			session.commit();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteLiterature(String literatureId) {
		SqlSession session = factory.openSession();
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			mapper.deleteLiterature(literatureId);
			session.commit();
		} finally {
			session.close();
		}
		return false;
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
	public List<Literature> selectLiteraturesByGenreOrderByHits(String hits) {
		SqlSession session = factory.openSession();
		List<Literature> list = null;
		
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			list = mapper.selectLiteraturesByGenreOrderByHits(hits);
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
