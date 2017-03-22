package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Episode;
import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/register.do")
public class EpisodeRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.show episodeRegister.jsp Screen
		// 2.insert Genre, literatureName, literatureContents

		LiteratureService service = new LiteratureServiceLogic();

		String selectGenre = request.getParameter("selectGenre");
		String epiName = request.getParameter("literatureName");
		String literatureContents = request.getParameter("literatureContents");
		
		Episode episode = new Episode();
		
//		episode.setTitle(literatureName);
		
//		service.registerEpisode(episode)

	}

}
