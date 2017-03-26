package store.mapper;

import java.util.HashMap;
import java.util.List;

import domain.LitStorage;

public interface LitStorageMapper {
	public int insertLitStorage(LitStorage litStorage);
	public int deleteLitStorage(String litStorageId);
	public LitStorage selectLitStorageById(String id);
	public List<LitStorage> selectLitStoragesByMemberId(String id);
	public List<LitStorage> selectLitStoragesByName(String name);
	public List<LitStorage> selectAll();
	public List<LitStorage> selectAllWithPage(HashMap<String, String> map);
	public String selectRows();
}
