package litstorage.store.facade;

import java.util.List;

import litstorage.domain.DiscussionContent;

public interface DiscussionContentStore {
	boolean insertDiscussionContent(DiscussionContent discussionContent);
	List<DiscussionContent> selectDiscussionContentsByDiscussionPlaceId(String id);
	boolean deleteDiscussionContentById(String id);
}
