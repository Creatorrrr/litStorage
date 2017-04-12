package litstorage.store.facade;

import java.util.List;

import litstorage.domain.ChangeHistory;
import litstorage.domain.Episode;

public interface EpisodeStore {
	public boolean insertEpisode(Episode episode);
	/**
	 * insertEpisodeToGit() must used after insertEpisode() because of a generated episodeId
	 */
	public String insertEpisodeToGit(Episode episode, String message);
	public boolean updateEpisode(Episode episode);
	public String updateEpisodeToGit(Episode episode, String message);
	public boolean deleteEpisode(String episodeId);
	public String deleteEpisodeFromGit(Episode episode, String message);
	public boolean deleteEpisodesByLiteratureId(String literatureId);
	public Episode selectEpisodeById(String id);
	public List<Episode> selectEpisodesByLiteratureId(String literatureId);
	public boolean insertChangeHistory(ChangeHistory history);
}
