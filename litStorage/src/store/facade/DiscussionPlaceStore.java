package store.facade;

import java.util.List;

import domain.DiscussionPlace;

public interface DiscussionPlaceStore {
	public boolean insertDiscussionPlace(DiscussionPlace discussionPlace);
	public DiscussionPlace selectDiscussionPlaceById(String id);
	public List<DiscussionPlace> selectDiscussionPlacesByLitStorageId(String litStorageId);
	public List<DiscussionPlace> selectDiscussionPlacesByName(String title);
	public List<DiscussionPlace> selectDiscussionPlacesByMemberId(String memberId);
}
