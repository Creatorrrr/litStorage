package litstorage.store.mapper;

import java.util.List;

import litstorage.domain.DiscussionPlace;

public interface DiscussionPlaceMapper {

	public boolean insertDiscussionPlace(DiscussionPlace discussionPlace);
	public DiscussionPlace selectDiscussionPlaceById(String id);
	public List<DiscussionPlace> selectDiscussionPlacesByLitStorageId(String litStorageId);
	public List<DiscussionPlace> selectDiscussionPlacesByName(String title);
	public List<DiscussionPlace> selectDiscussionPlacesByMemberId(String memberId);
	
}