package controller.literature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Literature;
import domain.Member;
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

		String literatureId = request.getParameter("literatureId");
		String loginId = (String)request.getSession().getAttribute("loginId");
		
		Literature literature = service.findLiteratureById(literatureId);
		
		service.increaseLiteratureHitByLiteratureId(literatureId);
		
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
