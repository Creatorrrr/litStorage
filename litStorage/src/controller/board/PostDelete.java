package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.facade.BoardService;
import service.logic.BoardServiceLogic;

@WebServlet("/postDelete.do")
public class PostDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		BoardService service = new BoardServiceLogic();
		String id = request.getParameter("id");
		//service.removeBoard(id);
		service.removePost(id);
		response.sendRedirect(request.getContextPath()+"/views/postList.jsp");
	}

}
