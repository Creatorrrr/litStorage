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
		boolean check = false;

		try {
			InviteRequestMapper mapper = session.getMapper(InviteRequestMapper.class);
			if(check = mapper.insertInviteRequest(inviteRequest) > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} finally {
			session.close();
		}
		return check;
	}

	@Override
	public boolean deleteInviteRequest(InviteRequest inviteRequest) {
		SqlSession session = factory.openSession();
		boolean check = false;
		HashMap<String, String> map = new HashMap<>();
		map.put("sender", inviteRequest.getSender().getId());
		map.put("receiver", inviteRequest.getReceiver().getId());
		map.put("litStorage", inviteRequest.getLitStorage().getId());

		try {
			if(check = session.delete("deleteInviteRequest", map) > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} finally {
			session.close();
		}
		return check;
	}

	@Override
	public List<InviteRequest> selectInviteRequestBySenderId(String senderId) {
		SqlSession session = factory.openSession();
		List<InviteRequest> list = null;

		try {
			InviteRequestMapper mapper = session.getMapper(InviteRequestMapper.class);
			list = mapper.selectInviteRequestBySenderId(senderId);
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
		} finally {
			session.close();
		}
		return list;
	}

}
