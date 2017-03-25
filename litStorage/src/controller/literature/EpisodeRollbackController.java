package controller.literature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ChangeHistory;
import domain.Episode;
import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/rollback.do")
public class EpisodeRollbackController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LiteratureService service;
	
	public EpisodeRollbackController() {
		service = new LiteratureServiceLogic();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String historyId = request.getParameter("historyId");
		
		ChangeHistory history = service.findChangeHistoryById(historyId);
		Episode episode = history.getEpisode();
		
		episode.setContent(history.getContentFromGit());
		
		if(!service.modifyEpisode(episode)) {
			throw new RuntimeException("episode rollback failed");
		}

		request.setAttribute("episode", episode);

		request.getRequestDispatcher("/views/episodeDetail.jsp").forward(request, response);
	}
}
