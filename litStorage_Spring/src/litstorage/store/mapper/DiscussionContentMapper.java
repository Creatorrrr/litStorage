package litstorage.store.mapper;

import java.util.List;

import litstorage.domain.DiscussionContent;

public interface DiscussionContentMapper {
	int insertDiscussionContent(DiscussionContent discussionContent);
	List<DiscussionContent> selectDiscussionContentsByDiscussionPlaceId(String id);
	int deleteDiscussionContentById(String id);
}
