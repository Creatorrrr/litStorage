package controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.Post;
import service.facade.BoardService;
import service.logic.BoardServiceLogic;

@WebServlet("/post/list.do") // /post/postList.do /board/freeBoard.do
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardService service = new BoardServiceLogic();
		
		String boardId = request.getParameter("boardId");
		
		List<Board> boardList = service.findAllBoards();
		List<Post> postList = null;

		if(boardId == null) {
			if(boardList.size() > 0) {
				boardId = boardList.get(0).getId();
			}
		}
		
		if(boardId != null) {
			postList = service.findPostsByBoardId(boardId);
		}
		
		request.setAttribute("boards", boardList);
		request.setAttribute("posts", postList);
		request.setAttribute("boardId", boardId);
		
		request.getRequestDispatcher("/views/postList.jsp").forward(request, response);
	}
	
}
