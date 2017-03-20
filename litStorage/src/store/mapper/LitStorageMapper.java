package store.mapper;

import java.util.List;

import domain.LitStorage;

public interface LitStorageMapper {
	public int insertLitStorage(LitStorage litStorage);
	public int deleteLitStorage(String id);
	public LitStorage selectLitStorageById(String id);
	public List<LitStorage> selectLitStoragesByMemberId(String id);
	public List<LitStorage> selectLitStoragesByName(String name);
	public List<LitStorage> selectAll();
}
