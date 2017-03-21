package service.facade;

import java.util.List;

import domain.InviteRequest;
import domain.Member;

public interface MemberService {
	public boolean registerMember(Member member); 
	public Member findMemberById(String id);
	public boolean modifyMember(Member member);
	public boolean removeMember(String id);
	public boolean registerInviteRequest(InviteRequest inviteRequest);
	public boolean removeInviteRequest(String senderId,String receiverId);
	List<InviteRequest>findInviteRequestsBySenderId(String senderId);
	List<InviteRequest>findInviteRequestsByReceiverId(String receiverId);
}
