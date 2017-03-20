package store.Mapper;

import java.util.List;

import domain.Post;

public interface PostMapper {

	boolean insertPost(Post post);
	boolean updatePost(Post post);
	boolean deletePost(String id);
	List<Post>selectPostsByBoardId(String boardId);
	Post selectPostById(String id);
	List<Post>selectPostsByContent(String content);
	List<Post>selectPostsByHashtag(String hashtag);
}
