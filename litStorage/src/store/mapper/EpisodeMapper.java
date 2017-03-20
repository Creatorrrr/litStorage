package store.mapper;

import java.util.List;

import domain.Episode;

public interface EpisodeMapper {

	public boolean insertEpisode(Episode episode);
	public boolean updateEpisode(Episode episode);
	public boolean deleteEpisode(Episode episode);
	public Episode selectEpisodeByid(String id);
	public List<Episode> selectEpisodesByLiteratureId(String literatureId);
	public boolean updateBound(String bound);
	
	
}
