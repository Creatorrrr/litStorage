package litstorage.service.facade;

import java.util.List;

import litstorage.domain.DiscussionContent;
import litstorage.domain.DiscussionPlace;

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
