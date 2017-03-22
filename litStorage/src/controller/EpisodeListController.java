package controller;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1. Show the contents and artist name.
		// 2. Show list of serials.
		
		LiteratureService service = new LiteratureServiceLogic();
		
//		String id = request.getParameter("LiteratureId");
		String name = "물";
		
		//find Literature By Name
		List<Literature> literature = service.findLiteratureByName(name);
		request.setAttribute("literatures", literature);
		
		request.getRequestDispatcher("../views/episodeList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//select(writerName,writerId,MemberName)
		//search contents
		//select(openLitstorage,openAll)
		String selectContents = request.getParameter("selectContents");
		String searchContents = request.getParameter("searchContents");
		String openSelect = request.getParameter("openSelect");
		System.out.println(openSelect);
		
		
	}

}
