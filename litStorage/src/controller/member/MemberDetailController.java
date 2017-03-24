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

@WebServlet("/member/detail.do")
public class MemberDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		System.out.println(loginId);
		MemberService service = new MemberServiceLogic();
		Member memberDB = service.findMemberById(loginId);
		memberDB.setPassword("");
		
		request.setAttribute("member", memberDB);
		request.getRequestDispatcher("/views/memberDetail.jsp").forward(request, response);
	}

}
