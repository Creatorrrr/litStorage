package test.store.logic;

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

	@Test
	public void testInsertLiterature() {
		String name = "f";
		String genre = "y";
		String imagepath = "a/a/a";
		String introduce = "w";
		Member creator = new Member();
		creator.setId("k");
		int hit = 2;
		LitStorage litStorage = new LitStorage();
		litStorage.setId("5");
		
		Literature literature = new Literature();
		
		literature.setName(name);
		literature.setGenre(genre);
		literature.setImagePath(imagepath);
		literature.setIntroduce(introduce);
		literature.setCreator(creator);
		literature.setHits(hit);
		literature.setLitStorage(litStorage);
		
		assertTrue(store.insertLiterature(literature));
		assertTrue(store.insertLiteratureToGit(literature));
	}
//
//	@Test
//	public void testDeleteLiterature() {
//		String name = "f";
//		String genre = "y";
//		String imagepath = "a/a/a";
//		String introduce = "w";
//		Member creator = new Member();
//		creator.setId("k");
//		int hit = 2;
//		LitStorage litStorage = new LitStorage();
//		litStorage.setId("1");
//		
//		Literature literature = new Literature();
//		
//		literature.setId("2");
//		literature.setName(name);
//		literature.setGenre(genre);
//		literature.setImagePath(imagepath);
//		literature.setIntroduce(introduce);
//		literature.setCreator(creator);
//		literature.setHits(hit);
//		literature.setLitStorage(litStorage);
//		
//		assertTrue(store.deleteLiterature("2"));
//		assertTrue(store.deleteLiteratureFromGit(literature));
//	}
//	
//	 @Test
//	 public void testSelectLiteraturesById() {
//		 Literature li = store.selectLiteraturesById("6");
//		 assertNotNull(li);
//		 assertEquals("sdaghjshg",li.getCreator().getEmail());
//		 System.out.println(li.getCreator().getId());
//	 }
//	 
//	 @Test
//	 public void testSelectLiteraturesByLitStorageId() {
//		 List<Literature> li = store.selectLiteraturesByLitStorageId("e");
//		 assertNotNull(li);
//		 assertEquals("�뀒�뒪�듃",li.get(0).getCreator().getName());
//		 assertEquals("test@gmail.com",li.get(0).getCreator().getEmail());
//	 }
//	
//	 @Test
//	 public void testSelectLiteraturesByName() {
//		 List<Literature> li = store.selectLiteraturesByName("f");
//		 assertNotNull(li);
//		 assertEquals("�뀒�뒪�듃",li.get(0).getCreator().getName());
//		 assertEquals("test@gmail.com",li.get(0).getCreator().getEmail());
//	 }
//
//	 @Test
//	 public void testSelectLiteraturesByGenreOrderByHits() {
//		 List<Literature> a = store.selectLiteraturesByGenreOrderByHits();
//		
//		 assertEquals(123, a.get(1).getHits());
//	 }
//	
//	 @Test
//	 public void testSelectLiteratureByGenreOrderById() {
//		 List<Literature> li = store.selectLiteratureByGenreOrderById("6");
//		
//		 assertNotNull(li);
//		 assertEquals("�뀒�뒪�듃",li.get(0).getCreator().getName());
//		 assertEquals("test@gmail.com",li.get(0).getCreator().getEmail());
//	 }
//	
//	 @Test
//	 public void testSelectLiteraturesByMemberId() {
//		 List<Literature> e = store.selectLiteraturesByMemberId("test2");
//		
//		 assertNotNull(e);
//		 assertEquals(2, e.size());
//		 assertEquals("�뀒�뒪�듃",e.get(0).getCreator().getName());
//		 assertEquals("test@gmail.com",e.get(0).getCreator().getEmail());
//	 }

}
