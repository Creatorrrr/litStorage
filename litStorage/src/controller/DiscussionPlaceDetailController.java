package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DiscussionPlace;
import service.facade.DiscussionPlaceService;
import service.logic.DiscussionPlaceServiceLogic;


@WebServlet("/discussionPlace/detail.do")
public class DiscussionPlaceDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		DiscussionPlaceService service = new DiscussionPlaceServiceLogic();
		DiscussionPlace discussionPlace = service.findDiscussionPlaceById(id);
		
		request.setAttribute("discussionPlace", discussionPlace);
		request.getRequestDispatcher("/views/discussionPlaceDetail.jsp").forward(request, response);
		
	}



}
