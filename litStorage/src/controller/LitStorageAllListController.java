package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.facade.LitStorageService;
import service.logic.LitStorageServiceLogic;

@WebServlet("/litStorage/allList.do")
public class LitStorageAllListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LitStorageService service = new LitStorageServiceLogic();
		
		request.setAttribute("litStorages", service.findAll());
		
		request.getRequestDispatcher("/views/litStorageList.jsp").forward(request, response);
	}

}
