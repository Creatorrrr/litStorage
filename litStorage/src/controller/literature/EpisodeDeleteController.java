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

@WebServlet("/episode/delete.do")
public class EpisodeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. recevice episodeId to episodeDetail.jsp
		// 2. delete episode
		String loginId = (String)request.getSession().getAttribute("loginId");

		LiteratureService Lservice = new LiteratureServiceLogic();
		
		String episodeId = request.getParameter("episodeId");
		
		Episode episode = Lservice.findEpisodeById(episodeId);
		
		Lservice.removeEpisode(episodeId);
		
		// 전 연재글 목록으로 이동
		Literature literature = Lservice.findLiteratureById(episode.getLiterature().getId());
		
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
