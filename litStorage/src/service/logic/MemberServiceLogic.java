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

	private MemberStore mStore;
	private InviteRequestStore iStore;
	
	public MemberServiceLogic() {
		mStore=new MemberStoreLogic();
		iStore=new InviteRequestStoreLogic();
	}
	
	
	@Override
	public boolean registerMember(Member member) {
		return mStore.insertMember(member);
	}

	@Override
	public Member findMemberById(String id) {
		return mStore.selectMemberById(id);
	}

	@Override
	public boolean modifyMember(Member member) {
		return mStore.updateMember(member);
	}

	@Override
	public boolean removeMember(String id) {
		return mStore.deleteMember(id);
	}

	@Override
	public boolean registerInviteRequest(InviteRequest inviteRequest) {
		return iStore.insertInviteRequest(inviteRequest);
	}

	@Override
	public boolean removeInviteRequest(String senderId, String receiverId) {
		return iStore.deleteInviteRequest(senderId, receiverId);
	}

	@Override
	public List<InviteRequest> findInviteRequestsBySenderId(String senderId) {
		return iStore.selectInviteRequestBySenderId(senderId);
	}

	@Override
	public List<InviteRequest> findInviteRequestsByReceiverId(String receiverId) {
		return iStore.selectInviteRequestByReceiverId(receiverId);
	}

}
