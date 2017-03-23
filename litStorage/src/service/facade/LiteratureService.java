package service.facade;

import java.util.List;

import domain.ChangeHistory;
import domain.Episode;
import domain.Literature;

public interface LiteratureService {
	public boolean registerLiterature(Literature literature);

	public boolean removeLiterature(String Id);

	public Literature findLiteratureById(String Id);

	public List<Literature> findLiteratureByName(String name);

	public List<Literature> findLiteratureByLitStorageId(String litStorageId);

	public List<Literature> findLiteratureByGenreOrderByHits();

	public List<Literature> findLiteratureByGenreOrderById(String Id);

	public boolean registerEpisode(Episode episode);

	public boolean modifyEpisode(Episode episode);

	public boolean removeEpisode(Episode episodeId);

	public Episode findEpisodeById(String Id);

	public List<Episode> findEpisodeByLiteratureId(String literatureId);

	public boolean modifyBoundById(String Id);

	public boolean registerChangeHistory(ChangeHistory changeHistory);

	public List<ChangeHistory> findChangeHistoriesByEpisodeId(String episodeId);

	public ChangeHistory findChangeHistoryById(String Id);

}
