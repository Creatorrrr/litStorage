package controller.literature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;

@WebServlet("/literature/delete.do")
public class LiteratureDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. recevice LiteratureId to episodeList.jsp
		// 2. delete literature
		
		LiteratureService service = new LiteratureServiceLogic();
		
		String deleteLiteratureId = request.getParameter("deleteLiteratureId");
		
		boolean check = service.removeLiterature(deleteLiteratureId);
		System.out.println(request.getContextPath()+"/litStorage/allList.do");
		
		response.sendRedirect(request.getContextPath()+"/episode/do");
		System.out.println(request.getContextPath()+"/litStorage/allList.do");
		request.getRequestDispatcher("/allList.do").forward(request, response);
	}

}
