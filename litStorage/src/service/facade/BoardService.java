package service.facade;

import java.util.List;

import domain.Board;
import domain.Post;

public interface BoardService {
	public boolean registerBoard(Board board);
	public boolean removeBoard(String id);
	public List<Board> findAllBoards();
	public boolean registerPost(Post post);
	public boolean modifyPost(Post post);
	public boolean removePost(String id);
	public List<Post> findPostsByBoardId(String id);
	public Post findPostById(String id);
	public List<Post> findPostsByTitle(String title, String boardId);
	public List<Post> findPostsByContent(String content, String boardId);
	public List<Post> findPostsByHashTag(String hashTag, String boardId);
}
