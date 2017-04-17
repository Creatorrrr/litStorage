package litstorage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import litstorage.constants.Constants;
import litstorage.domain.ChangeHistory;
import litstorage.domain.Episode;
import litstorage.domain.Literature;
import litstorage.domain.Member;
import litstorage.service.facade.LiteratureService;

@Controller
@RequestMapping("episode")
public class EpisodeController {

	@Autowired
	private LiteratureService lService;
	
	@RequestMapping(value="register.do", method=RequestMethod.GET)
	public String showEpisodeRegisterForm(String literatureId, Model model, HttpSession session) {
		// 1. receive LiteratureId
		String loginId = (String)session.getAttribute("loginId");
		
		Literature literature = lService.findLiteratureById(literatureId);
		
		boolean onGroupFlag = false;
		
		for(Member m : literature.getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("literature", literature);
		model.addAttribute("literatureId", literatureId);
		
		return "episodeRegister";
	}
	
	@RequestMapping(value="register.do", method=RequestMethod.POST)
	public String registerEpisode(String literatureId, String episodeName, String episodeContents, Model model, HttpSession session) {
		// 1.show episodeRegister.jsp Screen
		// 2.insert Genre, literatureName, literatureContents
		String loginId = (String)session.getAttribute("loginId");
		
		Episode episode = new Episode();
		episode.setTitle(episodeName);
		episode.setContent(episodeContents);
		// Genre literature
		Literature literature = lService.findLiteratureById(literatureId);
		
		episode.setLiterature(literature);
		
		//episode writer
		Member writer = new Member();
		writer.setId(loginId);
		
		episode.setWriter(writer);
		
		//private on register Member
		episode.setBound(Constants.BOUND_MEMBER);
		
		if(!lService.registerEpisode(episode)) {
			throw new RuntimeException("fail to register episode");
		}
		
		Literature registeredLiterature = lService.findLiteratureById(literatureId);
		
		boolean onGroupFlag = false;
		
		for(Member m : literature.getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("literature", registeredLiterature);
		
		return "episodeList";
	}
	
	@RequestMapping(value="detail.do", method=RequestMethod.GET)
	public String showEpisodeDetail(String episodeId, Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");

		Episode episode = lService.findEpisodeById(episodeId);
		
		boolean onGroupFlag = false;
		
		for(Member m : episode.getLiterature().getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("episode", episode);

		return "episodeDetail";
	}
	
	@RequestMapping(value="modify.do", method=RequestMethod.GET)
	public String showModifyEpisodeForm(String episodeId, Model model, HttpSession session) {
		// 1. before episodeDetail.jsp 
		// 2. recevice episodeId
		// 3. show modify detail
		String loginId = (String)session.getAttribute("loginId");
		
		Episode episode = lService.findEpisodeById(episodeId);
		
		boolean onGroupFlag = false;
		
		for(Member m : episode.getLiterature().getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("episode", episode);
		
		return "episodeModify";
	}
	
	@RequestMapping(value="modify.do", method=RequestMethod.POST)
	public String modifyEpisode(String episodeId, String episodeTitle, String episodeContent, String openSelect, Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		Episode episode = lService.findEpisodeById(episodeId);
		episode.setTitle(episodeTitle);
		episode.setContent(episodeContent);
		episode.setBound(openSelect);
		Member writer = new Member();
		writer.setId(loginId);
		
		episode.setWriter(writer);
		
		if(!lService.modifyEpisode(episode)) {
			throw new RuntimeException("episode modify failed");
		}
		
		Literature literature = lService.findLiteratureById(episode.getLiterature().getId());
		
		boolean onGroupFlag = false;
		
		for(Member m : literature.getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("literature", literature);
		
		return "episodeList";
	}
	
	@RequestMapping(value="delete.do", method=RequestMethod.GET)
	public String deleteEpisode(String episodeId, Model model, HttpSession session) {
		// 1. recevice episodeId to episodeDetail.jsp
		// 2. delete episode
		String loginId = (String)session.getAttribute("loginId");
		
		Episode episode = lService.findEpisodeById(episodeId);
		
		lService.removeEpisode(episodeId);
		
		// 전 연재글 목록으로 이동
		Literature literature = lService.findLiteratureById(episode.getLiterature().getId());
		
		boolean onGroupFlag = false;
		
		for(Member m : literature.getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("literature", literature);

		return "episodeList";
	}
	
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public String showEpisodeList(String literatureId, Model model, HttpSession session) {
		// 1. Show the contents and artist name.
		// 2. Show list of serials.
		String loginId = (String)session.getAttribute("loginId");
		
		Literature literature = lService.findLiteratureById(literatureId);
		
		lService.increaseLiteratureHitByLiteratureId(literatureId);
		
		boolean onGroupFlag = false;
		
		for(Member m : literature.getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("literature", literature);
		
		return "episodeList";
	}
	
	@RequestMapping(value="bound.do", method=RequestMethod.POST)
	public String changeEpisodeBound(String episodeId, String bound, Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		Episode episode = lService.findEpisodeById(episodeId);
		
		episode.setBound(bound);
		episode.setContent(episode.getContentFromGit());
		
		if(!lService.modifyEpisode(episode)) {
			throw new RuntimeException("episode modify failed");
		}
		
		Literature literature = lService.findLiteratureById(episode.getLiterature().getId());
		
		boolean onGroupFlag = false;
		
		for(Member m : literature.getLitStorage().getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("literature", literature);
		
		return "episodeList";
	}
	
	@RequestMapping(value="changeHistoryDetail.do", method=RequestMethod.GET)
	public String showEpisodeChangeHistory(String historyId, Model model, HttpSession session) {
		model.addAttribute("history", lService.findChangeHistoryById(historyId));
		
		return "changeHistoryDetail";
	}
	
	@RequestMapping(value="rollback.do", method=RequestMethod.GET)
	public String rollbackEpisode(String historyId, Model model, HttpSession session) {
		ChangeHistory history = lService.findChangeHistoryById(historyId);
		Episode episode = history.getEpisode();
		
		episode.setContent(history.getContentFromGit());
		
		if(!lService.modifyEpisode(episode)) {
			throw new RuntimeException("episode rollback failed");
		}

		model.addAttribute("episode", episode);

		return "episodeDetail";
	}
}
