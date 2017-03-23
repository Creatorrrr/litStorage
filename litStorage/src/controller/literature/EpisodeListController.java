package controller.literature;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import domain.Literature;
import service.facade.LitStorageService;
import service.facade.LiteratureService;
import service.logic.LitStorageServiceLogic;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/list.do")
public class EpisodeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LiteratureService service;

	public EpisodeListController() {
		service = new LiteratureServiceLogic();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Show the contents and artist name.
		// 2. Show list of serials.

		// String id = request.getParameter("LiteratureId");
		String name = "ë¶?";

		// find Literature By Name
		List<Literature> literature = service.findLiteratureByName(name);
		request.setAttribute("literatures", literature);

		request.getRequestDispatcher("../views/episodeList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. select(writerName,writerId,MemberName)
		// 2. search contents
		// 3. select(openLitstorage,openAll)
		String selectContents = request.getParameter("selectContents");
		String searchContents = request.getParameter("searchContents");
		String openSelect = request.getParameter("openSelect");

//		if (selectContents.equals("writerName")) {
//
//		} else if (searchContents.equals("writerId")) {
//
//		} else if (searchContents.equals("MemberName")) {
//
//		}

	}

}
