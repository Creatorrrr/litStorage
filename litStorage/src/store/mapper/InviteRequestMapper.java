package store.mapper;

import java.util.List;

import domain.InviteRequest;

public interface InviteRequestMapper {
	public int insertInviteRequest(InviteRequest inviteRequest);

	public int deleteInviteRequest(InviteRequest inviteRequest);

	public List<InviteRequest> selectInviteRequestBySenderId(String senderId);

	public List<InviteRequest> selectInviteRequestByReceiverId(String receiverId);
}
