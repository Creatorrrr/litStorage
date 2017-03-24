package controller.literature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Literature;
import service.facade.LiteratureService;
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

		String id = request.getParameter("LiteratureId");
		String LiteratureName = request.getParameter("LiteratureName");

		List<Literature> literatures = new ArrayList<>();

		// find Literature By Name
		if (LiteratureName != null) {

			literatures = service.findLiteratureByName(LiteratureName);

		} else if (id != null) {

			Literature literatuer = service.findLiteratureById(id);

			literatures.add(literatuer);
		}
		request.setAttribute("literatures", literatures);

		request.getRequestDispatcher("../views/episodeList.jsp").forward(request, response);

	}

}
