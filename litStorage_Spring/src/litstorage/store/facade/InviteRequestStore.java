package litstorage.store.facade;

import java.util.List;

import litstorage.domain.InviteRequest;

public interface InviteRequestStore {
	public boolean insertInviteRequest(InviteRequest inviteRequest);
	public boolean deleteInviteRequest(InviteRequest inviteRequest);
	public List<InviteRequest> selectInviteRequestBySenderId(String senderId);
	public List<InviteRequest> selectInviteRequestByReceiverId(String receiverId);
}
