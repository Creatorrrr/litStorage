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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.show episodeRegister.jsp Screen
		// 2.insert Genre, literatureName, literatureContents

		LiteratureService service = new LiteratureServiceLogic();

		String selectGenre = request.getParameter("selectGenre");
		String episodeName = request.getParameter("episodeName");
		String episodeContents = request.getParameter("episodeContents");
		
		System.out.println(selectGenre);
		System.out.println(episodeName);
		System.out.println(episodeContents);
		
		Episode episode = new Episode();
		// Genre literature
		Literature literature = new Literature();
		literature.setGenre(selectGenre);
		episode.setLiterature(literature);
		episode.setTitle(episodeName);
		episode.setContent(episodeContents);
		
		boolean check = service.registerEpisode(episode);
		
		if(check){
			response.sendRedirect(request.getContextPath()+"../epsode/list.do");
		}

	}

}
