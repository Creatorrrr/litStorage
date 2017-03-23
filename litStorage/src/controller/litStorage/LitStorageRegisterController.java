package controller.litStorage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import domain.Member;
import service.facade.LitStorageService;
import service.logic.LitStorageServiceLogic;

@WebServlet("/litStorage/register.do")
public class LitStorageRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect(request.getContextPath()+"/views/litStorageRegister.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LitStorageService service = new LitStorageServiceLogic();
		
		String name = request.getParameter("name");
		String introduce = request.getParameter("introduce");
		
		if(name == null || name.isEmpty()) {
			throw new RuntimeException("Please write LitStorage's name");
		}
		
		LitStorage litStorage = new LitStorage();
		
		Member creator = new Member();
		creator.setId((String)request.getSession().getAttribute("loginId"));	// Must be logined
//		creator.setId((String)request.getParameter("userId"));
		litStorage.setCreator(creator);
		litStorage.setName(name);
		litStorage.setIntroduce(introduce);
		
		if(!service.registerLitStorage(litStorage)) {
			throw new RuntimeException("LitStorage register failed");
		}
		// go to controller for loading LitStorage info
		response.sendRedirect(request.getContextPath() + "/litStorage/myList.do");
	}

}
