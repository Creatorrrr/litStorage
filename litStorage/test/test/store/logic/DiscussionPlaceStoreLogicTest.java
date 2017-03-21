package test.store.logic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.DiscussionPlace;
import domain.LitStorage;
import domain.Member;
import store.logic.DiscussionPlaceStoreLogic;

public class DiscussionPlaceStoreLogicTest {
	
	private DiscussionPlaceStoreLogic store; 

	@Before
	public void setUp() throws Exception {
		store  = new DiscussionPlaceStoreLogic();
		
	}

//	@Test
//	public void testInsertDiscussionPlace() {
//		DiscussionPlace d = new DiscussionPlace();
//		d.setId("111");
//		d.setTitle("tttttttt");
//		
//		Member u= new Member();
//		u.setId("test");
//		d.setCreator(u);
//		
//		LitStorage l= new LitStorage();
//		l.setId("333");
//		d.setLitStorage(l);
//		
//		assertTrue(store.insertDiscussionPlace(d));
//	}

//	@Test
//	public void testSelectDiscussionPlaceById() {
//
//		DiscussionPlace d=store.selectDiscussionPlaceById("4");
//		assertEquals("4", d.getId());
//		assertEquals("tttttttt", d.getTitle());
//		//assertEquals("test", d.getCreator().getId());
//		
//	}
//
//	@Test
//	public void testSelectDiscussionPlaceByLitStorageId() {
//		List<DiscussionPlace> list= store.selectDiscussionPlaceByLitStorageId("333");
//		System.out.println(list.get(0).getId());//4
//		System.out.println(list.get(0).getTitle());//tttttttt
//	}

//	@Test
//	public void testSelectDiscussionPlaceByName() {
//		//--------???
//		List<DiscussionPlace> list=store.selectDiscussionPlaceByName("tttttttt");
//		System.out.println(list.get(0).getId());//4
//		System.out.println(list.get(0).getTitle());//tttttttt
//	}
//
//	@Test
//	public void testSelectDiscussionPlaceByMemberId() {
//		List<DiscussionPlace> list = store.selectDiscussionPlaceByMemberId("test");
//		System.out.println(list.get(0).getId());//4
//		System.out.println(list.get(0).getTitle());//tttttttt
//	}

}
