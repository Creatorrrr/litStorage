package test.store.logic;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.ChangeHistory;
import domain.Episode;
import domain.Member;
import store.logic.ChangeHistoryStoreLogic;

public class ChangeHistoryLogicTest {

	private ChangeHistoryStoreLogic logic;
	
	@Before
	public void setup(){
		logic = new ChangeHistoryStoreLogic();
	}
	
	@Test
	public void testInsertChangeHistory() {
		ChangeHistory ch = new ChangeHistory();
		Episode ep = new Episode();
		ep.setId("1");
		ch.setId("11");
		Member member = new Member();
		member.setId("33");
		ch.setEditor(member);
		ch.setContent("asdf");
		ch.setMessage("qwer");
		ch.setEpisode(ep);
		boolean flag = logic.insertChangeHistory(ch);
		assertEquals(true, flag);
	}

	@Test
	public void testSelectChangeHistoriesByEpisodeId() {
		List<ChangeHistory> list = logic.selectChangeHistoriesByEpisodeId("1");
		assertEquals(1,list.size());
	}

	@Test
	public void testSelectChangeHistoryById() {
		ChangeHistory ch = logic.selectChangeHistoryById("5");
		assertEquals("asdf",ch.getContent());
	}

}
