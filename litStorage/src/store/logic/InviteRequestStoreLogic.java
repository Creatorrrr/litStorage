package store.logic;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.InviteRequest;
import store.facade.InviteRequestStore;
import store.mapper.InviteRequestMapper;


public class InviteRequestStoreLogic implements InviteRequestStore {

	private SqlSessionFactory factory;

	public InviteRequestStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	@Override
	public boolean insertInviteRequest(InviteRequest inviteRequest) {
		SqlSession session = factory.openSession();

		try {
			InviteRequestMapper mapper = session.getMapper(InviteRequestMapper.class);
			mapper.insertInviteRequest(inviteRequest);
			session.commit();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public InviteRequest deleteInviteRequest(String senderId, String receiverId) {
		SqlSession session = factory.openSession();
		InviteRequest inviteRequest = null;

		try {
			InviteRequestMapper mapper = session.getMapper(InviteRequestMapper.class);
			inviteRequest = mapper.deleteInviteRequest(senderId, receiverId);
			session.commit();
		} finally {
			session.close();
		}
		return inviteRequest;
	}

	@Override
	public List<InviteRequest> selectInviteRequestBySenderId(String senderId) {
		SqlSession session = factory.openSession();
		List<InviteRequest> list = null;

		try {
			InviteRequestMapper mapper = session.getMapper(InviteRequestMapper.class);
			list = mapper.selectInviteRequestBySenderId(senderId);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<InviteRequest> selectInviteRequestByReceiverId(String receiverId) {
		SqlSession session = factory.openSession();
		List<InviteRequest> list = null;
		
		try {
			InviteRequestMapper mapper = session.getMapper(InviteRequestMapper.class);
			list = mapper.selectInviteRequestByReceiverId(receiverId);
			session.commit();
		} finally {
			session.close();
		}
		return list;
	}

}
