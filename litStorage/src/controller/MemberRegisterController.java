package controller;

import java.io.IOException;

import javax.print.ServiceUIFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;


@WebServlet("/join.do")
public class MemberRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/views/memberRegister.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		MemberService service = new MemberServiceLogic();

		Member member = new Member();
		
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
	//	member.setPassword(request.getParameter("pwd2"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		
		service.registerMember(member);

		String id = request.getParameter("id");
		String pwd = request.getParameter("password");
	//	String pwd2 = request.getParameter("pwd2");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		

		response.sendRedirect(request.getContextPath()+"/views/main.jsp");
	
//		HttpSession session = request.getSession(); 
//		session.setAttribute("login", id);
//		session.setAttribute("login", pwd);
//		session.setAttribute("login", pwd2);
//		session.setAttribute("login", name);
//		session.setAttribute("login", email);      濡쒓렇�씤�뿉�꽌留� �븘�슂
		
		
		//response.sendRedirect("/views/main.jsp");
	}

	
}
