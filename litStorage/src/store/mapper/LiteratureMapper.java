package store.mapper;

import java.util.List;

import domain.Literature;

public interface LiteratureMapper {
	public int insertLiterature(Literature literature);

	public int deleteLiterature(String literatureId);
	
	public int updateLiteratureHitByLiteratureId(String literatureId);

	public Literature selectLiteraturesById(String literatureId);

	public List<Literature> selectLiteraturesByLitStorageId(String litstorageId);

	public List<Literature> selectLiteraturesByName(String name);

	public List<Literature> selectLiteraturesByGenreOrderByHits(String genre);

	public List<Literature> selectLiteraturesByGenreOrderById(String genre);
	
	public List<Literature> selectLiteraturesByGenreOrderByHitsForMain(String genre);

	public List<Literature> selectLiteraturesByGenreOrderByIdForMain(String genre);

	public List<Literature> selectLiteraturesByMemberId(String memberId);
}
