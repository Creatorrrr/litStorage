package store.facade;

import java.util.List;

import domain.ChangeHistory;
import domain.Episode;

public interface EpisodeStore {
	public boolean insertEpisode(Episode episode);
	public String insertEpisodeToGit(Episode episode, String message);
	public boolean updateEpisode(Episode episode);
	public String updateEpisodeToGit(Episode episode, String message);
	public boolean deleteEpisode(String episodeId);
	public String deleteEpisodeToGit(Episode episode, String message);
	public Episode selectEpisodeById(String id);
	public List<Episode> selectEpisodesByLiteratureId(String literatureId);
	public boolean updateBound(String bound);
	public boolean insertChangeHistory(ChangeHistory history);
}
