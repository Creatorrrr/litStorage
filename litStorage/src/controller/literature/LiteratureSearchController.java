package controller.literature;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import domain.Board;
import domain.LitStorage;
import domain.Literature;
import domain.wrapper.Wrapper;
import service.facade.BoardService;
import service.facade.LitStorageService;
import service.facade.LiteratureService;
import service.logic.BoardServiceLogic;
import service.logic.LitStorageServiceLogic;
import service.logic.LiteratureServiceLogic;

@WebServlet("/literature/search.do")
public class LiteratureSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LiteratureService service = new LiteratureServiceLogic();
		//검색 타입과 검색어를 가져옴
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		List<Literature> list = new ArrayList<>();
		//검색 타입 비교해서 검색결과 list 생성
		if (type.equals("id")) {
			list = service.findLiteraturesByMemberId(keyword);
		} else {
			list = service.findLiteratureByName(keyword);
		}
		//request에 list 담아서 literatureList.jsp로 보내줌
		request.setAttribute("literatures", list);
		
		BoardService bService = new BoardServiceLogic();
		List<Board> bList = bService.findAllBoards();
		
		List<Board> bListRemoved = new ArrayList<>();

		/* 장르 dropdownlist를 만들기 위해 게시판 이름을 싹 보내줌(신고 빼고) */
		for (Board b : bList) {
			if (!b.getTitle().equals("신고")) {
				bListRemoved.add(b);
			}
		}

		request.setAttribute("genreList", bListRemoved);
		
		request.getRequestDispatcher("/views/literatureAllList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		OutputStream out = response.getOutputStream();

		JAXBContext context;
		
		LiteratureService service = new LiteratureServiceLogic();

		// check option whether selected condition is id or name
		String type = request.getParameter("type");
		// get search keyword
		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
		List<Literature> list = new ArrayList<>();
		if (type.equals("id")) {
			list = service.findLiteraturesByMemberId(keyword);
		} else {
			list = service.findLiteratureByName(keyword);
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
	}

}
