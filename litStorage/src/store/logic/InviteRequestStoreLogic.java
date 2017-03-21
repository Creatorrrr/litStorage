package store.logic;

import java.util.HashMap;
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
	public boolean deleteInviteRequest(String sender, String receiver) {
		SqlSession session = factory.openSession();
		HashMap<String, String> map = new HashMap<>();
		map.put("sender", sender);
		map.put("receiver", receiver);

		try {
			session.delete("deleteInviteRequest", map);
			session.commit();
			
		} finally {
			session.close();
		}
		return false;
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
