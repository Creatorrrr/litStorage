package controller.literature;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Episode;
import domain.Literature;
import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/bound.do")
public class EpisodeBoundController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String episodeId = request.getParameter("episodeId");
		String bound = request.getParameter("bound");
		
		LiteratureService service = new LiteratureServiceLogic();
		
		Episode episode = service.findEpisodeById(episodeId);
		
		episode.setBound(bound);
		
		if(!service.modifyEpisode(episode)) {
			throw new RuntimeException("episode modify failed");
		}
		
		Literature literature = service.findLiteratureById(episode.getLiterature().getId());
		
		request.setAttribute("literature", literature);
		request.getRequestDispatcher("/views/episodeList.jsp").forward(request, response);
	}

}
