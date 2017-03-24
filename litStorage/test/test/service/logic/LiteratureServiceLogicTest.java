package test.service.logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Episode;
import domain.LitStorage;
import domain.Literature;
import domain.Member;
import service.logic.LiteratureServiceLogic;

public class LiteratureServiceLogicTest {
	
	private LiteratureServiceLogic service;
	
	@Before
	public void setUp() {
		service = new LiteratureServiceLogic();
	}

//	@Test
//	public void testRegisterLiterature() {
//		String name = "f";
//		String genre = "y";
//		String imagepath = "a/a/a";
//		String introduce = "w";
//		Member creator = new Member();
//		creator.setId("33");
//		int hit = 2;
//		LitStorage litStorage = new LitStorage();
//		litStorage.setId("11");
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
//		assertTrue(service.registerLiterature(literature));
//	}
//
//	@Test
//	public void testRemoveLiterature() {
//		assertTrue(service.removeLiterature("6"));
//	}
//
//	@Test
//	public void testRegisterEpisode() {
//		Episode e = new Episode();
//		e.setTitle("aaa");
//		e.setContent("아아\r\ndddd");
//		
//		Member m = new Member();
//		m.setId("33");
//		
//		e.setWriter(m);
//		
//		e.setBound("0001");
//		
//		Literature l= new Literature();
//		l.setId("9");
//		
//		LitStorage ls = new LitStorage();
//		ls.setId("11");
//		l.setLitStorage(ls);
//		
//		e.setLiterature(l);
//		
//		assertTrue(service.registerEpisode(e));
//	}
//
//	@Test
//	public void testModifyEpisode() {
//		Episode e = new Episode();
//		e.setId("38");
//		e.setTitle("gggggg");
//		e.setContent("asdffff\r\n아아\r\ndddd");
//		
//		Member m = new Member();
//		m.setId("33");
//		
//		e.setWriter(m);
//		
//		e.setBound("0001");
//		
//		Literature l= new Literature();
//		l.setId("6");
//		
//		LitStorage ls = new LitStorage();
//		ls.setId("10");
//		l.setLitStorage(ls);
//		
//		e.setLiterature(l);
//		
//		assertTrue(service.modifyEpisode(e));
//	}
//
//	@Test
//	public void testRemoveEpisode() {
//		Episode e = new Episode();
//		e.setId("29");
//		
//		Member m = new Member();
//		m.setId("33");
//		
//		e.setWriter(m);
//		
//		e.setBound("0001");
//		
//		Literature l= new Literature();
//		l.setId("2");
//		
//		LitStorage ls = new LitStorage();
//		ls.setId("8");
//		l.setLitStorage(ls);
//		
//		e.setLiterature(l);
//		service.removeEpisode(e);
//	}
//	
//	@Test
//	public void testSelectEpisodeById() {
//		System.out.println(service.findEpisodeById("29").getHistories().get(2).getMessage());
//	}

}
