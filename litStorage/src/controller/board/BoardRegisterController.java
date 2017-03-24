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

@WebServlet("/board/boardRegister.do")
public class BoardRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String boardName = request.getParameter("boardName");     // boardName
		
		BoardService service = new BoardServiceLogic();
		
		Board board = new Board();
		
		if(boardName != null && !boardName.isEmpty()) {
			board.setTitle(boardName);
			
			if(!service.registerBoard(board)) {
				throw new RuntimeException("failed to create board");
			}
		} else {
			board.setId("1");
		}
		
		List<Board> boardList = service.findAllBoards();
		List<Post> postList = service.findPostsByBoardId(board.getId());
		
		request.setAttribute("boards", boardList);
		request.setAttribute("posts", postList);
		
		request.getRequestDispatcher("/views/postList.jsp").forward(request, response);
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//	
//		String boardName = request.getParameter("boardName");
//		//String boardId=request.getParameter("boardId");
//		
//		BoardService service = new BoardServiceLogic();
//		
//		Board board = new Board();
//		board.setTitle(boardName);
//	//	board.setId(boardId);
//		
//		List<Board> list = service.findAllBoards();
//		
//		if (!service.registerBoard(board)) { // if the registration failed
//			throw new RuntimeException("board register failed");
//		}
//		
//		request.setAttribute("boardRegister",list);
//		request.getRequestDispatcher("/views/postList.jsp").forward(request, response);
//	}

}
