package dataTest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import litstorage.domain.Board;
import litstorage.domain.LitStorage;
import litstorage.domain.Literature;
import litstorage.domain.Member;
import litstorage.service.facade.LitStorageService;
import litstorage.service.facade.LiteratureService;
import litstorage.service.facade.MemberService;
import litstorage.service.logic.BoardServiceLogic;
import litstorage.service.logic.LitStorageServiceLogic;
import litstorage.service.logic.LiteratureServiceLogic;
import litstorage.service.logic.MemberServiceLogic;

public class DataGenerator {

	private LitStorageService lsService;
	private LiteratureService lService;
	private MemberService mService;
	private List<Member> memberList;
	private List<String> memberIdList;

	public DataGenerator() {
		lsService = new LitStorageServiceLogic();
		lService = new LiteratureServiceLogic();
		mService = new MemberServiceLogic();
		memberIdList = new ArrayList<>();
		memberList = mService.findMemberByName("");
		for (Member m : memberList) {
			memberIdList.add(m.getId());
		}
	}
//
//	 @Test
//	 public void makeLitStorage() {
//	 List<String> nameList = new ArrayList<>();
//	 try {
//	 BufferedReader in = new BufferedReader(new
//	 FileReader("litStorageName.txt"));
//	 String str;
//	
//	 while ((str = in.readLine()) != null) {
//	 nameList.add(str);
//	 }
//	
//	 int nameRand;
//	 int introRand;
//	 int writerRand;
//	 for(int i = 0 ; i < nameList.size(); i++){
//	 nameRand = (int)(Math.random() * (nameList.size()));
//	 introRand = (int)(Math.random() * (nameList.size()));
//	 writerRand = (int)(Math.random() * (nameList.size()));
//	 LitStorage lit = new LitStorage();
//	 lit.setName(nameList.get(nameRand));
//	 lit.setIntroduce(nameList.get(introRand));
//	 lit.setCreator(memberList.get(writerRand));
//	 lsService.registerLitStorage(lit);
//	 }
//	
//	
//	 in.close();
//	 } catch (FileNotFoundException e) {
//	 e.printStackTrace();
//	 } catch (IOException e) {
//	 e.printStackTrace();
//	 }
//	
//	
//	 assertEquals(true,true);
//	 }

	@Test
	public void makeLiterature() {
		Document doc = null;
		Elements titleContents = null;
		Elements imageContents = null;
		List<LitStorage> lsList = lsService.findAll();
		List<Board> genreList = new BoardServiceLogic().findAllBoards();
		List<String> introList = new ArrayList<>();
		
		int lsRand, genreRand, introRand, memberRand;
		try {
			BufferedReader in = new BufferedReader(new FileReader("litStorageName.txt"));
			 String str;
				
			 while ((str = in.readLine()) != null) {
				 introList.add(str);
			 }
			 in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		for (int i = 1; i <= 200; i++) {
			try {
				doc = Jsoup.connect("https://ridibooks.com/category/books/1710?page="+i).get();
				titleContents = doc.select("h3.meta_title span.title_text");
				imageContents = doc.select("div.thumbnail_image img");
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (int j = 0; j < 20; j++) {
				lsRand = (int)(Math.random() * (300)+1);
				genreRand = (int)((Math.random()*7)-1);
				introRand = (int)(Math.random() * (introList.size()));
				memberRand = (int)(Math.random() * (memberList.size()));
				Literature liter = new Literature();
				liter.setName(titleContents.get(j).text());
				liter.setGenre(genreList.get(genreRand).getTitle());
				liter.setImagePath(imageContents.get(j).attr("src"));
				liter.setIntroduce(introList.get(introRand));
				liter.setCreator(memberList.get(memberRand));
				liter.setHits((int)(Math.random()*90000));
				liter.setLitStorage(lsList.get(lsRand));
				
				lService.registerLiterature(liter);
			}
		}
		
		
		assertEquals(true, true);
	}
}
