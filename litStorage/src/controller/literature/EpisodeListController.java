package controller.literature;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Episode;
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

		String literatureId = request.getParameter("LiteratureId");

		List<Episode> episodes = service.findEpisodeByLiteratureId(literatureId);
		Literature literature = service.findLiteratureById(literatureId);
		
		request.setAttribute("literature", literature);
		request.setAttribute("episodes", episodes);

		request.getRequestDispatcher("/views/episodeList.jsp").forward(request, response);

	}

}
