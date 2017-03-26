package controller.literature;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Episode;
import domain.Literature;
import domain.Member;
import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/bound.do")
public class EpisodeBoundController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String episodeId = request.getParameter("episodeId");
		String bound = request.getParameter("bound");
		String loginId = (String)request.getSession().getAttribute("loginId");
		
		LiteratureService service = new LiteratureServiceLogic();
		
		Episode episode = service.findEpisodeById(episodeId);
		
		episode.setBound(bound);
		
		if(!service.modifyEpisode(episode)) {
			throw new RuntimeException("episode modify failed");
		}
		
		Literature literature = service.findLiteratureById(episode.getLiterature().getId());
		
		boolean onGroupFlag = false;
		
		for(Member m : literature.getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		request.setAttribute("onGroup", onGroupFlag);	// set user is on group or not
		
		request.setAttribute("literature", literature);
		request.getRequestDispatcher("/views/episodeList.jsp").forward(request, response);
	}

}
