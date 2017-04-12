package litstorage.service.facade;

import java.util.List;

import litstorage.domain.InviteRequest;
import litstorage.domain.Member;


public interface MemberService {
	public boolean registerMember(Member member); 
	public Member findMemberById(String id);
	public List<Member> findMemberByName(String name);
	public boolean modifyMember(Member member);
	public boolean removeMember(String id);
	public boolean registerInviteRequest(InviteRequest inviteRequest);
	public boolean removeInviteRequest(InviteRequest inviteRequest);
	List<InviteRequest>findInviteRequestsBySenderId(String senderId);
	List<InviteRequest>findInviteRequestsByReceiverId(String receiverId);
}
