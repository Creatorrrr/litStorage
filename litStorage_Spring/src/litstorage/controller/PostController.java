package litstorage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import litstorage.constants.Constants;
import litstorage.domain.Board;
import litstorage.domain.Member;
import litstorage.domain.Post;
import litstorage.service.facade.BoardService;

@Controller
@RequestMapping("post")
public class PostController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="register.do", method=RequestMethod.GET)
	public String showPostRegisterForm(String boardId, Model model) {
		model.addAttribute("boardId", boardId);
		return "postRegister";
	}
	
	@RequestMapping(value="register.do", method=RequestMethod.POST)
	public String registerPost(String postTitle, String postContent, String hashtag, String boardId, Model model, HttpSession session) {
		String writerId=(String)session.getAttribute("loginId"); // Must be logined
		
		Post post = new Post();

		post.setTitle(postTitle);
		post.setContent(postContent);
		post.setHashTag(hashtag);
		
		Member writer = new Member();
		writer.setId(writerId);
		
		post.setWriter(writer);
		
		Board board = new Board();
		board.setId(boardId);
		post.setBoard(board);
		
		if (!boardService.registerPost(post)) { // if the registration failed
			throw new RuntimeException("post register failed");
		}
		
		model.addAttribute("post", boardService.findPostById(post.getId()));
		return "postDetail";
	}
	
	@RequestMapping(value="detail.do", method=RequestMethod.GET)
	public String showPostDetail(String postId, Model model) {		
		Post post = boardService.findPostById(postId);

		model.addAttribute("post", post);
		
		return "postDetail";
	}
	
	@RequestMapping(value="modify.do", method=RequestMethod.GET)
	public String showPostModifyForm(String postId, Model model) {
		Post post = boardService.findPostById(postId);
		
		model.addAttribute("post", post);
		
		return "postModify";
	}
	
	@RequestMapping(value="modify.do", method=RequestMethod.POST)
	public String modifyPost(String postId, String title, String content, String hashtag, Model model) {
		Post post = boardService.findPostById(postId);
		post.setTitle(title);
		post.setContent(content);
		post.setHashTag(hashtag);
		
		if(!boardService.modifyPost(post)) {
			throw new RuntimeException("post modify failed");
		}
		
		model.addAttribute("post", post);
		
		return "postDetail";
	}
	
	@RequestMapping(value="delete.do", method=RequestMethod.GET)
	public String deletePost(String postId, Model model) {
		Post post = boardService.findPostById(postId);
		
		if(!boardService.removePost(postId)) {
			throw new RuntimeException("post remove failed");
		}
		
		List<Board> boardList = boardService.findTitles();
		
		List<Post> postList = boardService.findPostsByBoardId(post.getBoard().getId());

		model.addAttribute("boards", boardList);
		model.addAttribute("posts", postList);
		model.addAttribute("boardId", post.getBoard().getId());
		
		return "postList";
	}
	
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public String showPostList(String boardId, String pageNum, Model model) {
		if(pageNum == null || pageNum.isEmpty()) {
			pageNum = "1";
		}
		
		List<Board> boardList = boardService.findTitles();
		List<Post> postList = null;

		if(boardId == null) {
			if(boardList.size() > 0) {
				boardId = boardList.get(0).getId();
			}
		}
		
		String rows = null;
		if(boardId != null) {
			postList = boardService.findPostsByBoardIdWithPage(boardId, pageNum);
			rows = boardService.findRowsByBoardId(boardId);
		}
		
		model.addAttribute("boards", boardList);
		model.addAttribute("posts", postList);
		model.addAttribute("boardId", boardId);
		model.addAttribute("rows", rows);
		
		return "postList";
	}
	
	@RequestMapping(value="searchList.do", method=RequestMethod.POST)
	public String showPostSearchList(String boardId, String select, String searchside, Model model) {
		List<Board> boardList = boardService.findTitles();
		List<Post> postList = null;
		
		switch(select) {
		case Constants.POST_TITLE:
			postList = boardService.findPostsByTitle(searchside, boardId);
			break;
		case Constants.POST_CONTENT:
			postList = boardService.findPostsByContent(searchside, boardId);
			break;
		case Constants.POST_HASHTAG:
			postList = boardService.findPostsByHashTag(searchside, boardId);
			break;
		}

		model.addAttribute("boards", boardList);
		model.addAttribute("posts", postList);
		model.addAttribute("boardId", boardId);
		
		return "postList";
	}
}
