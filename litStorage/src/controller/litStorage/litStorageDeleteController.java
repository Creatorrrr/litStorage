package controller.litStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import domain.MemberLitStorage;
import service.facade.LitStorageService;
import service.logic.LitStorageServiceLogic;

@WebServlet("/litStorage/delete.do")
public class litStorageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String litStorageId = request.getParameter("litStorageId");
		String loginId = (String)request.getSession().getAttribute("loginId");
		
		LitStorageService service = new LitStorageServiceLogic();
		
		if(!service.removeLitStorage(litStorageId)) {
			throw new RuntimeException("litStorage remove failed");
		}
		
		//find MemberLitStorage by login Id
		List<LitStorage> lsList = new ArrayList<>();

		List<MemberLitStorage> mlsList = service.findMemberLitStoragesByMemberId(loginId);
		
		// rearrange List<LitStorage> from List<MemberLitStorag>e
		for(MemberLitStorage mls : mlsList) {
			lsList.add(mls.getLitStorage());
		}
		
		request.setAttribute("litStorages", lsList);
		
		request.getRequestDispatcher("/views/litStorageMyStorageList.jsp").forward(request, response);
	}

}
