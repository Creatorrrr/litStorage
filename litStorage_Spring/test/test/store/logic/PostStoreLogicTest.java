package test.store.logic;

import org.junit.Before;
import org.junit.Test;

import litstorage.store.logic.PostStoreLogic;

public class PostStoreLogicTest {

	private PostStoreLogic logic;
	
	@Before
	public void setUp() {
		logic = new PostStoreLogic();
	}
	
//	@Test  
//	public void testInsertPost() {
//		Post post = new Post();
//		
//		Member member = new Member();
//		member.setId("Mtest");
//		
//		Board board = new Board();
//		board.setId("Btest");
//		
//		post.setTitle("홍길동전");
//		post.setContent("동에번쩍");
//		post.setHashTag("길동이");
//		post.setWriter(member);
//		post.setBoard(board);
//		
//		assertTrue(logic.insertPost(post));
//	}
//	@Test   
//	public void testUpdatePost() {
//		Post post = new Post();
//		
//		Member member = new Member();
//		member.setId("Mtest");
//		
//		Board board = new Board();
//		board.setId("Btest");
//		
//		post.setId("4");
//		post.setTitle("홍길동전 1화");
//		post.setContent("동에번쩍");
//		post.setHashTag("길동이");
//		post.setWriter(member);
//		post.setBoard(board);
//		assertTrue(logic.updatePost(post));
//	}
//
//	@Test      
//	public void testDeletePost() {
//		Post post = new Post();
//		post.setId("4");
//		assertEquals(true,logic.deletePost("4"));
//		
//	}
//
//	@Test
//	public void testSelectPostsByBoardId() {
//		List<Post> list = logic.selectPostsByBoardId("1");
//		
//		assertNotNull(list);
//		assertEquals("1", list.get(0).getBoard().getId());
//
//	}
//
//	@Test  
//	public void testSelectPostById() {
//		Post post = logic.selectPostById("4");
//		
//		assertEquals("4",post.getId());
//	}
//
//	@Test    
//	public void testSelectPostsByContent() {		
//		List<Post> postList = logic.selectPostsByContent("동에번쩍", "1");
//	
//		assertEquals(1,postList.size());
//		assertEquals("동에번쩍",postList.get(0).getContent());
//	}
//
//	@Test
//	public void testSelectPostsByHashtag() {
//		List<Post> postList = logic.selectPostsByHashtag("길동이", "1");
//		
//		assertEquals("길동이", postList.get(0).getHashTag());
//	}
//	
//	@Test
//	public void testSelectPostsByTitle() {
//		List<Post> postList = logic.selectPostsByTitle("22", "1");
//		
//		assertEquals("22", postList.get(0).getBoard().getId());
//		assertEquals("4", postList.get(1).getBoard().getId());
//	}
	
	@Test
	public void testSelectRowsByBoardId() {
		System.out.println(logic.selectRowsByBoardId("2"));
	}

}
