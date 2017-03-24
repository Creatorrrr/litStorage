	package controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;


@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.sendRedirect(req.getContextPath()+"/views/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		
		MemberService service = new MemberServiceLogic();
		Member memberDB = service.findMemberById(loginId);
		

		if(memberDB != null && password.equals(memberDB.getPassword()) ){
			HttpSession session = request.getSession();
			session.setAttribute("loginId", loginId);
			if(loginId == "admin"){
				session.setAttribute("isAdmin", true);
			}
			request.setAttribute("message", "로그인 되었습니다.");
//			request.getRequestDispatcher("/").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/");
			return ;
		}else {
			request.setAttribute("message", "로그인 실패 하였습니다.");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return ;
		}

	}

}
