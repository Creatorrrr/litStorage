package store.mapper;

import java.util.List;

import domain.InviteRequest;

public interface InviteRequestMapper {
	public boolean insertInviteRequest(InviteRequest inviteRequest);

	public InviteRequest deleteInviteRequest(String senderId, String receiverId);

	public List<InviteRequest> selectInviteRequestBySenderId(String senderId);

	public List<InviteRequest> selectInviteRequestByReceiverId(String receiverId);
}
