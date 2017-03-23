package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.reflection.SystemMetaObject;

import domain.Board;
import domain.Post;

import service.facade.BoardService;
import service.logic.BoardServiceLogic;


@WebServlet("/postRegister.do")
public class PostRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String lectureName = request.getParameter("lectureName");
//		String instructorName= request.getParameter("instructorName");
//		String lectureIntroduce =request.getParameter("lectureIntroduce");
//		
//		Lecture lecture = new Lecture(lectureName, instructorName, lectureIntroduce);
//		
//		LectureService service = new LectureServiceLogic();
//		
//		service.register(lecture);
//		
//		response.sendRedirect("list.do");
		
		
		String postTitle = request.getParameter("postTitle");
		String postContent= request.getParameter("postContent");
		String hashtag =request.getParameter("hashtag");
		
		Board board = new Board();
		Post post = new Post(); 
		
		BoardService service = new BoardServiceLogic();
		
//		post.setId((String)request.getSession().getAttribute("id"));  // Must be logined
		
		post.setTitle(postTitle);
		post.setContent(postContent);
		post.setHashTag(hashtag);
		
		board.setTitle(postTitle);
		System.out.println("1111111"+board.getTitle());
		
		
		if(!service.registerPost(post)) {        //if the registration failed
			throw new RuntimeException("post register failed");
		}
		
//		Post post = new Post(PostName, PostContent, HashTag);
//		
//		PostService service = new PostServiceLogic();
//		
		service.registerPost(post);
		service.registerBoard(board);
		
		
		
		response.sendRedirect(request.getContextPath()+"/board/freeBoard.do");
	
	
	}

}
