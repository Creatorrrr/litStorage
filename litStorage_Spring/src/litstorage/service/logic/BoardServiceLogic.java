package litstorage.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import litstorage.constants.Constants;
import litstorage.domain.Board;
import litstorage.domain.Post;
import litstorage.service.facade.BoardService;
import litstorage.store.facade.BoardStore;
import litstorage.store.facade.PostStore;

@Service
public class BoardServiceLogic implements BoardService {
	
	@Autowired
	private BoardStore bStore;
	@Autowired
	private PostStore pStore;

	@Override
	public boolean registerBoard(Board board) {
		return bStore.insertBoard(board);
	}

	@Override
	public boolean removeBoard(String id) {
		return bStore.deleteBoard(id);
	}

	@Override
	public List<Board> findAllBoards() {
		return bStore.selectAll();
	}
	
	@Override
	public List<Board> findTitles() {
		return bStore.selectTitles();
	}

	@Override
	public boolean registerPost(Post post) {
		return pStore.insertPost(post);
	}

	@Override
	public boolean modifyPost(Post post) {
		return pStore.updatePost(post);
	}

	@Override
	public boolean removePost(String id) {
		return pStore.deletePost(id);
	}

	@Override
	public List<Post> findPostsByBoardId(String id) {
		return pStore.selectPostsByBoardId(id);
	}
	
	@Override
	public String findRowsByBoardId(String boardId) {
		return pStore.selectRowsByBoardId(boardId);
	}
	
	@Override
	public List<Post> findPostsByBoardIdWithPage(String boardId, String page) {
		String begin = (Integer.parseInt(page) - 1) * Constants.POST_ROW_SIZE + 1 + "";
		String end = Integer.parseInt(page) * Constants.POST_ROW_SIZE + "";

		return pStore.selectPostsByBoardIdWithPage(boardId, begin, end);
	}

	@Override
	public Post findPostById(String id) {
		return pStore.selectPostById(id);
	}

	@Override
	public List<Post> findPostsByTitle(String title, String boardId) {
		return pStore.selectPostsByTitle(title, boardId);
	}

	@Override
	public List<Post> findPostsByContent(String content, String boardId) {
		return pStore.selectPostsByContent(content, boardId);
	}

	@Override
	public List<Post> findPostsByHashTag(String hashTag, String boardId) {
		return pStore.selectPostsByHashtag(hashTag, boardId);
	}
	

}
