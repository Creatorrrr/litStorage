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
import service.facade.DiscussionPlaceService;
import service.logic.DiscussionPlaceServiceLogic;



@WebServlet("/discussionPlace/list.do")
public class DiscussionPlaceListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DiscussionPlaceService service = new DiscussionPlaceServiceLogic();
		String litStorageId = request.getParameter("litStorageId");
		List<DiscussionPlace> list=service.findDiscussionPlacesByLitStorageId(litStorageId);
		request.setAttribute("discussionPlaces", list);
		
		//sideNav를 위해 litStorage 재포함
		LitStorage ls = new LitStorage();
		ls.setId(litStorageId);
		request.setAttribute("litStorage", ls);
		
		request.getRequestDispatcher("/views/discussionPlaceList.jsp").forward(request, response);
		
	}



	
}
