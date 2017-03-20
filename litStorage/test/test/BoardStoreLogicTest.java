package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Board;
import store.logic.BoardStoreLogic;

public class BoardStoreLogicTest {

	private BoardStoreLogic logic;
	
	@Before
	public void setup(){
		logic = new BoardStoreLogic();
	}
	@Test
	public void testInsertBoard() {
		Board b = new Board();
		b.setId("1");
		b.setTitle("¹«Çù");
		boolean flag = logic.insertBoard(b);
		assertEquals(true,flag);
	}

	@Test
	public void testDeleteBoard() {
		boolean flag = logic.deleteBoard("1");
		assertEquals(true,flag);
	}

	@Test
	public void testSelectAll() {
		Board b = new Board();
		b.setId("2");
		b.setTitle("ÆÇÅ¸Áö");
		boolean flag = logic.insertBoard(b);
		List<Board> list = logic.selectAll();
		assertEquals(true, flag);
	}

}
