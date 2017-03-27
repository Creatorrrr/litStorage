package controller.discussionPlace;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DiscussionPlace;
import domain.LitStorage;
import domain.Member;
import service.facade.DiscussionPlaceService;
import service.facade.LitStorageService;
import service.logic.DiscussionPlaceServiceLogic;
import service.logic.LitStorageServiceLogic;



@WebServlet("/discussionPlace/list.do")
public class DiscussionPlaceListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DiscussionPlaceService dpService = new DiscussionPlaceServiceLogic();
		LitStorageService lsService = new LitStorageServiceLogic();
		String loginId = (String)request.getSession().getAttribute("loginId");
		
		String litStorageId = request.getParameter("litStorageId");
		List<DiscussionPlace> list = dpService.findDiscussionPlacesByLitStorageId(litStorageId);
		request.setAttribute("discussionPlaces", list);
		
		//sideNav를 위해 litStorage 재포함
		LitStorage ls = lsService.findLitStorageById(litStorageId);
		
		boolean onGroupFlag = false;
		
		for(Member m : ls.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		request.setAttribute("onGroup", onGroupFlag);	// set user is on group or not
		request.setAttribute("litStorage", ls);
		
		request.getRequestDispatcher("/views/discussionPlaceList.jsp").forward(request, response);
		
	}



	
}
