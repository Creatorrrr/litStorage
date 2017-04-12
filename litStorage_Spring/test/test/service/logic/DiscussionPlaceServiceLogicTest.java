package test.service.logic;

import org.junit.Before;

import litstorage.service.facade.DiscussionPlaceService;
import litstorage.service.logic.DiscussionPlaceServiceLogic;

public class DiscussionPlaceServiceLogicTest {

	private DiscussionPlaceService service;
	
	
	@Before
	public void setUp() throws Exception {
		service = new DiscussionPlaceServiceLogic();
	}

//	@Test
//	public void testRegisterDiscussionPlace() {
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
//		assertTrue(service.registerDiscussionPlace(d));
//	}
//
//	@Test
//	public void testFindDiscussionPlacesByLitStorageId() {
//		List<DiscussionPlace> list= service.findDiscussionPlacesByLitStorageId("333");
//		System.out.println(list.get(0).getId());//4
//		System.out.println(list.get(0).getTitle());//tttttttt
//		System.out.println(list.get(0).getDiscussionContents().size());
//	}
//
//	@Test
//	public void testRegisterDiscussionContent() {
//		DiscussionContent con = new DiscussionContent();
//		DiscussionPlace dp = new DiscussionPlace();
//		dp.setId("123");
//		Member member = new Member();
//		member.setId("33");
//		con.setWriter(member);
//		con.setContent("dqwdqw");
//		con.setDiscussionPlace(dp);
//		boolean flag = service.registerDiscussionContent(con);
//		assertEquals(true, flag);
//	}
//
//	@Test
//	public void testRemoveDiscussionContent() {
//		boolean flag = service.removeDiscussionContent("2");
//		assertEquals(true, flag);
//	}
//
//	@Test
//	public void testFindDiscussionContentsByDiscussionPlaceId() {
//		List<DiscussionContent> list = service.findDiscussionContentsByDiscussionPlaceId("123");
//		assertEquals(1,list.size());
//	}
//
//	@Test
//	public void testFindDiscussionPlaceById() {
//		DiscussionPlace d=service.findDiscussionPlaceById("4");
//		assertEquals("4", d.getId());
//		assertEquals("tttttttt", d.getTitle());
//		System.out.println( d.getCreator().getId());
//		System.out.println(d.getLitStorage().getId());
//		System.out.println(d.getDiscussionContents().size());
//	}

//	@Test
//	public void testFindDiscussionPlacesByName() {
//		List<DiscussionPlace> list=service.findDiscussionPlacesByName("ttttt");
//		System.out.println(list.get(0).getId());//4
//		System.out.println(list.get(0).getTitle());//tttttttt
//		System.out.println(list.get(0).getDiscussionContents().size());
//	}
//
//	@Test
//	public void testFindDiscussionPlacesByMemberId() {
//		List<DiscussionPlace> list = service.findDiscussionPlacesByMemberId("test");
//		System.out.println(list.get(0).getId());//4
//		System.out.println(list.get(0).getTitle());//tttttttt
//	}

}
