package test.service.logic;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import litstorage.service.logic.LitStorageServiceLogic;

public class LitStorageServiceLogicTest {
	
	private LitStorageServiceLogic service;
	
	@Before
	public void setUp() {
		service = new LitStorageServiceLogic();
	}

//	@Test
//	public void testRegisterLitStorage() {
//		LitStorage litStorage = new LitStorage();
//		Member member = new Member();
//		member.setId("33");
//		
//		litStorage.setName("aeaetaebser");
//		litStorage.setIntroduce("qwbtwqbwq");
//		litStorage.setCreator(member);
//		assertTrue(service.registerLitStorage(litStorage));
//	}
//
	@Test
	public void testRemoveLitStorage() {
		assertTrue(service.removeLitStorage("11"));
	}

}
