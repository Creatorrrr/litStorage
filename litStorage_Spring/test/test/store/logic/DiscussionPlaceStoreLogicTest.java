package test.store.logic;

import org.junit.Before;

import litstorage.store.logic.DiscussionPlaceStoreLogic;

public class DiscussionPlaceStoreLogicTest {
	
	private DiscussionPlaceStoreLogic store; 

	@Before
	public void setUp() throws Exception {
		store  = new DiscussionPlaceStoreLogic();
		
	}

//	@Test
//	public void testInsertDiscussionPlace() {
//		DiscussionPlace d = new DiscussionPlace();
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
//		System.out.println( d.getCreator().getId());
//		System.out.println(d.getLitStorage().getId());//
//		System.out.println(d.getDiscussionContents().size());
//	}

//	@Test
//	public void testSelectDiscussionPlaceByLitStorageId() {
//		List<DiscussionPlace> list= store.selectDiscussionPlaceByLitStorageId("333");
//		System.out.println(list.get(0).getId());//4
//		System.out.println(list.get(0).getTitle());//tttttttt
//		System.out.println(list.get(0).getDiscussionContents().size());
//	}

//	@Test
//	public void testSelectDiscussionPlaceByName() {
//		List<DiscussionPlace> list=store.selectDiscussionPlaceByName("ttttt");
//		System.out.println(list.get(0).getId());//4
//		System.out.println(list.get(0).getTitle());//tttttttt
//		System.out.println(list.get(0).getDiscussionContents().size());
//	}
//
//	@Test
//	public void testSelectDiscussionPlaceByMemberId() {
//		List<DiscussionPlace> list = store.selectDiscussionPlaceByMemberId("test");
//		System.out.println(list.get(0).getId());//4
//		System.out.println(list.get(0).getTitle());//tttttttt
//	}

}
