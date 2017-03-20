package store.facade;

import java.util.List;

import domain.Literature;

public interface LiteratureStore {
	public boolean insertLiterature(Literature literature);
	public boolean deleteLiterature(String literatureId);
	public Literature selectLiteraturesById(String literatureId);
	public List<Literature> selectLiteraturesByLitStorageId(String litstorageId);
	public List<Literature> selectLiteraturesByName(String name);
	public List<Literature> selectLiteraturesByGenreOrderByHits(String hits);
	public List<Literature> selectLiteraturesByMemberId(String memberId);
}
