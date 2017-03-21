package test.store.logic;

import static org.junit.Assert.*;
import org.apache.log4j.Logger; 
import org.apache.log4j.BasicConfigurator; 

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.DiscussionContent;
import domain.DiscussionPlace;
import domain.Member;
import store.logic.DiscussionContentStoreLogic;

public class DisContentTest {
	private DiscussionContentStoreLogic logic;

	@Before
	public void setup() {
		logic = new DiscussionContentStoreLogic();
	}

	@Test
	public void testInsertDiscussionContent() {
		DiscussionContent con = new DiscussionContent();
		DiscussionPlace dp = new DiscussionPlace();
		dp.setId("123");
		Member member = new Member();
		member.setId("33");
		con.setWriter(member);
		con.setContent("dqwdqw");
		con.setDiscussionPlace(dp);
		boolean flag = logic.insertDiscussionContent(con);
		assertEquals(true, flag);
	}

	@Test
	public void testSelectDiscussionContentsByDiscussionPlaceId() {
		List<DiscussionContent> list = logic.selectDiscussionContentsByDiscussionPlaceId("123");
		assertEquals(1,list.size());
	}

	@Test
	public void testDeleteDiscussionContent() {
		boolean flag = logic.deleteDiscussionContentById("2");
		assertEquals(true, flag);
	}

}
