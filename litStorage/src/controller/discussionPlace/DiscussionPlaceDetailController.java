package controller.discussionPlace;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DiscussionPlace;
import domain.LitStorage;
import service.facade.DiscussionPlaceService;
import service.facade.LitStorageService;
import service.logic.DiscussionPlaceServiceLogic;
import service.logic.LitStorageServiceLogic;


@WebServlet("/discussionPlace/detail.do")
public class DiscussionPlaceDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String litStorageId = request.getParameter("litStorageId");
		
		LitStorageService lsService = new LitStorageServiceLogic();
		DiscussionPlaceService service = new DiscussionPlaceServiceLogic();
		DiscussionPlace discussionPlace = service.findDiscussionPlaceById(id);
		
		//sideNav를 위해 litStorage 재포함
		LitStorage ls = lsService.findLitStorageById(litStorageId);
		request.setAttribute("litStorage", ls);
		
		request.setAttribute("discussionPlace", discussionPlace);
		request.getRequestDispatcher("/views/discussionPlaceDetail.jsp").forward(request, response);
		
	}



}
