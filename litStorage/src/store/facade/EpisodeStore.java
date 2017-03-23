package store.facade;

import java.util.List;

import domain.Episode;

public interface EpisodeStore {
	public boolean insertEpisode(Episode episode);
	public boolean insertEpisodeToGit(Episode episode);
	public boolean updateEpisode(Episode episode);
	public boolean updateEpisodeToGit(Episode episode);
	public boolean deleteEpisode(String episodeId);
	public boolean deleteEpisodeToGit(Episode episode);
	public Episode selectEpisodeById(String id);
	public List<Episode> selectEpisodesByLiteratureId(String literatureId);
	public boolean updateBound(String bound);
}
