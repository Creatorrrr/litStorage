package controller.discussionPlace;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DiscussionPlace;
import domain.LitStorage;
import domain.Member;
import service.facade.DiscussionPlaceService;
import service.logic.DiscussionPlaceServiceLogic;


@WebServlet("/discussionPlace/register.do")
public class DiscussionPlaceRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		String litStorageId = request.getParameter("litStorageId");
		DiscussionPlaceService service = new DiscussionPlaceServiceLogic();
		
		DiscussionPlace d = new DiscussionPlace();
		d.setTitle(title);
		
		Member member= new Member();
		member.setId((String)request.getSession().getAttribute("loginId"));
		d.setCreator(member);
		
		LitStorage l= new LitStorage();
		l.setId(litStorageId);
		//l.setId("333");
		d.setLitStorage(l);

		boolean check = service.registerDiscussionPlace(d);
			response.sendRedirect(request.getContextPath() + "/discussionPlace/list.do?litStorageId="+litStorageId);



		
		
	}

}
