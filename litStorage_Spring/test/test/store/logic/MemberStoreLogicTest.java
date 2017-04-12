package test.store.logic;

import org.junit.Before;

import litstorage.store.logic.MemberStoreLogic;

public class MemberStoreLogicTest {

	private MemberStoreLogic logic;
	
	@Before
	public void setup() {
		logic = new MemberStoreLogic();
	}

//	@Test
//	public void testInsertMember() {
//		MemberStoreLogic store = new MemberStoreLogic();
//		
//		Member member = new Member();
//		member.setId("test");
//		member.setName("테스트");
//		member.setEmail("test@gamil.com");
//		member.setPassword("1234");
//		
////		boolean b=logic.insertMember(member);
////		assertEquals(b,true);
//		assertTrue(logic.insertMember(member));
//
//	}
//
//	@Test
//	public void testupdateMember() {
//		MemberStoreLogic logic = new MemberStoreLogic();
//		
//		Member m = new Member();
//		m.setId("test");
//		m.setName("1테스트");
//		m.setPassword("1234");
//		m.setEmail("test@gamail.com");
//
//		assertTrue(logic.updateMember(m));
//
//	}

//	@Test
//	public void testDeleteMember() {
//		
//		Member m = new Member();
//		m.setId("test");
//		assertEquals(true,logic.deleteMember("test"));
//
//	}
	
//	@Test
//	public void testSelectMemberById() {
//		
//		Member member = logic.selectMemberById("test");
//		assertNotNull(member);
//		assertEquals("test",member.getId());
//	}
	
//	@Test
//	public void testSelectMembersByName() {
//	
//		List<Member> list = logic.selectMembersByName("1테스트");
//		assertNotNull(list);
//		assertEquals("1테스트",list.get(0).getName());
//	
//	}
}
