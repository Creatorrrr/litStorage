package controller.literature;

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

@WebServlet("/literature/list.do")
public class LiteratureListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 전체 작품 저장소 및 나의 작품 저장소 LitstorageId 가져오기
		// 작품 리스트로 이동
		LitStorageService service = new LitStorageServiceLogic();

		String litStorageId = request.getParameter("id");
		String loginId = (String)request.getSession().getAttribute("loginId");

		LitStorage litStorage = service.findLitStorageById(litStorageId);
		
		boolean onGroupFlag = false;
		
		for(Member m : litStorage.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		request.setAttribute("onGroup", onGroupFlag);	// set user is on group or not
		
		request.setAttribute("litStorage", litStorage);
		
		request.getRequestDispatcher("/views/literatureList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
