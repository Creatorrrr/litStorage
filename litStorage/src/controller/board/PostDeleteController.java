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

@WebServlet("/post/delete.do")
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardService service = new BoardServiceLogic();
		
		String postId = request.getParameter("postId");
		
		Post post = service.findPostById(postId);
		
		if(!service.removePost(postId)) {
			throw new RuntimeException("post remove failed");
		}
		
		List<Board> boardList = service.findAllBoards();
		
		List<Post> postList = service.findPostsByBoardId(post.getBoard().getId());

		request.setAttribute("boards", boardList);
		request.setAttribute("posts", postList);
		request.setAttribute("boardId", post.getBoard().getId());
		
		request.getRequestDispatcher("/views/postList.jsp").forward(request, response);
	}

}
