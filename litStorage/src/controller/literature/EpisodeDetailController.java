package controller.literature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Episode;
import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/detail.do")
public class EpisodeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LiteratureService service = new LiteratureServiceLogic();
		
		String episodeId = "2"; 
//		String episodeId = request.getParameter("episodeId");
		Episode episode = service.findEpisodeById(episodeId);
		
		request.setAttribute("episode", episode);
		
		request.getRequestDispatcher("../views/episodeDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String delete = request.getParameter("deleteEpisode");
		System.out.println(delete);
	}

}
