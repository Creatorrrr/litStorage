package store.logic;

import java.util.List;

import domain.LitStorage;
import store.facade.LitStorageStore;

public class LitStorageStoreLogic implements LitStorageStore {

	@Override
	public boolean insertLitStorage(LitStorage litStorage) {
		return false;
	}

	@Override
	public boolean deleteLitStorage(String id) {
		return false;
	}

	@Override
	public LitStorage selectLitStorageById(String id) {
		return null;
	}

	@Override
	public List<LitStorage> selectLitStoragesByMemberId(String id) {
		return null;
	}

	@Override
	public List<LitStorage> selectLitStoragesByName(String name) {
		return null;
	}

	@Override
	public List<LitStorage> selectAll() {
		return null;
	}

}
