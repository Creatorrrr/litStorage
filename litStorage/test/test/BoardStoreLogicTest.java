package test;

import static org.junit.Assert.*;

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
		assertEquals(flag,true);
	}

//	@Test
//	public void testDeleteBoard() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSelectAll() {
//		fail("Not yet implemented");
//	}

}
