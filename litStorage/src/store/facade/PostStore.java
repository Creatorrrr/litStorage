package store.facade;

import java.util.List;

import domain.Post;

public interface PostStore {
	boolean insertPost(Post post);
	boolean updatePost(Post post);
	boolean deletePost(String id);
	List<Post> selectPostsByBoardId(String boardId);
	Post selectPostById(String id);
	List<Post> selectPostsByContent(String content, String boardId);
	List<Post> selectPostsByHashtag(String hashTag, String boardId);
	List<Post> selectPostsByTitle(String title, String boardId);
}
