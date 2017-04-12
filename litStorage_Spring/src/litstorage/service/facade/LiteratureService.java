package litstorage.service.facade;

import java.util.List;

import litstorage.domain.ChangeHistory;
import litstorage.domain.Episode;
import litstorage.domain.Literature;

public interface LiteratureService {
	public boolean registerLiterature(Literature literature);

	public boolean removeLiterature(String Id);
	
	public boolean increaseLiteratureHitByLiteratureId(String id);

	public Literature findLiteratureById(String id);

	public List<Literature> findLiteratureByName(String name);

	public List<Literature> findLiteraturesByLitStorageId(String litStorageId);
	
	public List<Literature> findLiteraturesByMemberId(String id);

	public List<Literature> findLiteraturesByGenreOrderByHits(String genre);
	
	public List<Literature> findLiteraturesByGenreOrderByHitsForMain(String genre);

	public List<Literature> findLiteraturesByGenreOrderById(String genre);
	
	public List<Literature> findLiteraturesByGenreOrderByIdForMain(String genre);
	
	public List<Literature> findLiteraturesByGenreWithPage(String genre, String page);
	
	public String findRowsByGenre(String genre);

	public boolean registerEpisode(Episode episode);

	public boolean modifyEpisode(Episode episode);

	public boolean removeEpisode(String episodeId);

	public Episode findEpisodeById(String Id);

	public List<Episode> findEpisodeByLiteratureId(String literatureId);

//	public boolean registerChangeHistory(ChangeHistory changeHistory);

	public List<ChangeHistory> findChangeHistoriesByEpisodeId(String episodeId);

	public ChangeHistory findChangeHistoryById(String Id);

}
