package controller.discussionPlace;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DiscussionContent;
import domain.DiscussionPlace;
import domain.LitStorage;
import domain.Member;
import service.facade.DiscussionPlaceService;
import service.facade.LitStorageService;
import service.logic.DiscussionPlaceServiceLogic;
import service.logic.LitStorageServiceLogic;


@WebServlet("/discussionContent/register.do")
public class DiscussionContentRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String content = request.getParameter("discussionContentText");
		String discussionPlaceId = request.getParameter("discussionPlaceId");
		String litStorageId = request.getParameter("litStorageId");
		
		DiscussionPlaceService service = new DiscussionPlaceServiceLogic();

		DiscussionContent discussionContent = new DiscussionContent();
		discussionContent.setContent(content);
		
		Member member = new Member();
		member.setId((String)request.getSession().getAttribute("loginId"));
		discussionContent.setWriter(member);
		
		DiscussionPlace discussionPlace = new DiscussionPlace();
		discussionPlace.setId(discussionPlaceId);
		discussionContent.setDiscussionPlace(discussionPlace);
		boolean check = service.registerDiscussionContent(discussionContent);
		
		//if(check ){
		response.sendRedirect(request.getContextPath()+"/discussionPlace/detail.do?id="+discussionPlaceId+"&litStorageId="+litStorageId);
		//}
		
	}

}
