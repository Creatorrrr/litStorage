package service.logic;

import java.util.List;

import domain.InviteRequest;
import domain.Member;
import service.facade.MemberService;
import store.facade.InviteRequestStore;
import store.facade.MemberStore;
import store.logic.InviteRequestStoreLogic;
import store.logic.MemberStoreLogic;

public class MemberServiceLogic implements MemberService{

	private MemberStore MStore;
	private InviteRequestStore IStore;
	
	public MemberServiceLogic() {
		MStore=new MemberStoreLogic();
		IStore=new InviteRequestStoreLogic();
	}
	
	
	@Override
	public boolean registerMember(Member member) {
		return MStore.insertMember(member);
	}

	@Override
	public Member findMemberById(String id) {
		return MStore.selectMemberById(id);
	}

	@Override
	public boolean modifyMember(Member member) {
		return MStore.updateMember(member);
	}

	@Override
	public boolean removeMember(String id) {
		return MStore.deleteMember(id);
	}

	@Override
	public boolean registerInviteRequest(InviteRequest inviteRequest) {
		return IStore.insertInviteRequest(inviteRequest);
	}

	@Override
	public boolean removeInviteRequest(String senderId, String receiverId) {
		return IStore.deleteInviteRequest(senderId, receiverId);
	}

	@Override
	public List<InviteRequest> findInviteRequestsBySenderId(String senderId) {
		return IStore.selectInviteRequestBySenderId(senderId);
	}

	@Override
	public List<InviteRequest> findInviteRequestsByReceiverId(String receiverId) {
		return IStore.selectInviteRequestByReceiverId(receiverId);
	}

}
