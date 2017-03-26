package store.facade;

import java.util.List;

import domain.Post;

public interface PostStore {
	public boolean insertPost(Post post);
	public boolean updatePost(Post post);
	public boolean deletePost(String id);
	public List<Post> selectPostsByBoardId(String boardId);
	public String selectRowsByBoardId(String boardId);
	public List<Post> selectPostsByBoardIdWithPage(String boardId, String begin, String end);
	public Post selectPostById(String id);
	public List<Post> selectPostsByContent(String content, String boardId);
	public List<Post> selectPostsByHashtag(String hashTag, String boardId);
	public List<Post> selectPostsByTitle(String title, String boardId);
}
