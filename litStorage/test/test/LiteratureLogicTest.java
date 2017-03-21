package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.LitStorage;
import domain.Literature;
import domain.Member;
import store.facade.LiteratureStore;
import store.logic.LiteratureStoreLogic;

public class LiteratureLogicTest {
	
	private LiteratureStore store;
	

	@Before
	public void setup() {
		store = new LiteratureStoreLogic();
	}

//	@Test
//	public void testInsertLiterature() {
//		String name = "f";
//		String genre = "y";
//		String imagepath = "a/a/a";
//		String introduce = "w";
//		Member creator = new Member();
//		creator.setId("k");
//		int hit = 2;
//		LitStorage litStorage = new LitStorage();
//		litStorage.setId("e");
//		
//		Literature literature = new Literature();
//		
//		literature.setName(name);
//		literature.setGenre(genre);
//		literature.setImagePath(imagepath);
//		literature.setIntroduce(introduce);
//		literature.setCreator(creator);
//		literature.setHits(hit);
//		literature.setLitStorage(litStorage);
//		
//		boolean a = store.insertLiterature(literature);
//		
//		assertEquals(true, a);
//		
//	}

//	@Test
//	public void testDeleteLiterature() {
//		String id = "3";
//		
//		boolean a = store.deleteLiterature(id);
//		
//		assertEquals(true, a);
//	}
//
//	@Test
//	public void testSelectLiteraturesById() {
//		Literature li = store.selectLiteraturesById("6");
//		assertNotNull(li);
//		assertEquals("sdaghjshg",li.getIntroduce());   
//	}
//
//	@Test
//	public void testSelectLiteraturesByLitStorageId() {
//		List<Literature> li = store.selectLiteraturesByLitStorageId("w");
//		assertNotNull(li);
//		assertEquals(2,li.size());   
//	}
//
//	@Test
//	public void testSelectLiteraturesByName() {
//		List<Literature> li = store.selectLiteraturesByName("aaaaa");
//		assertNotNull(li);
//		assertEquals(1, li.size());
//	}
//
//	@Test
//	public void testSelectLiteraturesByGenreOrderByHits() {
//		List<Literature> a = store.selectLiteraturesByGenreOrderByHits();
//		
//		assertEquals(123, a.get(0).getHits());
//	}
//
	@Test
	public void testSelectLiteratureByGenreOrderById() {
		List<Literature> li = store.selectLiteratureByGenreOrderById("6");
		
		assertNotNull(li);
		assertEquals("segeg", li.get(0).getGenre());
	}
//
//	@Test
//	public void testSelectLiteraturesByMemberId() {
//		store.selectLiteraturesByMemberId("sdgsgseg");
//	}

}
