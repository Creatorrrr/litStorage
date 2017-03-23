package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Post;
import service.facade.BoardService;
import service.logic.BoardServiceLogic;

@WebServlet("/post/postDetail.do")
public class PostDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardService service = new BoardServiceLogic();
	//	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		String postId = request.getParameter("id");
	
		request.setAttribute("post",service.findPostById(postId));
		request.getRequestDispatcher("/views/postDetail.jsp").forward(request, response);
	//	response.sendRedirect(request.getContextPath()+"/views/postDetail.jsp");
	
	}

}
