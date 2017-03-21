package test.store.logic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Board;
import domain.Member;
import domain.Post;
import store.logic.PostStoreLogic;

public class PostStoreLogicTest {

	private PostStoreLogic store;
	
	@Before
	public void setUp() {
		store = new PostStoreLogic();
	}

	@Test
	public void testInsertPost() {
		Post post = new Post();
		Member member = new Member();
		member.setId("55");
		Board board = new Board();
		board.setId("22");
		
		post.setId("11");
		post.setTitle("22");
		post.setContent("33");
		post.setHashTag("44");
		post.setWriter(member);
		post.setBoard(board);
		
		assertTrue(store.insertPost(post));
	}

	@Test
	public void testUpdatePost() {
		Post post = new Post();
		Member member = new Member();
		member.setId("44");
		Board board = new Board();
		board.setId("55");
		
		post.setId("9");
		post.setTitle("33");
		post.setContent("6666");
		post.setHashTag("1111");
		post.setWriter(member);
		post.setBoard(board);
		
		assertTrue(store.updatePost(post));
	}

	@Test
	public void testDeletePost() {
		assertTrue(store.deletePost("9"));
	}

	@Test
	public void testSelectPostsByBoardId() {
		List<Post> postList = store.selectPostsByBoardId("22");
		
		assertEquals("22", postList.get(0).getBoard().getId());
	}

	@Test
	public void testSelectPostById() {
		Post post = store.selectPostById("10");
	
		assertEquals("10", post.getId());
	}

	@Test
	public void testSelectPostsByContent() {
		List<Post> postList = store.selectPostsByContent("3");
		
		assertEquals("10", postList.get(0).getId());
	}

	@Test
	public void testSelectPostsByHashtag() {
		List<Post> postList = store.selectPostsByHashtag("4");
		
		assertEquals("10", postList.get(0).getId());
	}

}
