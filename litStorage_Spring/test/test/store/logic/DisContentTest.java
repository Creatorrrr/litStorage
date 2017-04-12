package test.store.logic;

import org.junit.Before;

import litstorage.store.logic.DiscussionContentStoreLogic;

public class DisContentTest {
	private DiscussionContentStoreLogic logic;

	@Before
	public void setup() {
		logic = new DiscussionContentStoreLogic();
	}

//	@Test
//	public void testInsertDiscussionContent() {
//		DiscussionContent con = new DiscussionContent();
//		DiscussionPlace dp = new DiscussionPlace();
//		dp.setId("123");
//		Member member = new Member();
//		member.setId("33");
//		con.setWriter(member);
//		con.setContent("dqwdqw");
//		con.setDiscussionPlace(dp);
//		boolean flag = logic.insertDiscussionContent(con);
//		assertEquals(true, flag);
//	}
//
//	@Test
//	public void testSelectDiscussionContentsByDiscussionPlaceId() {
//		List<DiscussionContent> list = logic.selectDiscussionContentsByDiscussionPlaceId("1");
//		System.out.println(list.size());
//		System.out.println(list.get(0).getDiscussionPlace().getId());
//		assertEquals(1,list.size());
//	}
//
//	@Test
//	public void testDeleteDiscussionContent() {
//		boolean flag = logic.deleteDiscussionContentById("2");
//		assertEquals(true, flag);
//	}

}
