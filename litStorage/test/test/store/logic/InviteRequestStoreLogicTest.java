package test.store.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import domain.InviteRequest;
import domain.Member;
import store.facade.InviteRequestStore;
import store.logic.InviteRequestStoreLogic;

public class InviteRequestStoreLogicTest {
	
	private InviteRequestStore store;

	@Before
	public void setup() {
		store = new InviteRequestStoreLogic();
	}

	@Test
	public void testInsertInviteRequest() {
		Member sender = new Member();
		Member receiver = new Member();
		
		sender.setId("cccc");
		receiver.setId("dddd");
		String message = "cccccccccc";
		String form = "dddd";
		
		InviteRequest ir = new InviteRequest();
		
		ir.setSender(sender);
		ir.setReceiver(receiver);
		ir.setMessage(message);
		ir.setForm(form);
		
		store.insertInviteRequest(ir);
	}

//	@Test
//	public void testDeleteInviteRequest() {
//		store.deleteInviteRequest("aaaa", "bbbb");
//	}
//
//	@Test
//	public void testSelectInviteRequestBySenderId() {
//		store.selectInviteRequestBySenderId("aaaa");
//		assertEquals(expected, actual);
//	}
//
	@Test
	public void testSelectInviteRequestByReceiverId() {
		store.selectInviteRequestByReceiverId("bbbb");
	}

}
