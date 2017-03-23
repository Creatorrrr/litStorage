package controller.discussionPlace;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.facade.DiscussionPlaceService;
import service.logic.DiscussionPlaceServiceLogic;


@WebServlet("/discussionContent/delete.do")
public class DiscussionContentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");//discussion place id
		String cid = request.getParameter("cid");//discussion content id
		DiscussionPlaceService service = new DiscussionPlaceServiceLogic();
		boolean check = service.removeDiscussionContent(cid);
		
		//if(check){
		//	response.sendRedirect(request.getContextPath()+"/discussionPlace/list.do");
		//}else{
			response.sendRedirect(request.getContextPath()+"/discussionPlace/detail.do?id="+pid);
		//}
		
		
	}



}
