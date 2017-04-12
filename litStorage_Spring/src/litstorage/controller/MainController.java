package litstorage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import litstorage.domain.Board;
import litstorage.domain.Literature;
import litstorage.service.facade.BoardService;
import litstorage.service.facade.LiteratureService;

@Controller
public class MainController {
	
	@Autowired
	private LiteratureService lService;
	@Autowired
	private BoardService bService;
	
	@RequestMapping(value="main.do", method=RequestMethod.GET)
	public String showMain(Model model) {
		List<Board> bList = bService.findTitles();
		
		// 게시판 제목(장르) 중 첫번째 항목 장르별로 조회수순, 신작순으로 가져옴.
		// 만약 생성된 게시판이 없으면 바로 index.jsp로 리다이렉트
		if (bList.size() == 0) {
			return "main";
		}
		
		List<Literature> recoCutList = lService.findLiteraturesByGenreOrderByHitsForMain(bList.get(0).getTitle());
		List<Literature> newCutList = lService.findLiteraturesByGenreOrderByIdForMain(bList.get(0).getTitle());
		
		model.addAttribute("recoLiteratures", recoCutList);
		model.addAttribute("newLiteratures", newCutList);
		
		List<Board> bListRemoved = new ArrayList<>();

		/* 장르 dropdownlist를 만들기 위해 게시판 이름을 싹 보내줌(신고 빼고) */
		for (Board b : bList) {
			if (!b.getTitle().equals("신고")) {
				bListRemoved.add(b);
			}
		}
		
		model.addAttribute("genreList", bListRemoved);

		return "main";
	}

}
