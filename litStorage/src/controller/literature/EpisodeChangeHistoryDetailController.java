package controller.literature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/changeHistoryDetail.do")
public class EpisodeChangeHistoryDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LiteratureService service;
	
	public EpisodeChangeHistoryDetailController() {
		service = new LiteratureServiceLogic();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String historyId = request.getParameter("historyId");
		
		request.setAttribute("history", service.findChangeHistoryById(historyId));
		
		request.getRequestDispatcher("/views/changeHistoryDetail.jsp").forward(request, response);
	}
}
