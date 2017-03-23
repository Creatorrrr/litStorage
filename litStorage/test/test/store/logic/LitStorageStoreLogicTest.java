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
//		member.setId("33");
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
//		LitStorage litStorage = new LitStorage();
//		litStorage.setId("9");
//		
//		assertTrue(store.deleteLitStorage(litStorage));
//	}
//
//	@Test
//	public void testSelectLitStorageById() {
//		LitStorage litStorage = store.selectLitStorageById("1");
//		
//		System.out.println(litStorage.getCreator().getId());
//		System.out.println(litStorage.getLiteratures().get(0).getGenre());
//		System.out.println(litStorage.getParticipants().get(0).getEmail());
//		System.out.println(litStorage.getDiscussionPlaces().get(0).getTitle());
//		
//		assertEquals("1", litStorage.getId());
//	}
//
//	@Test
//	public void testSelectLitStoragesByMemberId() {
//		List<LitStorage> litStorageList = store.selectLitStoragesByMemberId("33");
//		System.out.println(litStorageList.get(0).getCreator().getId());
//		assertEquals("1", litStorageList.get(0).getId());
//	}
//
//	@Test
//	public void testSelectLitStoragesByName() {
//		List<LitStorage> litStorageList = store.selectLitStoragesByName("sd");
//		System.out.println(litStorageList.get(0).getCreator().getId());
//		assertEquals("1", litStorageList.get(0).getId());
//	}
//
//	@Test
//	public void testSelectAll() {
//		List<LitStorage> litStorageList = store.selectAll();
//		System.out.println(litStorageList.get(0).getCreator().getId());
//		assertEquals("1", litStorageList.get(0).getId());
//	}

}
