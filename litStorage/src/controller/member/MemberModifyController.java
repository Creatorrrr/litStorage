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

@WebServlet("/member/modify.do")
public class MemberModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService service;
	
	public MemberModifyController() {
		service = new MemberServiceLogic();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		System.out.println(loginId);
		MemberService service = new MemberServiceLogic();
		Member memberDB = service.findMemberById(loginId);
		memberDB.setPassword("");
		
		request.setAttribute("member", memberDB);
		request.getRequestDispatcher("/views/memberModify.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		Member member = new Member();
		member.setId(loginId);
		member.setName(name);
		member.setPassword(password);
		member.setEmail(email);
		
		boolean check = service.modifyMember(member);
		if(check){
			request.setAttribute("member", member);
			response.sendRedirect(request.getContextPath() + "/member/detail.do");
		}else{
			request.setAttribute("message", "회원정보 수정 되지 않았습니다.");
			request.getRequestDispatcher("/member/modify.do").forward(request, response);
		}
		
		
		
		
	}

}
