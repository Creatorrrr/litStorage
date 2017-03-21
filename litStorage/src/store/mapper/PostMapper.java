package store.mapper;

import java.util.List;

import domain.Post;

public interface PostMapper {

	int insertPost(Post post);
	int updatePost(Post post);
	int deletePost(String id);
	List<Post>selectPostsByBoardId(String boardId);
	Post selectPostById(String id);
	List<Post>selectPostsByContent(String content);
	List<Post>selectPostsByHashtag(String hashtag);
}
