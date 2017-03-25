package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DiscussionPlace;
import service.facade.BoardService;
import service.facade.DiscussionPlaceService;
import service.logic.BoardServiceLogic;
import service.logic.DiscussionPlaceServiceLogic;

@WebServlet("/boardDelete.do")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService service = new BoardServiceLogic();
		
		String boardId = request.getParameter("boardId");
		
		if(!service.removeBoard(boardId)) {
			throw new RuntimeException("board remove failed");
		}
		
		response.sendRedirect(request.getContextPath() + "/post/postList.do");
	}

}
