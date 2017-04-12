package litstorage.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import litstorage.constants.Constants;
import litstorage.domain.ChangeHistory;
import litstorage.domain.Episode;
import litstorage.domain.Literature;
import litstorage.service.facade.LiteratureService;
import litstorage.store.facade.ChangeHistoryStore;
import litstorage.store.facade.EpisodeStore;
import litstorage.store.facade.LiteratureStore;
import litstorage.utils.PathBuilder;

@Service
public class LiteratureServiceLogic implements LiteratureService {

	@Autowired
	private LiteratureStore lStore;
	@Autowired
	private EpisodeStore epStore;
	@Autowired
	private ChangeHistoryStore chStore;

	@Override
	public boolean registerLiterature(Literature literature) {
		boolean checkLiterature = lStore.insertLiterature(literature);	// insert literature to db
		boolean checkGit = lStore.insertLiteratureToGit(literature);	// insert literature to git
		return checkLiterature && checkGit;
	}

	@Override
	public boolean removeLiterature(String id) {
		Literature literature = lStore.selectLiteraturesById(id);	// select literature from db
		
		epStore.deleteEpisodesByLiteratureId(id);	// delete episodes first
		for(Episode e : literature.getEpisodes()) {
			String message = literature.getCreator().getId() + " removed " + PathBuilder.buildEpisodeFileName(e) + " en bloc";
			String treeHash = epStore.deleteEpisodeFromGit(e, message);
			ChangeHistory history = new ChangeHistory();	// set change history
			history.setEditor(literature.getCreator());
			history.setContent(treeHash);
			history.setEpisode(e);
			history.setMessage(message);
			chStore.insertChangeHistory(history);	// insert change history
		}
		
		boolean checkLiterature = lStore.deleteLiterature(id);	// delete literature after delete episodes
		boolean checkGit = lStore.deleteLiteratureFromGit(PathBuilder.buildLiteraturePath(literature));	// delete from git
		
		return checkLiterature && checkGit;
	}
	
	@Override
	public boolean increaseLiteratureHitByLiteratureId(String id) {
		return lStore.updateLiteratureHitByLiteratureId(id);
	}

	@Override
	public Literature findLiteratureById(String Id) {
		return lStore.selectLiteraturesById(Id);
	}

	@Override
	public List<Literature> findLiteratureByName(String name) {

		List<Literature> literatures = lStore.selectLiteraturesByName(name);
		for (Literature literature : literatures) {

			literature.setEpisodes(epStore.selectEpisodesByLiteratureId(literature.getId()));
		}

		return literatures;
	}

	@Override
	public List<Literature> findLiteraturesByLitStorageId(String litStorageId) {
		return lStore.selectLiteraturesByLitStorageId(litStorageId);
	}

	@Override
	public List<Literature> findLiteraturesByGenreOrderByHits(String genre) {
		return lStore.selectLiteraturesByGenreOrderByHits(genre);
	}
	
	@Override
	public List<Literature> findLiteraturesByGenreOrderByHitsForMain(String genre) {
		return lStore.selectLiteraturesByGenreOrderByHitsForMain(genre);
	}

	@Override
	public List<Literature> findLiteraturesByGenreOrderById(String genre) {
		return lStore.selectLiteraturesByGenreOrderById(genre);
	}
	
	@Override
	public List<Literature> findLiteraturesByGenreOrderByIdForMain(String genre) {
		return lStore.selectLiteraturesByGenreOrderByIdForMain(genre);
	}
	
	@Override
	public List<Literature> findLiteraturesByGenreWithPage(String genre, String page) {
		String begin = (Integer.parseInt(page) - 1) * Constants.LITERATURE_ROW_SIZE + 1 + "";
		String end = Integer.parseInt(page) * Constants.LITERATURE_ROW_SIZE + "";
		
		return lStore.selectLiteraturesByGenreWithPage(genre, begin, end);
	}
	
	@Override
	public String findRowsByGenre(String genre) {
		return lStore.selectRowsByGenre(genre);
	}
	
	@Override
	public List<Literature> findLiteraturesByMemberId(String id){
		return lStore.selectLiteraturesByMemberId(id);
	}

	@Override
	public boolean registerEpisode(Episode episode) {
		boolean checkEpisode = epStore.insertEpisode(episode);	// insert episode to db
		String message = episode.getWriter().getId() + " registered " + PathBuilder.buildEpisodeFileName(episode);	// set commit message
		String treeHash = epStore.insertEpisodeToGit(episode, message);	// insert episode to git
		ChangeHistory history = new ChangeHistory();	// set change history
		history.setEditor(episode.getWriter());
		history.setContent(treeHash);
		history.setEpisode(episode);
		history.setMessage(message);
		boolean checkHistory = chStore.insertChangeHistory(history);	// insert change history
		return checkEpisode && checkHistory;
	}

	@Override
	public boolean modifyEpisode(Episode episode) {
		boolean checkEpisode = epStore.updateEpisode(episode);	// update episode to db
		String message = episode.getWriter().getId() + " modified " + PathBuilder.buildEpisodeFileName(episode);	// set commit message
		String treeHash = epStore.updateEpisodeToGit(episode, message);	// update episode to git
		ChangeHistory history = new ChangeHistory();	// set change history
		history.setEditor(episode.getWriter());
		history.setContent(treeHash);
		history.setEpisode(episode);
		history.setMessage(message);
		boolean checkHistory = chStore.insertChangeHistory(history);	// insert change history
		return checkEpisode && checkHistory;
	}

	@Override
	public boolean removeEpisode(String episodeId) {
		Episode episode = epStore.selectEpisodeById(episodeId);
		boolean checkEpisode = epStore.deleteEpisode(episode.getId());	// remove episode from db
		String message = episode.getWriter().getId() + " removed " + PathBuilder.buildEpisodeFileName(episode);	// set commit message
		String treeHash = epStore.deleteEpisodeFromGit(episode, message);	// remove episode from git
		ChangeHistory history = new ChangeHistory();	// set change history
		history.setEditor(episode.getWriter());
		history.setContent(treeHash);
		history.setEpisode(episode);
		history.setMessage(message);
		boolean checkHistory = chStore.insertChangeHistory(history);	// insert change history
		return checkEpisode && checkHistory;
	}

	@Override
	public Episode findEpisodeById(String Id) {
		return epStore.selectEpisodeById(Id);
	}

	@Override
	public List<Episode> findEpisodeByLiteratureId(String literatureId) {
		return epStore.selectEpisodesByLiteratureId(literatureId);
	}

//	@Override
//	public boolean registerChangeHistory(ChangeHistory changeHistory) {
//		return chStore.insertChangeHistory(changeHistory);
//	}

	@Override
	public List<ChangeHistory> findChangeHistoriesByEpisodeId(String episodeId) {
		return chStore.selectChangeHistoriesByEpisodeId(episodeId);
	}

	@Override
	public ChangeHistory findChangeHistoryById(String Id) {
		return chStore.selectChangeHistoryById(Id);
	}

}
