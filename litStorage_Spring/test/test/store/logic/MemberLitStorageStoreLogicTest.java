package test.store.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import litstorage.domain.LitStorage;
import litstorage.domain.Member;
import litstorage.domain.MemberLitStorage;
import litstorage.store.logic.MemberLitStorageStoreLogic;

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
		member.setId("33");
		LitStorage storage = new LitStorage();
		storage.setId("1");
		
		memberLit.setMember(member);
		memberLit.setLitStorage(storage);
		
		boolean result = store.insertMemberLitStorage(memberLit);
		
		assertTrue(result);
	}

	@Test
	public void testSelectMemberLitStoragesByLitStorageId() {
		List<MemberLitStorage> list = null;
		
		list = store.selectMemberLitStoragesByLitStorageId("1");
		
		System.out.println(list.get(0).getLitStorage().getName());

		assertEquals("1", list.get(0).getLitStorage().getId());
	}

	@Test
	public void testSelectMemberLitStoragesByMemberId() {
		List<MemberLitStorage> list = null;
		
		list = store.selectMemberLitStoragesByMemberId("33");
		
		System.out.println(list.get(0).getMember().getEmail());
		System.out.println(list.get(0).getLitStorage().getIntroduce());
		
		assertEquals("33", list.get(0).getMember().getId());
	}
	
	@Test
	public void testSelectMembersByLitStorageId() {
		List<Member> list = store.selectMembersByLitStorageId("1");
	
		System.out.println(list.get(0).getEmail());
		
		assertEquals("33", list.get(0).getId());
	}

}
