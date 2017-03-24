package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;


@WebServlet("/member/delete.do")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 탈퇴 기능 구현 
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		if(loginId == null || loginId.isEmpty()){
			//error
		}else{
			MemberService service = new MemberServiceLogic();
			boolean check= service.removeMember(loginId);
			if(check){
				//response.sendRedirect(request.getContextPath() + "/logout.do");
				request.setAttribute("message", "탈퇴 처리가 되었습니다.");
				request.getRequestDispatcher("/views/memberDetail.jsp").forward(request, response);
			}
			
		}
		
	}


}
