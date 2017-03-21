package store.mapper;

import java.util.List;

import domain.DiscussionContent;

public interface DiscussionContentMapper {
	int insertDiscussionContent(DiscussionContent discussionContent);
	List<DiscussionContent> selectDiscussionContentsByDiscussionPlaceId(String id);
	int deleteDiscussionContentById(String id);
}
