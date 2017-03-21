package store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Literature;
import store.facade.LiteratureStore;
import store.mapper.LiteratureMapper;

public class LiteratureStoreLogic implements LiteratureStore{
	
	private SqlSessionFactory factory;
	
	public LiteratureStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public boolean insertLiterature(Literature literature) {
		SqlSession session = factory.openSession();
		int check;
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			check = mapper.insertLiterature(literature);
			session.commit();
		} finally {
			session.close();
		}
		return check>0;
	}

	@Override
	public boolean deleteLiterature(String literatureId) {
		SqlSession session = factory.openSession();
		int check;
		try {
			LiteratureMapper mapper = session.getMapper(LiteratureMapper.class);
			check = mapper.deleteLiterature(literatureId);
			session.commit();
		} finally {
			session.close();
		}
		return check>0;
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
