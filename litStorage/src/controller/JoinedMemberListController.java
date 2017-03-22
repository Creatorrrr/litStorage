package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import service.facade.LitStorageService;
import service.logic.LitStorageServiceLogic;

@WebServlet("/litStorage/memberList.do")
public class JoinedMemberListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LitStorageService service = new LitStorageServiceLogic();
		
		/*if we call this memberlist method then method type is get and must contain
		 * parameter named "id", which represent litStorage */
		LitStorage ls = service.findLitStorageById(request.getParameter("id"));
		
		request.setAttribute("litStorage", ls);
		
		request.getRequestDispatcher("/views/joinedMemberList.jsp").forward(request, response);
	}

}
