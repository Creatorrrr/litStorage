package controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import service.facade.BoardService;
import service.logic.BoardServiceLogic;

@WebServlet("/board/boardRegister.do")
public class BoardRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String boardId = request.getParameter("boards");     //boardId
		
		BoardService service = new BoardServiceLogic();
		List<Board> list=service.findAllBoards();
		request.setAttribute("boards", list);
		
		
		//request.setAttribute("boards", boardId);
		request.getRequestDispatcher("/views/boardRegister.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String boardName = request.getParameter("boardName");
		//String boardId=request.getParameter("boardId");
		
		BoardService service = new BoardServiceLogic();
		
		Board board = new Board();
		board.setTitle(boardName);
	//	board.setId(boardId);
		
		List<Board> list = service.findAllBoards();
		
		if (!service.registerBoard(board)) { // if the registration failed
			throw new RuntimeException("board register failed");
		}
		
		request.setAttribute("boardRegister",list);
		request.getRequestDispatcher("/views/postList.jsp").forward(request, response);

		
	}

}
