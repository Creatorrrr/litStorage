package test.store.logic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.LitStorage;
import domain.Member;
import domain.MemberLitStorage;
import store.logic.MemberLitStorageStoreLogic;

public class MemberLitStorageStoreLogicTest {
	
	private MemberLitStorageStoreLogic store;
	
	@Before
	public void setUp() {
		store = new MemberLitStorageStoreLogic();
	}

	@Test
	public void testInsertMemberLitStorage() {
		MemberLitStorage memberLit = new MemberLitStorage();
		Member member = new Member();
		member.setId("11");
		LitStorage storage = new LitStorage();
		storage.setId("22");
		
		memberLit.setMember(member);
		memberLit.setLitStorage(storage);
		
		boolean result = store.insertMemberLitStorage(memberLit);
		
		assertTrue(result);
	}

	@Test
	public void testSelectMemberLitStoragesByLitStorageId() {
		List<MemberLitStorage> list = null;
		
		list = store.selectMemberLitStoragesByLitStorageId("22");

		assertEquals("22", list.get(0).getLitStorage().getId());
	}

	@Test
	public void testSelectMemberLitStoragesByMemberId() {
		List<MemberLitStorage> list = null;
		
		list = store.selectMemberLitStoragesByMemberId("22");
	
		assertEquals("11", list.get(0).getMember().getId());
	}
	
	@Test
	public void testSelectMembersByLitStorageId() {
		List<MemberLitStorage> list = null;
		
		list = store.selectMemberLitStoragesByMemberId("22");
	
		assertEquals("11", list.get(0).getMember().getId());
	}

}
