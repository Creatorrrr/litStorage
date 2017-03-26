package store.facade;

import java.util.List;

import domain.DiscussionContent;

public interface DiscussionContentStore {
	boolean insertDiscussionContent(DiscussionContent discussionContent);
	List<DiscussionContent> selectDiscussionContentsByDiscussionPlaceId(String id);
	boolean deleteDiscussionContentById(String id);
}
