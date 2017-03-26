package controller.literature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import domain.Literature;
import domain.Member;
import service.facade.LitStorageService;
import service.facade.LiteratureService;
import service.logic.LitStorageServiceLogic;
import service.logic.LiteratureServiceLogic;

@WebServlet("/literature/delete.do")
public class LiteratureDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. recevice LiteratureId to episodeList.jsp
		// 2. delete literature

		LiteratureService lService = new LiteratureServiceLogic();
		LitStorageService lsService = new LitStorageServiceLogic();

		String literatureId = request.getParameter("literatureId");
		String loginId = (String)request.getSession().getAttribute("loginId");
		
		Literature literature = lService.findLiteratureById(literatureId);
		
		Member creator = new Member();
		creator.setId((String)request.getSession().getAttribute("loginId"));
		
		literature.setCreator(creator);
		
		if(!lService.removeLiterature(literatureId)) {
			throw new RuntimeException("literature remove failed");
		}
		
		LitStorage litStorage = lsService.findLitStorageById(literature.getLitStorage().getId());
		
		boolean onGroupFlag = false;
		
		for(Member m : litStorage.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		request.setAttribute("onGroup", onGroupFlag);	// set user is on group or not
		
		request.setAttribute("litStorage", litStorage);

		request.getRequestDispatcher("/views/literatureList.jsp").forward(request, response);
	}
}
