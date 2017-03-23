package test.store.logic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Episode;
import domain.LitStorage;
import domain.Literature;
import domain.Member;
import store.logic.EpisodeStoreLogic;

public class EpisodeStoreLogicTest {
	
	private EpisodeStoreLogic store;

	@Before
	public void setUp() throws Exception {
		store = new EpisodeStoreLogic();
	}

	@Test
	public void testInsertEpisode() {
		Episode e = new Episode();
		e.setTitle("aaa");
		e.setContent("아아\r\ndddd");
		
		Member m = new Member();
		m.setId("test");
		
		e.setWriter(m);
		
		e.setBound("0001");
		
		Literature l= new Literature();
		l.setId("22");
		
		LitStorage ls = new LitStorage();
		ls.setId("11");
		l.setLitStorage(ls);
		
		e.setLiterature(l);
		
		assertTrue(store.insertEpisode(e));
	}

//	@Test
//	public void testUpdateEpisode() {
//		Episode e = new Episode();
//		e.setId("15");
//		e.setTitle("ㅁㅁ");
//		e.setContent("오오옹\r\n앙다아아댜야ㅗㅎ");
//		
//		Member m = new Member();
//		m.setId("test");
//		
//		e.setWriter(m);
//		
//		e.setBound("0001");
//		
//		Literature l= new Literature();
//		l.setId("22");
//		
//		LitStorage ls = new LitStorage();
//		ls.setId("11");
//		l.setLitStorage(ls);
//		
//		e.setLiterature(l);
//		
//		assertTrue(store.updateEpisode(e));
//	}

//	@Test
//	public void testDeleteEpisode() {
//		Episode e = new Episode();
//		e.setId("7");
//		assertTrue(store.deleteEpisode(e));
//		
//	}
//
//	@Test
//	public void testSelectEpisodeByid() {
//		Episode e=store.selectEpisodeById("8");
//		System.out.println(e.getId());//8
//		System.out.println(e.getTitle());//aaa
//		System.out.println(e.getWriter().getId());
//		System.out.println(e.getLiterature().getName());
//		//System.out.println(e.getHistories().size());
//	}

//	@Test
//	public void testSelectEpisodesByLiteratureId() {
//		List<Episode> list=store.selectEpisodesByLiteratureId("22");
//		System.out.println(list.get(0).getId());//8
//		System.out.println(list.get(0).getTitle());//aaa
//	}
//
//	@Test
//	public void testUpdateBound() {
//		//????? where .....
//		List<Episode> list=store.updateBound("22");
//		System.out.println(list.get(0).getId());//8
//		System.out.println(list.get(0).getTitle());//aaa
//	}

}
