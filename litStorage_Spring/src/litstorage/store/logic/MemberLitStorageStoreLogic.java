package litstorage.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import litstorage.domain.Member;
import litstorage.domain.MemberLitStorage;
import litstorage.store.facade.MemberLitStorageStore;
import litstorage.store.mapper.MemberLitStorageMapper;

@Repository
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
			if(result = mapper.insertMemberLitStorage(memberLitStorage) > 0) {
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
	public boolean deleteMemberLitStorageByLitStorageId(String id) {
		SqlSession session = factory.openSession();
		boolean result = false;

		try {
			MemberLitStorageMapper mapper = session.getMapper(MemberLitStorageMapper.class);
			if(result = mapper.deleteMemberLitStorageByLitStorageId(id) > 0) {
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
