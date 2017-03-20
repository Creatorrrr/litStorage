package store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Member;
import store.Mapper.MemberMapper;
import store.facade.MemberStore;

public class MemberStoreLogic implements MemberStore{

	private SqlSessionFactory factory;
	
	public MemberStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}
	
	@Override
	public boolean insertMember(Member member) {
		
		SqlSession session = factory.openSession();
		boolean result;
		
		try{
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			result=mapper.insertMember(member);
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
		boolean result;
		
		try{
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			result=mapper.updateMember(member);
			if (result != false){
				session.commit();
				}else{
					session.rollback();
				}
				return result;
		}finally{
			session.close();
		}
	
	}

	@Override
	public boolean deleteMember(String id) {

		SqlSession session = factory.openSession();
		boolean result;
		
		try{
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			result = mapper.deleteMember(id);
			if (result != false){
				session.commit();
			}else{
				session.rollback();
			}
			return result;
		}finally{
			session.close();
		}
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
