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
		l.setId("11");
		
		LitStorage ls = new LitStorage();
		ls.setId("1");
		l.setLitStorage(ls);
		
		e.setLiterature(l);
		
		assertTrue(store.insertEpisode(e));
		System.out.println(store.insertEpisodeToGit(e, "message"));
	}
//
//	@Test
//	public void testUpdateEpisode() {
//		Episode e = new Episode();
//		e.setId("1");
//		e.setTitle("ss");
//		e.setContent("ㄻㄴㄻㄴㅇㄻdghddㄴㅇ\r\n아아");
//		
//		Member m = new Member();
//		m.setId("test");
//		
//		e.setWriter(m);
//		
//		e.setBound("0001");
//		
//		Literature l= new Literature();
//		l.setId("1");
//		
//		LitStorage ls = new LitStorage();
//		ls.setId("1");
//		l.setLitStorage(ls);
//		
//		e.setLiterature(l);
//		
//		assertTrue(store.updateEpisode(e));
//		assertTrue(store.updateEpisodeToGit(e));
//	}
//
//	@Test
//	public void testDeleteEpisode() {
//		Episode e = new Episode();
//		e.setId("2");
//		Member writer = new Member();
//		writer.setId("asfd");
//		e.setWriter(writer);
//		Literature literature = new Literature();
//		literature.setId("1");
//		LitStorage litStorage = new LitStorage();
//		litStorage.setId("1");
//		literature.setLitStorage(litStorage);
//		e.setLiterature(literature);
//		
//		assertTrue(store.deleteEpisode(e.getId()));
//		assertTrue(store.deleteEpisodeToGit(e));
//	}
//
//	@Test
//	public void testSelectEpisodeById() {
//		Episode e = store.selectEpisodeById("1");
//		System.out.println(e.getId());//8
//		System.out.println(e.getTitle());//aaa
//		System.out.println(e.getWriter().getId());
//		System.out.println(e.getLiterature().getName());
//		System.out.println(e.getLiterature().getLitStorage().getIntroduce());
//		System.out.println(e.getContent());
//		System.out.println(e.getContentFromGit());
//		System.out.println(e.getHistories().size());
//		System.out.println(e.getHistories().get(0).getEpisode().getId());
//	}
//
//	@Test
//	public void testSelectEpisodesByLiteratureId() {
//		List<Episode> list=store.selectEpisodesByLiteratureId("8");
//		Episode e = list.get(0);
//		System.out.println(e.getId());//8
//		System.out.println(e.getTitle());//aaa
//		System.out.println(e.getWriter().getId());
//		System.out.println(e.getLiterature().getName());
//		System.out.println(e.getLiterature().getLitStorage().getIntroduce());
//		System.out.println(e.getContent());
//		//System.out.println(e.getHistories().size());
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
