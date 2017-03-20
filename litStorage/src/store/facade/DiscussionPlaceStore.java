package store.facade;

import java.util.List;

import domain.DiscussionPlace;

public interface DiscussionPlaceStore {

	public boolean insertDiscussionPlace(DiscussionPlace discussionPlace);
	public DiscussionPlace selectDiscussionPlaceById(String id);
	public List<DiscussionPlace> selectDiscussionPlaceByLitStorageId(String listStorageId);
	public List<DiscussionPlace> selectDiscussionPlaceByName(String name);
	public List<DiscussionPlace> selectDiscussionPlaceByMemberId(String memberId);
	
}
