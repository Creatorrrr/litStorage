package store.mapper;

import java.util.List;

import domain.DiscussionContent;

public interface DiscussionContentMapper {
	boolean insertDiscussionContent(DiscussionContent discussionContent);
	List<DiscussionContent> selectDiscussionContentsByDiscussionPlaceId(String id);
	boolean deleteDiscussionContent(String id);
}
