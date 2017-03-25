package controller.literature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import service.facade.LitStorageService;
import service.logic.LitStorageServiceLogic;

@WebServlet("/literature/list.do")
public class LiteratureLIstController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 전체 작품 저장소 및 나의 작품 저장소 LitstorageId 가져오기
		// 작품 리스트로 이동
		LitStorageService service = new LitStorageServiceLogic();

		String litstorageId = request.getParameter("id");

		LitStorage litStorage = service.findLitStorageById(litstorageId);
		
		request.setAttribute("litStorage", litStorage);

		request.getRequestDispatcher("/views/literatureList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
