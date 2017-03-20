package store.facade;

import java.util.List;

import domain.LitStorage;

public interface LitStorageStore {
	public boolean insertLitStorage(LitStorage litStorage);
	
	public boolean deleteLitStorage(String id);
	
	public LitStorage selectLitStorageById(String id);
	
	public List<LitStorage> selectLitStoragesByMemberId(String id);
	
	public List<LitStorage> selectLitStoragesByName(String name);
	
	public List<LitStorage> selectAll();
}
