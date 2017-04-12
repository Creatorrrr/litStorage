package litstorage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import litstorage.domain.Board;
import litstorage.domain.Post;
import litstorage.service.facade.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="register.do", method=RequestMethod.POST)
	public String registerBoard(String boardName, Model model) {		
		Board board = new Board();
		
		board.setTitle(boardName);
			
		if(!boardService.registerBoard(board)) {
			throw new RuntimeException("failed to create board");
		}
		
		List<Board> boardList = boardService.findTitles();
		List<Post> postList = boardService.findPostsByBoardId(board.getId());
		
		model.addAttribute("boards", boardList);
		model.addAttribute("posts", postList);
		model.addAttribute("boardId", board.getId());
		
		return "postList";
	}
	
	@RequestMapping(value="delete.do", method=RequestMethod.GET)
	public String deleteBoard(String boardId, Model model) {
		if(!boardService.removeBoard(boardId)) {
			throw new RuntimeException("board remove failed");
		}
		
		List<Board> boardList = boardService.findTitles();
		List<Post> postList = null;
		
		if(boardList.size() > 0) {
			boardId = boardList.get(0).getId();
			postList = boardService.findPostsByBoardId(boardId);
		} else {
			boardId = null;
		}
		
		model.addAttribute("boards", boardList);
		model.addAttribute("posts", postList);
		model.addAttribute("boardId", boardId);
		
		return "postList";
	}
}
