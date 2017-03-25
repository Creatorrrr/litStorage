package controller.literature;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import domain.Literature;
import service.facade.LitStorageService;
import service.facade.LiteratureService;
import service.logic.LitStorageServiceLogic;
import service.logic.LiteratureServiceLogic;

@WebServlet("/literature/list.do")
public class LiteratureLIstController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 전체 작품 저장소 및 나의 작품 저장소 LitstorageId 가져오기
		// 작품 리스트로 이동
		LiteratureService service = new LiteratureServiceLogic();
		LitStorageService lsService = new LitStorageServiceLogic();
		String litstorageId = request.getParameter("id");
		
		System.out.println(litstorageId);
		List<Literature> literatures = service.findLiteraturesByLitStorageId(litstorageId);
		System.out.println(literatures);
		Literature literature = new Literature();
		LitStorage litStorage = new LitStorage();
		litStorage = lsService.findLitStorageById(litstorageId);
		literature.setLitStorage(litStorage);
		
		
		request.setAttribute("litStorage", litStorage);
		request.setAttribute("literature", literature);
		request.setAttribute("literatures", literatures);

		request.getRequestDispatcher("/views/literatureList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
