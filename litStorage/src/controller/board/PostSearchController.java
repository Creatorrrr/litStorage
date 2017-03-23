package controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Post;
import service.facade.BoardService;
import service.logic.BoardServiceLogic;

@WebServlet("/post/searchList.do")
public class PostSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService service = new BoardServiceLogic();
		String postName = request.getParameter("postName");
		String postContent=request.getParameter("postContent");
		String hashtag=request.getParameter("hashtag");
		
//		List<Post> list = service.findPostsByBoardId();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// select(postName,postContent,hashmap)
		// search contents
		String select = request.getParameter("select");
		String searchContents = request.getParameter("searchContents");

	}
}
