package service.logic;

import java.util.List;

import domain.Board;
import domain.Post;
import service.facade.BoardService;
import store.facade.BoardStore;
import store.facade.PostStore;
import store.logic.BoardStoreLogic;
import store.logic.PostStoreLogic;

public class BoardServiceLogic implements BoardService {
	
	private BoardStore bStore;
	private PostStore pStore;
	
	public BoardServiceLogic() {
		bStore = new BoardStoreLogic();
		pStore = new PostStoreLogic();
	}

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
	public Post findPostById(String id) {
		return pStore.selectPostById(id);
	}

	@Override
	public List<Post> findPostsByTitle(String title) {
		return pStore.selectPostsByTitle(title);
	}

	@Override
	public List<Post> findPostsByContent(String content) {
		return pStore.selectPostsByContent(content);
	}

	@Override
	public List<Post> findPostsByHashTag(String hashTag) {
		return pStore.selectPostsByHashtag(hashTag);
	}
	

}
