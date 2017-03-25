package controller.literature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import domain.Literature;
import domain.Member;
import service.facade.LitStorageService;
import service.facade.LiteratureService;
import service.facade.MemberService;
import service.logic.LitStorageServiceLogic;
import service.logic.LiteratureServiceLogic;
import service.logic.MemberServiceLogic;

@WebServlet("/literature/register.do")
public class LiteratureRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String litStorageId = request.getParameter("litStorageId");
		System.out.println(litStorageId);
		request.setAttribute("litStorageId", litStorageId);

		request.getRequestDispatcher("/views/literatureRegister.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 작품 저장소 아이디를 작품

		LiteratureService Lservice = new LiteratureServiceLogic();
		LitStorageService LSservice = new LitStorageServiceLogic();
		
		MemberService Mservice = new MemberServiceLogic();
		
		String literatureName = request.getParameter("inputName");
		String literatureGenre = request.getParameter("selectGenre");
		String imagePath = request.getParameter("imagePath");
		String literatureIntroduce = request.getParameter("inputIntroduce");

		String loginId = (String) request.getSession().getAttribute("loginId");
		Member creator = Mservice.findMemberById(loginId);

		int hits = 0;

		String litStorageId = request.getParameter("litStorageId");
		LitStorage litstorageId = LSservice.findLitStorageById(litStorageId);
		Literature literature = new Literature();
		System.out.println(litStorageId);
		literature.setName(literatureName);
		literature.setGenre(literatureGenre);
		literature.setImagePath(imagePath);
		literature.setIntroduce(literatureIntroduce);
		literature.setCreator(creator);
		literature.setHits(hits);
		literature.setLitStorage(litstorageId);

		boolean check = Lservice.registerLiterature(literature);
		
		LitStorage litStorage = LSservice.findLitStorageById(litStorageId);
		
		request.setAttribute("litStorage", litStorage);

		request.getRequestDispatcher("/views/literatureList.jsp").forward(request, response);

	}

}
