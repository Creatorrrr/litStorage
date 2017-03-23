package controller.discussionPlace;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DiscussionPlace;
import service.facade.DiscussionPlaceService;
import service.logic.DiscussionPlaceServiceLogic;


@WebServlet("/discussionPlace/search.do")
public class DiscussionPlaceSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		List<DiscussionPlace> list = null;
		
		DiscussionPlaceService service = new DiscussionPlaceServiceLogic();
		
		if("title".equals(searchType)){
			list = service.findDiscussionPlacesByName(searchText);
		}else if("userId".equals(searchType)){
			list = service.findDiscussionPlacesByMemberId(searchText);
		}
		
		request.setAttribute("discussionPlaces", list);
		request.getRequestDispatcher("/views/discussionPlaceList.jsp").forward(request, response);
		
		
	}



}
