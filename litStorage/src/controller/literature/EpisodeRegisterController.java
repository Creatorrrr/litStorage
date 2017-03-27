package controller.literature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.Constants;
import domain.Episode;
import domain.Literature;
import domain.Member;
import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/register.do")
public class EpisodeRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LiteratureService Lservice;
	
	public EpisodeRegisterController() {
		Lservice = new LiteratureServiceLogic();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. receive LiteratureId
		String loginId = (String)req.getSession().getAttribute("loginId");
		String literatureId = req.getParameter("literatureId"); 
		
		Literature literature = Lservice.findLiteratureById(literatureId);
		
		boolean onGroupFlag = false;
		
		for(Member m : literature.getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		req.setAttribute("onGroup", onGroupFlag);	// set user is on group or not
		req.setAttribute("literature", literature);
		req.setAttribute("literatureId", literatureId);
		
		req.getRequestDispatcher("/views/episodeRegister.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.show episodeRegister.jsp Screen
		// 2.insert Genre, literatureName, literatureContents

		LiteratureService service = new LiteratureServiceLogic();

		String loginId = (String)request.getSession().getAttribute("loginId");
		String literatureId = request.getParameter("literatureId");
		String episodeName = request.getParameter("episodeName");
		String episodeContents = request.getParameter("episodeContents");
		Episode episode = new Episode();
		episode.setTitle(episodeName);
		episode.setContent(episodeContents);
		// Genre literature
		Literature literature = Lservice.findLiteratureById(literatureId);
		
		episode.setLiterature(literature);
		
		//episode writer
		Member writer = new Member();
		writer.setId((String)request.getSession().getAttribute("loginId"));
		
		episode.setWriter(writer);
		
		//private on register Member
		episode.setBound(Constants.BOUND_MEMBER);
		
		if(!service.registerEpisode(episode)) {
			throw new RuntimeException("fail to register episode");
		}
		
		Literature registeredLiterature = service.findLiteratureById(literatureId);
		
		boolean onGroupFlag = false;
		
		for(Member m : literature.getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		request.setAttribute("onGroup", onGroupFlag);	// set user is on group or not
		
		request.setAttribute("literature", registeredLiterature);
		
		request.getRequestDispatcher("/views/episodeList.jsp").forward(request, response);
	}
}
