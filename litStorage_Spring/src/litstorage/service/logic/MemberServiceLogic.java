package litstorage.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import litstorage.domain.InviteRequest;
import litstorage.domain.Member;
import litstorage.service.facade.MemberService;
import litstorage.store.facade.InviteRequestStore;
import litstorage.store.facade.MemberStore;

@Service
public class MemberServiceLogic implements MemberService{

	@Autowired
	private MemberStore mStore;
	@Autowired
	private InviteRequestStore iStore;
	
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
	public boolean removeInviteRequest(InviteRequest inviteRequest) {
		return iStore.deleteInviteRequest(inviteRequest);
	}

	@Override
	public List<InviteRequest> findInviteRequestsBySenderId(String senderId) {
		return iStore.selectInviteRequestBySenderId(senderId);
	}

	@Override
	public List<InviteRequest> findInviteRequestsByReceiverId(String receiverId) {
		return iStore.selectInviteRequestByReceiverId(receiverId);
	}

	@Override
	public List<Member> findMemberByName(String name) {
		return mStore.selectMembersByName(name);
	}

}
