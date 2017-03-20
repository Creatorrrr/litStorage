package store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Member;
import store.mapper.MemberMapper;
import store.facade.MemberStore;

public class MemberStoreLogic implements MemberStore{

	private SqlSessionFactory factory;
	
	public MemberStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}
	
	@Override
	public boolean insertMember(Member member) {
		
		SqlSession session = factory.openSession();
		boolean result = false;
		
		try{
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			if(result = mapper.insertMember(member) > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		}finally{
			session.close();
		}
		return result;
	}

	@Override
	public Member selectMemberById(String id) {
		SqlSession session = factory.openSession();
		Member member = null;
		try{
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			member = mapper.selectMemberById(id);
		}finally{
			session.close();
		}
		return member;
	}
		

	@Override
	public boolean updateMember(Member member) {
		SqlSession session = factory.openSession();
		boolean result = false;
		
		try{
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			if (result=mapper.updateMember(member) > 0){
				session.commit();
			}else{
				session.rollback();
			}
		}finally{
			session.close();
		}
		return result;
	}

	@Override
	public boolean deleteMember(String id) {

		SqlSession session = factory.openSession();
		boolean result;
		
		try{
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			if (result = mapper.deleteMember(id) > 0){
				session.commit();
			}else{
				session.rollback();
			}
		}finally{
			session.close();
		}
		return result;
	}

	@Override
	public List<Member> selectMembersByName(String name) {
		SqlSession session = factory.openSession();
		List<Member> list = null;
		
		try{
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			list=mapper.selectMembersByName(name);
		}finally{
			session.close();
		}
		return list;
	}

}
