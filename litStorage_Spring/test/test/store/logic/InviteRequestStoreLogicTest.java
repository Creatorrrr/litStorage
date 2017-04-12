package test.store.logic;

import org.junit.Before;

import litstorage.store.facade.InviteRequestStore;
import litstorage.store.logic.InviteRequestStoreLogic;

public class InviteRequestStoreLogicTest {
	
	private InviteRequestStore store;

	@Before
	public void setup() {
		store = new InviteRequestStoreLogic();
	}

//	@Test
//	public void testInsertInviteRequest() {
//		Member sender = new Member();
//		Member receiver = new Member();
//		
//		sender.setId("ccEfQw");
//		receiver.setId("dqdEWedd");
//		String message = "cdcqEEcccccccc";
//		String form = "ddqAa";
//		
//		InviteRequest ir = new InviteRequest();
//		
//		ir.setSender(sender);
//		ir.setReceiver(receiver);
//		ir.setMessage(message);
//		ir.setForm(form);
//		
//		boolean a = store.insertInviteRequest(ir);
//		
//		assertEquals(true, a);
//	}

//	@Test
//	public void testDeleteInviteRequest() {
//		boolean a = store.deleteInviteRequest("cccc", "dddd");
//		assertEquals(true, a);
//	}
//
//	@Test
//	public void testSelectInviteRequestBySenderId() {
//		List<InviteRequest> a = store.selectInviteRequestBySenderId("cccc");
//		
//		assertNotNull(a);
//		assertEquals(1, a.size());
//	}
//
//	@Test
//	public void testSelectInviteRequestByReceiverId() {
//		List<InviteRequest> a = store.selectInviteRequestByReceiverId("ddEdd");
//		
//		assertNotNull(a);
//		assertEquals(1, a.size());
//		System.out.println(a.get(0).getSendTime().toString());
//	}

}
