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

@WebServlet("/episode/modify.do")
public class EpisodeModifyController extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	
	private LiteratureService Lservice;
	//private MemberService Mservice;
	
	public EpisodeModifyController() {
		Lservice = new LiteratureServiceLogic();
		//Mservice = new MemberServiceLogic();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1. before episodeDetail.jsp 
		// 2. recevice episodeId
		// 3. show modify detail
		
		String episodeId = request.getParameter("episodeId");
		Episode episode = Lservice.findEpisodeById(episodeId);
		request.setAttribute("episode", episode);
		
		request.getRequestDispatcher("/views/episodeModify.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String episodeId = request.getParameter("episodeId");
		String title = request.getParameter("episodeTitle");
		String content = request.getParameter("episodeContent");
		String boundSelect = request.getParameter("openSelect"); 
		
		Episode episode = Lservice.findEpisodeById(episodeId);
		episode.setTitle(title);
		episode.setContent(content);
		episode.setBound(boundSelect);
		Member writer = new Member();
		writer.setId((String)request.getSession().getAttribute("loginId"));
		
		episode.setWriter(writer);
		
		if(!Lservice.modifyEpisode(episode)) {
			throw new RuntimeException("episode modify failed");
		}
		
		Literature literature = Lservice.findLiteratureById(episode.getLiterature().getId());
		
		request.setAttribute("literature", literature);
		
		request.getRequestDispatcher("/views/episodeList.jsp").forward(request, response);
	}

}
