package store.mapper;

import java.util.List;

import domain.Literature;

public interface LiteratureMapper {
	public int insertLiterature(Literature literature);

	public int deleteLiterature(String literatureId);

	public Literature selectLiteraturesById(String literatureId);

	public List<Literature> selectLiteraturesByLitStorageId(String litstorageId);

	public List<Literature> selectLiteraturesByName(String name);

	public List<Literature> selectLiteraturesByGenreOrderByHits();

	public List<Literature> selectLiteratureByGenreOrderById(String memberId);

	public List<Literature> selectLiteraturesByMemberId(String memberId);
}
