package controller.literature;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/episode/register.do")
public class EpisodeRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LiteratureService Lservice;
	private MemberService Mservice;
	
	public EpisodeRegisterController() {
		Lservice = new LiteratureServiceLogic();
		Mservice = new MemberServiceLogic();
				
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. receive LiteratureId
		
		String LiteratureId = req.getParameter("literatureId"); 
		System.out.println(LiteratureId+"aaaaa");
		req.setAttribute("LiteratureId", LiteratureId);
		
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
		Literature literature = Lservice.findLiteratureById(LiteratureId);
		
		literature.setId(LiteratureId);
		literature.setGenre(selectGenre);
		episode.setLiterature(literature);
		
		episode.setTitle(episodeName);
		
		episode.setContent(episodeContents);
		Lservice.findLiteratureById(LiteratureId);
		
		//episode writer
		Member writer = Mservice.findMemberById(literature.getCreator().getId());
		episode.setWriter(writer);
		
		//private on register Member
		episode.setBound("M");
		
		boolean check = service.registerEpisode(episode);
		
		if(check){
			List<Literature> literatures = new ArrayList<>();
			
			Literature literatuer = service.findLiteratureById(LiteratureId);

			literatures.add(literatuer);
			
			request.setAttribute("literatures", literatures);

			request.getRequestDispatcher("../views/episodeList.jsp").forward(request, response);
		}

	}

}
