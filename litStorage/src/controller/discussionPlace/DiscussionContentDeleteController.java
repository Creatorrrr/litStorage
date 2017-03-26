package controller.discussionPlace;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.facade.DiscussionPlaceService;
import service.logic.DiscussionPlaceServiceLogic;


@WebServlet("/discussionContent/delete.do")
public class DiscussionContentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		if(!loginId.isEmpty()){//로그인 했을때

			//삭제
			String pid = request.getParameter("pid");//discussion place id
			String cid = request.getParameter("cid");//discussion content id
			DiscussionPlaceService service = new DiscussionPlaceServiceLogic();
			boolean check = service.removeDiscussionContent(cid);
			if(check){
				request.setAttribute("message", "삭제 되었습니다.");
			}else{
				request.setAttribute("message", "실패 했습니다.");
			}
			request.getRequestDispatcher("/discussionPlace/detail.do?id="+pid).forward(request, response);
				
		}else{
			request.setAttribute("message", "정상적인 접근이 아닙니다.");
			request.getRequestDispatcher("/").forward(request, response);
		}
		
		

		
		
	}



}
