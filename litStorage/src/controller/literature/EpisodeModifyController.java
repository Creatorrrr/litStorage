package controller.literature;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Episode;
import domain.Literature;
import domain.Member;
import service.facade.LiteratureService;
import service.facade.MemberService;
import service.logic.LiteratureServiceLogic;
import service.logic.MemberServiceLogic;

@WebServlet("/episode/modify.do")
public class EpisodeModifyController extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	
	private LiteratureService Lservice;
	private MemberService Mservice;
	
	public EpisodeModifyController() {
		Lservice = new LiteratureServiceLogic();
		Mservice = new MemberServiceLogic();
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
		String episdoeId = request.getParameter("modifyEpisodeId");
		String literatureId = request.getParameter("modifyLiteratureId");
		String Title = request.getParameter("modifyEpisodeTitle");
		String Contents = request.getParameter("modifyEpisodeContents");
		
		Episode episode = new Episode();
		episode.setId(episdoeId);
		episode.setTitle(Title);
		episode.setContent(Contents);
		
		Literature lit = Lservice.findLiteratureById(literatureId);
		
		episode.setLiterature(lit);
		
		Member writer = new Member();
		writer.setId((String)request.getSession().getAttribute("loginId"));
		
		episode.setWriter(writer);
		
		if(!Lservice.modifyEpisode(episode)) {
			throw new RuntimeException("episode modify failed");
		}
		
		List<Episode> episodes = Lservice.findEpisodeByLiteratureId(literatureId);
		Literature literature = Lservice.findLiteratureById(literatureId);
		
		request.setAttribute("literature", literature);
		request.setAttribute("episodes", episodes);
		
		request.getRequestDispatcher("/views/episodeList.jsp").forward(request, response);
	}

}
