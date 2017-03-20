package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.ChangeHistory;
import domain.Episode;
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
		ch.setContent("블라라");
		ch.setMessage("우아앙");
		ch.setEpisode(ep);
		ch.setChangeTime(new Date(20170320));
		boolean flag = logic.insertChangeHistory(ch);
		assertEquals(true, flag);
	}

	@Test
	public void testSelectChangeHistoriesByEpisodeId() {
		List<ChangeHistory> list = logic.selectChangeHistoriesByEpisodeId("11");
		assertEquals(1,list.size());
	}

	@Test
	public void testSelectChangeHistoryById() {
		ChangeHistory ch = logic.selectChangeHistoryById("11");
		assertEquals("블라라",ch.getContent());
	}

}
