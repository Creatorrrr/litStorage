package litstorage.service.facade;

import java.util.List;

import litstorage.domain.Board;
import litstorage.domain.Post;

public interface BoardService {
	public boolean registerBoard(Board board);
	public boolean removeBoard(String id);
	public List<Board> findAllBoards();
	public List<Board> findTitles();
	public boolean registerPost(Post post);
	public boolean modifyPost(Post post);
	public boolean removePost(String id);
	public List<Post> findPostsByBoardId(String id);
	public String findRowsByBoardId(String boardId);
	public List<Post> findPostsByBoardIdWithPage(String boardId, String page);
	public Post findPostById(String id);
	public List<Post> findPostsByTitle(String title, String boardId);
	public List<Post> findPostsByContent(String content, String boardId);
	public List<Post> findPostsByHashTag(String hashTag, String boardId);
}
