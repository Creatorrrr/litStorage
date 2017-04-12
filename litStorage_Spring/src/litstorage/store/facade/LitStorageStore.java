package litstorage.store.facade;

import java.util.List;

import litstorage.domain.LitStorage;

public interface LitStorageStore {
	public boolean insertLitStorage(LitStorage litStorage);
	public boolean insertLitStorageToGit(LitStorage litStorage);
	public boolean deleteLitStorage(String litStorageId);
	public boolean deleteLitStorageFromGit(String path);
	public LitStorage selectLitStorageById(String id);
	public List<LitStorage> selectLitStoragesByMemberId(String id);
	public List<LitStorage> selectLitStoragesByName(String name);
	public List<LitStorage> selectAll();
	public List<LitStorage> selectAllWithPage(String begin, String end);
	public String selectRows();
}
