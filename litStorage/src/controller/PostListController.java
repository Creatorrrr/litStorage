package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.facade.BoardService;
import service.logic.BoardServiceLogic;

@WebServlet("/post/postList.do")           //    /post/postList.do   /board/freeBoard.do
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardService service = new BoardServiceLogic();
		
		request.setAttribute("boards", service.findAllBoards());
		request.getRequestDispatcher("/views/postList.jsp").forward(request, response);// /views/boardList.jsp
	
	}

}
