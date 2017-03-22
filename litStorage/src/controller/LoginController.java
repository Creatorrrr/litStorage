package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
		
		if("1234".equals(password)){
			HttpSession session = request.getSession();
			session.setAttribute("loginId", loginId);
			response.sendRedirect(request.getContextPath()+"/views/index.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/views/index.jsp");
		}

	/*	session.setAttribute("loginedUser", loginId); 
		session.setAttribute("isAdmin", isAdminUser(loginId));
		
		response.sendRedirect(request.getContextPath()+"main.do");*/
	}
	/*private boolean isAdminUser(String loginId){
		List<String> adminUsers=new ArrayList<String>();
		adminUsers.add("test");
		
		return adminUsers.contains(loginId);
	}*/
}
