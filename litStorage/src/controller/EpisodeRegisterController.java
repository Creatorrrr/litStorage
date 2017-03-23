package controller;

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

@WebServlet("/episode/register.do")
public class EpisodeRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. receive LiteratureId
		
		String LiteratureId = req.getParameter("literatureId"); 
		req.setAttribute("LiteratureId", LiteratureId);
		System.out.println(LiteratureId);
		
		req.getRequestDispatcher("../views/episodeRegister.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.show episodeRegister.jsp Screen
		// 2.insert Genre, literatureName, literatureContents

		LiteratureService service = new LiteratureServiceLogic();

		String LiteratureId = request.getParameter("literatureId");
		String selectGenre = request.getParameter("selectGenre");
		String episodeName = request.getParameter("episodeName");
		String episodeContents = request.getParameter("episodeContents");
		
		
		Episode episode = new Episode();
		// Genre literature
		Literature literature = new Literature();
		literature.setGenre(selectGenre);
		literature.setId(LiteratureId);
		episode.setLiterature(literature);
		episode.setTitle(episodeName);
		episode.setContent(episodeContents);
		
		boolean check = service.registerEpisode(episode);
		
		if(check){
			response.sendRedirect(request.getContextPath()+"/episode/list.do");
		}

	}

}
