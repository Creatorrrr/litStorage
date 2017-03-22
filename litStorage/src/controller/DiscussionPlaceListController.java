package controller;

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
		//???login
		DiscussionPlaceService service = new DiscussionPlaceServiceLogic();
		String litStorageId = "";//?????litStorage
		List<DiscussionPlace> list=service.findDiscussionPlacesByLitStorageId(litStorageId);
		request.setAttribute("discussionPlaces", list);
		request.getRequestDispatcher("/views/discussionPlaceList.jsp").forward(request, response);
		
	}



	
}
