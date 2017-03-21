package store.facade;

import java.util.List;

import domain.Episode;

public interface EpisodeStore {
	public boolean insertEpisode(Episode episode);
	public boolean updateEpisode(Episode episode);
	public boolean deleteEpisode(Episode episode);
	public Episode selectEpisodeById(String id);
	public List<Episode> selectEpisodesByLiteratureId(String literatureId);
	public boolean updateBound(String bound);
}
