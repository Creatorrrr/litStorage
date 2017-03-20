package store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Member;
import domain.MemberLitStorage;
import store.facade.MemberLitStorageStore;
import store.mapper.MemberLitStorageMapper;

public class MemberLitStorageStoreLogic implements MemberLitStorageStore {
	
	private SqlSessionFactory factory;
	
	public MemberLitStorageStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public boolean insertMemberLitStorage(MemberLitStorage memberLitStorage) {
		SqlSession session = factory.openSession();
		boolean result = false;

		try {
			MemberLitStorageMapper mapper = session.getMapper(MemberLitStorageMapper.class);
			result = mapper.insertMemberLitStorage(memberLitStorage);
			session.commit();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public List<MemberLitStorage> selectMemberLitStoragesByLitStorageId(String id) {
		SqlSession session = factory.openSession();
		List<MemberLitStorage> resultList = null;

		try {
			MemberLitStorageMapper mapper = session.getMapper(MemberLitStorageMapper.class);
			resultList = mapper.selectMemberLitStoragesByLitStorageId(id);
		} finally {
			session.close();
		}
		return resultList;
	}

	@Override
	public List<MemberLitStorage> selectMemberLitStoragesByMemberId(String id) {
		SqlSession session = factory.openSession();
		List<MemberLitStorage> resultList = null;

		try {
			MemberLitStorageMapper mapper = session.getMapper(MemberLitStorageMapper.class);
			resultList = mapper.selectMemberLitStoragesByMemberId(id);
		} finally {
			session.close();
		}
		return resultList;
	}

	@Override
	public List<Member> selectMembersByLitStorageId(String id) {
		SqlSession session = factory.openSession();
		List<Member> resultList = null;

		try {
			MemberLitStorageMapper mapper = session.getMapper(MemberLitStorageMapper.class);
			resultList = mapper.selectMembersByLitStorageId(id);
		} finally {
			session.close();
		}
		return resultList;
	}	
}
