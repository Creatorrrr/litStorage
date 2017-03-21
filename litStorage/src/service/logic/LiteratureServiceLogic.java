package service.logic;

import java.util.List;

import domain.ChangeHistory;
import domain.Episode;
import domain.Literature;
import service.facade.LiteratureService;
import store.facade.ChangeHistoryStore;
import store.facade.EpisodeStore;
import store.facade.LiteratureStore;
import store.logic.ChangeHistoryStoreLogic;
import store.logic.EpisodeStoreLogic;
import store.logic.LiteratureStoreLogic;

public class LiteratureServiceLogic implements LiteratureService{
	
	private LiteratureStore store;
	private EpisodeStore EpStore;
	private ChangeHistoryStore ChStore;
	
	public LiteratureServiceLogic() {
		store = new LiteratureStoreLogic();
		EpStore = new EpisodeStoreLogic();
		ChStore = new ChangeHistoryStoreLogic();
	}

	@Override
	public boolean registerLiterature(Literature literature) {
		return store.insertLiterature(literature);
	}

	@Override
	public boolean removeLiterature(String Id) {
		return store.deleteLiterature(Id);
	}

	@Override
	public Literature findLiteratureById(String Id) {
		return store.selectLiteraturesById(Id);
	}

	@Override
	public List<Literature> findLiteratureByName(String name) {
		return store.selectLiteraturesByName(name);
	}

	@Override
	public List<Literature> findLiteratureByLitStorageId(String litStorageId) {
		return store.selectLiteraturesByLitStorageId(litStorageId);
	}

	@Override
	public List<Literature> findLiteratureByGenreOrderByHits() {
		return store.selectLiteraturesByGenreOrderByHits();
	}

	@Override
	public List<Literature> findLiteratureByGenreOrderById(String Id) {
		return store.selectLiteratureByGenreOrderById(Id);
	}

	@Override
	public boolean registerEpisode(Episode episode) {
		return EpStore.insertEpisode(episode);
	}

	@Override
	public boolean modifyEpisode(Episode episode) {
		return EpStore.updateEpisode(episode);
	}

	@Override
	public boolean removeEpisode(Episode episode) {
		return EpStore.deleteEpisode(episode);
	}

	@Override
	public Episode findEpisodeById(String Id) {
		return EpStore.selectEpisodeById(Id);
	}

	@Override
	public List<Episode> findEpisodeByLiteratureId(String literatureId) {
		return EpStore.selectEpisodesByLiteratureId(literatureId);
	}

	@Override
	public boolean modifyBoundById(String Id) {
		return EpStore.updateBound(Id);
	}

	@Override
	public boolean registerChangeHistory(ChangeHistory changeHistory) {
		return ChStore.insertChangeHistory(changeHistory);
	}

	@Override
	public List<ChangeHistory> findChangeHistoriesByEpisodeId(String episodeId) {
		return ChStore.selectChangeHistoriesByEpisodeId(episodeId);
	}

	@Override
	public ChangeHistory findChangeHistoryById(String Id) {
		return ChStore.selectChangeHistoryById(Id);
	}

}
