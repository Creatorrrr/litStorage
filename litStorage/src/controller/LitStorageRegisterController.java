package controller;

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LitStorageService service = new LitStorageServiceLogic();
		
		String name = request.getParameter("name");
		String introduce = request.getParameter("introduce");
		
		if(name == null || name.isEmpty()) {
			throw new RuntimeException("작품저장소의 이름을 입력해주세요");	// validate() 필요
		}
		
		LitStorage litStorage = new LitStorage();
		
		Member creator = new Member();
//		creator.setId((String)request.getSession().getAttribute("userId"));	// 세선에 넣을 Attribute이름을 정해야 함
		creator.setId("dd");	// 테스트용 임시ID
		
		litStorage.setCreator(creator);
		litStorage.setName(name);
		litStorage.setIntroduce(introduce);
		
		if(!service.registerLitStorage(litStorage)) {
			throw new RuntimeException("작품저장소 생성 실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/views/litStorageMyStorageList.jsp");
	}

}
