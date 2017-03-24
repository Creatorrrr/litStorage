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
		int check;

		try {
			InviteRequestMapper mapper = session.getMapper(InviteRequestMapper.class);
			check = mapper.insertInviteRequest(inviteRequest);
			session.commit();
		} finally {
			session.close();
		}
		return check > 0;
	}

	@Override
	public boolean deleteInviteRequest(InviteRequest inviteRequest) {
		SqlSession session = factory.openSession();
		int check;
		HashMap<String, String> map = new HashMap<>();
		map.put("sender", inviteRequest.getSender().getId());
		map.put("receiver", inviteRequest.getReceiver().getId());
		map.put("litStorage", inviteRequest.getLitStorage().getId());

		try {
			check = session.delete("deleteInviteRequest", map);
			session.commit();
			
		} finally {
			session.close();
		}
		return check > 0;
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
