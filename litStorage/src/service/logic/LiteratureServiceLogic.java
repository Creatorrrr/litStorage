package service.logic;

import java.util.ArrayList;
import java.util.List;

import domain.ChangeHistory;
import domain.Episode;
import domain.Literature;
import service.facade.LiteratureService;
import store.facade.ChangeHistoryStore;
import store.facade.EpisodeStore;
import store.facade.LiteratureStore;
import store.facade.MemberStore;
import store.logic.ChangeHistoryStoreLogic;
import store.logic.EpisodeStoreLogic;
import store.logic.LiteratureStoreLogic;
import store.logic.MemberStoreLogic;

public class LiteratureServiceLogic implements LiteratureService {

	private LiteratureStore lStore;
	private EpisodeStore epStore;
	private ChangeHistoryStore chStore;

	public LiteratureServiceLogic() {
		lStore = new LiteratureStoreLogic();
		epStore = new EpisodeStoreLogic();
		chStore = new ChangeHistoryStoreLogic();
	}

	@Override
	public boolean registerLiterature(Literature literature) {
		return lStore.insertLiterature(literature);
	}

	@Override
	public boolean removeLiterature(String Id) {
		return lStore.deleteLiterature(Id);
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
	public List<Literature> findLiteratureByLitStorageId(String litStorageId) {
		return lStore.selectLiteraturesByLitStorageId(litStorageId);
	}

	@Override
	public List<Literature> findLiteratureByGenreOrderByHits() {
		return lStore.selectLiteraturesByGenreOrderByHits();
	}

	@Override
	public List<Literature> findLiteratureByGenreOrderById(String Id) {
		return lStore.selectLiteratureByGenreOrderById(Id);
	}

	@Override
	public boolean registerEpisode(Episode episode) {
		return epStore.insertEpisode(episode);
	}

	@Override
	public boolean modifyEpisode(Episode episode) {
		return epStore.updateEpisode(episode);
	}

	@Override
	public boolean removeEpisode(Episode episode) {
		return epStore.deleteEpisode(episode);
	}

	@Override
	public Episode findEpisodeById(String Id) {
		return epStore.selectEpisodeById(Id);
	}

	@Override
	public List<Episode> findEpisodeByLiteratureId(String literatureId) {
		return epStore.selectEpisodesByLiteratureId(literatureId);
	}

	@Override
	public boolean modifyBoundById(String Id) {
		return epStore.updateBound(Id);
	}

	@Override
	public boolean registerChangeHistory(ChangeHistory changeHistory) {
		return chStore.insertChangeHistory(changeHistory);
	}

	@Override
	public List<ChangeHistory> findChangeHistoriesByEpisodeId(String episodeId) {
		return chStore.selectChangeHistoriesByEpisodeId(episodeId);
	}

	@Override
	public ChangeHistory findChangeHistoryById(String Id) {
		return chStore.selectChangeHistoryById(Id);
	}

}
