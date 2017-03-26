package controller.litStorage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import service.facade.LitStorageService;
import service.logic.LitStorageServiceLogic;

@WebServlet("/litStorage/allList.do")
public class LitStorageAllListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null || pageNum.isEmpty()) {
			pageNum = "1";
		}
		
		LitStorageService service = new LitStorageServiceLogic();
		
		List<LitStorage> litStorageList = service.findAllWithPage(pageNum);
		String rows = service.findRows();
		
		request.setAttribute("litStorages", litStorageList);
		request.setAttribute("rows", rows);
		
		request.getRequestDispatcher("/views/litStorageList.jsp").forward(request, response);
	}

}
