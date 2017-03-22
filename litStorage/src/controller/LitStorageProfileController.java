package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.facade.LitStorageService;
import service.logic.LitStorageServiceLogic;

/**
 * Servlet implementation class LitStorageProfileController
 */
@WebServlet("/litStorage/profile.do")
public class LitStorageProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		LitStorageService service = new LitStorageServiceLogic();
		
	}

}
