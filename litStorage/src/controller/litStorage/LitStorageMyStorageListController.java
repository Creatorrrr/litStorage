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

@WebServlet("/litStorage/myList.do")
public class LitStorageMyStorageListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LitStorageService service = new LitStorageServiceLogic();
		//get loginId from session
		String id = (String)request.getSession().getAttribute("loginId");
			
		if(id == null){
			// if not logined, send to loginPage
			response.sendRedirect(request.getContextPath()+"/views/login.jsp");
		}else{
		//find LitStorage by login Id
		List<LitStorage> list = service.findLitStoragesByMemberId(id);
		request.setAttribute("litStorages", list);
		
		request.getRequestDispatcher("/views/litStorageMyStorageList.jsp").forward(request, response);
		}
	}

}
