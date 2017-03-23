package store.mapper;

import java.util.List;

import domain.Episode;

public interface EpisodeMapper {

	public boolean insertEpisode(Episode episode);

	public boolean updateEpisode(Episode episode);

	public boolean deleteEpisode(String episode);

	public Episode selectEpisodeById(String id);

	public List<Episode> selectEpisodesByLiteratureId(String literatureId);

	public boolean updateBound(String bound);

}
