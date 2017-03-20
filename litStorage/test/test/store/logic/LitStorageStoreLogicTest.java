package test.store.logic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.LitStorage;
import domain.Member;
import store.logic.LitStorageStoreLogic;

public class LitStorageStoreLogicTest {
	
	private LitStorageStoreLogic store;
	
	@Before
	public void setUp() {
		store = new LitStorageStoreLogic();
	}
	
//	@Test
//	public void testInsertLitStorage() {
//		LitStorage litStorage = new LitStorage();
//		Member member = new Member();
//		member.setId("11");
//		
//		litStorage.setName("asdf");
//		litStorage.setIntroduce("ffff");
//		litStorage.setCreator(member);
//		
//		assertTrue(store.insertLitStorage(litStorage));
//	}
//
//	@Test
//	public void testDeleteLitStorage() {
//		assertTrue(store.deleteLitStorage("5"));
//	}
//
//	@Test
//	public void testSelectLitStorageById() {
//		LitStorage litStorage = store.selectLitStorageById("6");
//		System.out.println(litStorage.getCreator().getId());
//		assertEquals("6", litStorage.getId());
//	}
//
//	@Test
//	public void testSelectLitStoragesByMemberId() {
//		List<LitStorage> litStorageList = store.selectLitStoragesByMemberId("11");
//		System.out.println(litStorageList.get(0).getCreator().getId());
//		assertEquals("6", litStorageList.get(0).getId());
//	}
//
//	@Test
//	public void testSelectLitStoragesByName() {
//		List<LitStorage> litStorageList = store.selectLitStoragesByName("sd");
//		System.out.println(litStorageList.get(0).getCreator().getId());
//		assertEquals("6", litStorageList.get(0).getId());
//	}
//
//	@Test
//	public void testSelectAll() {
//		List<LitStorage> litStorageList = store.selectAll();
//		System.out.println(litStorageList.get(0).getCreator().getId());
//		assertEquals("6", litStorageList.get(0).getId());
//	}

}
