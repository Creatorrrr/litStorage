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

@WebServlet("/post/postList.do") // /post/postList.do /board/freeBoard.do
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardService service = new BoardServiceLogic();

		/*
		 * String post = request.getParameter("post");
		 * List<Post>list = service.findPostsByBoardId(post);
		 * String board = request.getParameter("board");
		 * Post po = service.findPostById(post);
		 * request.setAttribute("posts", list); //5줄 수정부분
		 */
		
		//여기서 보드 아이디 추가할지 
		
		List<Board> lists = service.findAllBoards();
		request.setAttribute("boards", lists);
		// request.setAttribute("boards", service.findAllBoards());
		request.getRequestDispatcher("/views/postList.jsp").forward(request, response);// /views/boardList.jsp

	}

	
//	//검색용
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		BoardService service = new BoardServiceLogic();
//		String name = request.getParameter("search");
//		List<Post>list=service.findPostsByTitle(name);
//		
//		request.setAttribute("boards", list);
//		request.getRequestDispatcher("/views/postList.jsp").forward(request, response);
//	}
}
