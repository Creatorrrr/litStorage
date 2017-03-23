package controller.literature;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Episode;
import service.facade.LiteratureService;
import service.logic.LitStorageServiceLogic;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/delete.do")
public class EpisodeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. recevice episodeId to episodeDetail.jsp
		// 2. delete episode
		
		LiteratureService service = new LiteratureServiceLogic();
		
		String episodeId = request.getParameter("episodeId");
		
		Episode episode = service.findEpisodeById(episodeId);
		
		boolean check = service.removeEpisode(episode);
		
		if(check){
			response.sendRedirect(request.getContextPath()+"/cc/cc");
		}
	}

}
