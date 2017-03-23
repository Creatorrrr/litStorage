package controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.ibatis.reflection.SystemMetaObject;

import domain.Board;
import domain.Member;
import domain.Post;

import service.facade.BoardService;
import service.logic.BoardServiceLogic;

@WebServlet("/postRegister.do")
public class PostRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String boardId = req.getParameter("boardId");
		req.setAttribute("boardId", boardId);
		req.getRequestDispatcher("/views/postRegister.jsp").forward(req, resp);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("postContent");
		String hashtag = request.getParameter("hashtag");
		String boardId=request.getParameter("boardId");
		
		Board board = new Board();
		Post post = new Post();

		BoardService service = new BoardServiceLogic();

		String writerId=(String)request.getSession().getAttribute("loginId"); // Must be logined
		Member writer = new Member();
		
		writer.setId(writerId);
		post.setTitle(postTitle);
		post.setContent(postContent);
		post.setHashTag(hashtag);
		post.setWriter(writer);
		board.setId(boardId);
		post.setBoard(board);
		
		if (!service.registerPost(post)) { // if the registration failed
			throw new RuntimeException("post register failed");
		}
		
	//	System.out.println(post.getId()+"/aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	//	List<Post>list = service.findPostsByWriterId(writerId);
	//	response.sendRedirect(request.getContextPath()+"/post/postDetail.do");
		
		request.setAttribute("post",service.findPostById(post.getId()));
		request.getRequestDispatcher("/views/postDetail.jsp").forward(request, response);


	}

}
