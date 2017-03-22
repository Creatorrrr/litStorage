package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/discussionPlace/register.do")
public class DiscussionPlaceRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");

//			(토론장 생성에 성공한 경우)
//				5. 입력한 제목으로 토론장을 생성한다.
//				6. 생성한 토론장 내용 화면을 표시한다.
//			(토론장 생성에 실패한 경우)
//				5. 토론장 생성 오류 메시지를 표시한다

		
		
	}

}
