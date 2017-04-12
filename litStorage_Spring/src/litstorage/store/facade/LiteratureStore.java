package litstorage.store.facade;

import java.util.List;

import litstorage.domain.Literature;

public interface LiteratureStore {
	public boolean insertLiterature(Literature literature);
	public boolean insertLiteratureToGit(Literature literature);
	public boolean deleteLiterature(String literatureId);
	public boolean deleteLiteratureFromGit(String path);
	public boolean updateLiteratureHitByLiteratureId(String literatureId);
	public Literature selectLiteraturesById(String literatureId);
	public List<Literature> selectLiteraturesByLitStorageId(String litstorageId);
	public List<Literature> selectLiteraturesByName(String name);
	public List<Literature> selectLiteraturesByGenreOrderByHits(String genre);
	public List<Literature> selectLiteraturesByGenreOrderByHitsForMain(String genre);
	public List<Literature> selectLiteraturesByGenreOrderById(String genre);
	public List<Literature> selectLiteraturesByGenreOrderByIdForMain(String genre);
	public List<Literature> selectLiteraturesByMemberId(String memberId);
	public List<Literature> selectLiteraturesByGenreWithPage(String genre, String begin, String end);
	public String selectRowsByGenre(String genre);
}
