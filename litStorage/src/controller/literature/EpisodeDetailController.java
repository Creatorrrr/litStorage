package controller.literature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Episode;
import domain.Member;
import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/detail.do")
public class EpisodeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LiteratureService service = new LiteratureServiceLogic();

		String episodeId = request.getParameter("episodeId");
		String loginId = (String)request.getSession().getAttribute("loginId");

		Episode episode = service.findEpisodeById(episodeId);
		
		boolean onGroupFlag = false;
		
		for(Member m : episode.getLiterature().getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		request.setAttribute("onGroup", onGroupFlag);	// set user is on group or not

		request.setAttribute("episode", episode);

		request.getRequestDispatcher("/views/episodeDetail.jsp").forward(request, response);
	}

}
