package store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.LitStorage;
import store.facade.LitStorageStore;
import store.mapper.LitStorageMapper;

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
	public boolean deleteLitStorage(String id) {
		SqlSession session = factory.openSession();
		boolean result = false;

		try {
			LitStorageMapper mapper = session.getMapper(LitStorageMapper.class);
			if(result = mapper.deleteLitStorage(id) > 0) {
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
