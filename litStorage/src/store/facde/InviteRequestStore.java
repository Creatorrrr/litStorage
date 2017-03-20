package store.facde;

import java.util.List;

import domain.InviteRequest;

public interface InviteRequestStore {
	public boolean insertInviteRequest(InviteRequest inviteRequest);

	public InviteRequest deleteInviteRequest(String senderId, String receiverId);

	public List<InviteRequest> selectInviteRequestBySenderId(String senderId);

	public List<InviteRequest> selectInviteRequestByReceiverId(String receiverId);
}
