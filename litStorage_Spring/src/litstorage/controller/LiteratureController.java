package litstorage.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import litstorage.constants.Constants;
import litstorage.domain.Board;
import litstorage.domain.LitStorage;
import litstorage.domain.Literature;
import litstorage.domain.Member;
import litstorage.domain.wrapper.Wrapper;
import litstorage.service.facade.BoardService;
import litstorage.service.facade.LitStorageService;
import litstorage.service.facade.LiteratureService;
import litstorage.utils.AutoCloser;

@Controller
@RequestMapping("literature")
public class LiteratureController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private LitStorageService lsService;
	@Autowired
	private LiteratureService lService;
	
	@RequestMapping(value="register.do", method=RequestMethod.GET)
	public String showLiteratureRegisterForm(String litStorageId, Model model) {
		List<Board> boardList = boardService.findTitles();
		List<Board> bListRemoved = new ArrayList<>();

		/* 장르 dropdownlist를 만들기 위해 게시판 이름을 싹 보내줌(신고 빼고) */
		for (Board b : boardList) {
			if (!b.getTitle().equals("신고")) {
				bListRemoved.add(b);
			}
		}

		model.addAttribute("boards", bListRemoved);
		model.addAttribute("litStorageId", litStorageId);

		return "literatureRegister";
	}
	
	@RequestMapping(value="register.do", method=RequestMethod.POST)
	public String registerLiterature(Model model, HttpServletRequest request) {
		String loginId = (String)request.getSession().getAttribute("loginId");
		
		String imagePath = Constants.IMAGE_PATH;

		File dir = new File(imagePath);
		if (!dir.exists()) {
			// 폴더가 존재하지 않으면 폴더 생성
			dir.mkdirs();
		}

		// MultipartRequest(request, 저장경로[, 최대허용크기, 인코딩케릭터셋, 동일한 파일명 보호 여부])
		MultipartRequest mr = null;
		
		String litStorageId = null;
		String name = null;
		String genre = null;
		File image = null;
		String introduce = null;
		
		try {
			mr = new MultipartRequest(request, imagePath, 5 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
		
			litStorageId = mr.getParameter("litStorageId");
			name = mr.getParameter("name");
			genre = mr.getParameter("selectGenre");
			image = mr.getFile("image");
			introduce = mr.getParameter("introduce");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Literature literature = new Literature();
		
		Member creator = new Member();
		creator.setId(loginId);
		
		LitStorage litStorage = new LitStorage();
		litStorage.setId(litStorageId);
		
		literature.setName(name);
		literature.setGenre(genre);
		if(image == null) {
			literature.setImagePath(Constants.DEFAULT_IMAGE);
		} else {
			try {
				literature.setImagePath(image.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		literature.setIntroduce(introduce);
		literature.setCreator(creator);
		literature.setHits(0);
		literature.setLitStorage(litStorage);
		
		if(!lService.registerLiterature(literature)) {
			throw new RuntimeException("literature register failed");
		}
		
		litStorage = lsService.findLitStorageById(litStorageId);
		
		boolean onGroupFlag = false;
		
		for(Member m : litStorage.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("litStorage", litStorage);
		
		return "literatureList";
	}
	
	@RequestMapping(value="delete.do", method=RequestMethod.GET)
	public String deleteLiterature(String literatureId, Model model, HttpSession session) {
		// 1. recevice LiteratureId to episodeList.jsp
		// 2. delete literature
		String loginId = (String)session.getAttribute("loginId");
		
		Literature literature = lService.findLiteratureById(literatureId);
		
		Member creator = new Member();
		creator.setId(loginId);
		
		literature.setCreator(creator);
		
		if(!lService.removeLiterature(literatureId)) {
			throw new RuntimeException("literature remove failed");
		}
		
		LitStorage litStorage = lsService.findLitStorageById(literature.getLitStorage().getId());
		
		boolean onGroupFlag = false;
		
		for(Member m : litStorage.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("litStorage", litStorage);

		return "literatureList";
	}
	
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public String showLiteratureList(String id, Model model, HttpSession session) {
		// 전체 작품 저장소 및 나의 작품 저장소 LitstorageId 가져오기
		// 작품 리스트로 이동
		String loginId = (String)session.getAttribute("loginId");

		LitStorage litStorage = lsService.findLitStorageById(id);
		
		boolean onGroupFlag = false;
		
		for(Member m : litStorage.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("litStorage", litStorage);
		
		return "literatureList";
	}
	
	@RequestMapping(value="allList.do", method=RequestMethod.GET)
	public String showLiteratureAllList(String id, Model model, HttpSession session) {
		List<Board> bList = boardService.findTitles();
		
		if (bList.size() == 0) {
			return "redirect:literatureAllList";
		}
		
		List<Literature> newList = lService.findLiteraturesByGenreOrderById(bList.get(0).getTitle());
		model.addAttribute("literatures", newList);
		
		List<Board> bListRemoved = new ArrayList<>();

		/* 장르 dropdownlist를 만들기 위해 게시판 이름을 싹 보내줌(신고 빼고) */
		for (Board b : bList) {
			if (!b.getTitle().equals("신고")) {
				bListRemoved.add(b);
			}
		}

		model.addAttribute("genreList", bListRemoved);

		return "literatureAllList";
	}
	
	@RequestMapping(value="search.do", method=RequestMethod.GET)
	public String showLiteratureSearchForm(String type, String keyword, Model model, HttpSession session) {
		//검색 타입과 검색어를 가져옴
		List<Literature> list = new ArrayList<>();
		
		//검색 타입 비교해서 검색결과 list 생성
		if (type.equals("id")) {
			list = lService.findLiteraturesByMemberId(keyword);
		} else {
			list = lService.findLiteratureByName(keyword);
		}
		
		//request에 list 담아서 literatureList.jsp로 보내줌
		model.addAttribute("literatures", list);
		
		List<Board> bList = boardService.findAllBoards();
		
		List<Board> bListRemoved = new ArrayList<>();

		/* 장르 dropdownlist를 만들기 위해 게시판 이름을 싹 보내줌(신고 빼고) */
		for (Board b : bList) {
			if (!b.getTitle().equals("신고")) {
				bListRemoved.add(b);
			}
		}

		model.addAttribute("genreList", bListRemoved);
		
		return "literatureAllList";
	}
	
	@RequestMapping(value="search.do", method=RequestMethod.POST)
	public void searchLiterature(String type, String keyword, Model model, HttpSession session, HttpServletResponse response) {
		response.setContentType("text/xml;charset=utf-8");
		OutputStream out;
		
		try {
			out = response.getOutputStream();

			JAXBContext context;
	
			List<Literature> list = new ArrayList<>();
			if (type.equals("id")) {
				list = lService.findLiteraturesByMemberId(keyword);
			} else {
				list = lService.findLiteratureByName(keyword);
			}
			// send memberSearchResult as list to memberInviteSearch.jsp using AJAX
			try {
				context = JAXBContext.newInstance(Wrapper.class, Literature.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				QName qname = new QName("literatures");
				Wrapper<Literature> wrapper = new Wrapper<>(list);
				JAXBElement<Wrapper> element = new JAXBElement<Wrapper>(qname, Wrapper.class, wrapper);
				m.marshal(element, out);
			} catch (JAXBException e) {
				e.printStackTrace();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@RequestMapping(value="image.do", method=RequestMethod.GET)
	public void searchLiterature(String literatureId, Model model, HttpServletResponse response) {
		Literature literature = lService.findLiteratureById(literatureId);
		
		File image = new File(literature.getImagePath());
		
		if(!image.exists()) {
			throw new RuntimeException("no literature image");
		}
		
		InputStream in = null;
		OutputStream out = null;
		
		try {
			in = new BufferedInputStream(new FileInputStream(image));
			out = response.getOutputStream();
			
			byte[] buf = new byte[8096];
			int readByte = 0;
			while((readByte = in.read(buf)) > -1) {
				out.write(buf, 0, readByte);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AutoCloser.close(in,out);
	}
	
	@RequestMapping(value="genreList.do", method=RequestMethod.GET)
	public void showGenreList(String type, String genre, String from, Model model, HttpServletResponse response) {
		response.setContentType("text/xml;charset=utf-8");
		OutputStream out;
		
		try {
			out = response.getOutputStream();

			JAXBContext context;
	
			List<Literature> list = new ArrayList<>();
			if (!from.equals("main")) {
				if (type.equals("newGenre")) {
					list = lService.findLiteraturesByGenreOrderById(genre);
				} else {
					list = lService.findLiteraturesByGenreOrderByHits(genre);
				}
			} else {
				if (type.equals("newGenre")) {
					list = lService.findLiteraturesByGenreOrderByIdForMain(genre);
				} else {
					list = lService.findLiteraturesByGenreOrderByHitsForMain(genre);
				}
			}
			
			try {
				context = JAXBContext.newInstance(Wrapper.class, Literature.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				QName qname = new QName("literatures");
				Wrapper<Literature> wrapper = new Wrapper<>(list);
				JAXBElement<Wrapper> element = new JAXBElement<Wrapper>(qname, Wrapper.class, wrapper);
				m.marshal(element, out);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
