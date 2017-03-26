package controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.Constants;
import domain.Board;
import domain.Post;
import service.facade.BoardService;
import service.logic.BoardServiceLogic;

@WebServlet("/post/searchList.do")
public class PostSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardService service = new BoardServiceLogic();
		
		String boardId = request.getParameter("boardId");
		String select = request.getParameter("select");
		String search = request.getParameter("searchside");
		
		List<Board> boardList = service.findAllBoards();
		List<Post> postList = null;
		
		switch(select) {
		case Constants.POST_TITLE:
			postList = service.findPostsByTitle(search, boardId);
			break;
		case Constants.POST_CONTENT:
			postList = service.findPostsByContent(search, boardId);
			break;
		case Constants.POST_HASHTAG:
			postList = service.findPostsByHashTag(search, boardId);
			break;
		}

		request.setAttribute("boards", boardList);
		request.setAttribute("posts", postList);
		request.setAttribute("boardId", boardId);
		
		request.getRequestDispatcher("/views/postList.jsp").forward(request, response);
	}
}
