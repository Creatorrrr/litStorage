package controller.literature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import domain.Literature;
import domain.MemberLitStorage;
import service.facade.LitStorageService;
import service.facade.LiteratureService;
import service.logic.LitStorageServiceLogic;
import service.logic.LiteratureServiceLogic;

@WebServlet("/literature/delete.do")
public class LiteratureDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. recevice LiteratureId to episodeList.jsp
		// 2. delete literature
		
		LiteratureService Lservice = new LiteratureServiceLogic();
		LitStorageService LSservice = new LitStorageServiceLogic();
		
		String deleteLiteratureId = request.getParameter("deleteLiteratureId");
		System.out.println(deleteLiteratureId);
		Literature literature = Lservice.findLiteratureById(deleteLiteratureId);
		
//		boolean check = Lservice.removeLiterature(deleteLiteratureId);
		//전 작품저장소 화면 이동 
		
		String loginedId = (String)request.getSession().getAttribute("loginId");
		System.out.println(loginedId);
		
		List<MemberLitStorage> memberLitstorage = LSservice.findMemberLitStoragesByMemberId(loginedId);
		
		List<LitStorage> litstorage = new ArrayList<>();
		
		for(MemberLitStorage memberlitstorage : memberLitstorage){
			 litstorage = LSservice.findLitStoragesByMemberId(memberlitstorage.getMember().getId());
			
		}
		
		request.setAttribute("litstorages", litstorage);
		
		request.getRequestDispatcher("../views/litStorageMyStorageList.jsp").forward(request, response);;
//		response.sendRedirect(request.getContextPath()+"/episode/do");
	}

}
