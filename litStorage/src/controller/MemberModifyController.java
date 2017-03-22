package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;

@WebServlet("/modify.do")
public class MemberModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService service;
	public MemberModifyController() {
		service = new MemberServiceLogic();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
//		Member member = service.modifyMember(member.getId());
//		request.setAttribute("member", member);
		request.getRequestDispatcher("memberModify.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
