package service;

import java.util.List;

import domain.DiscussionContent;
import domain.DiscussionPlace;

public interface DiscussionPlaceService {

	public boolean registerDiscussionPlace(DiscussionPlace discussionPlace);
	public List<DiscussionPlace> findDiscussionPlacesByLitStorageId(String litStorageId);
	public boolean registerDiscussionContent(DiscussionContent discussionContent);
	public boolean removeDiscussionContent(String id);
	public List<DiscussionContent> findDiscussionContentsByDiscussionPlaceId(String discussionPlaceId);
	public DiscussionPlace findDiscussionPlaceById(String id);
	public List<DiscussionPlace> findDiscussionPlacesByName(String name);
	public List<DiscussionPlace> findDiscussionPlacesByMemberId(String memberId);
	

}
