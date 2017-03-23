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
		
		request.getRequestDispatcher("../views/episodeModify.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String episdoeId = request.getParameter("modifyEpisodeId");
		String literatureId = request.getParameter("modifyLiteratureId");
		String Genre = request.getParameter("modifySelectGenre");
		String Title = request.getParameter("modifyEpisodeTitle");
		String Contents = request.getParameter("modifyEpisodeContents");
		
		Episode targetEpisode =  Lservice.findEpisodeById(episdoeId);
		
		Member member = Mservice.findMemberById(targetEpisode.getWriter().getId());
		
		Episode episode = new Episode();
		Literature literature = new Literature();
		
		literature.setGenre(Genre);
		literature.setId(literatureId);
		episode.setId(episdoeId);
		
		episode.setLiterature(literature);
		
		episode.setTitle(Title);
		episode.setContent(Contents);
		episode.setWriter(member);
		
		
		boolean check = Lservice.modifyEpisode(episode);
		
		if(check){
			response.sendRedirect(request.getContextPath()+"/epsode/detail.do");
		}
		
		
	}

}
