package store.logic;

import java.util.List;

import domain.MemberLitStorage;
import store.facade.MemberLitStorageStore;

public class MemberLitStorageStoreLogic implements MemberLitStorageStore {

	@Override
	public boolean insertMemberLitStorage(MemberLitStorage memberLitStorage) {
		return false;
	}

	@Override
	public List<MemberLitStorage> selectMemberLitStoragesByLitStorageId(String id) {
		return null;
	}

	@Override
	public List<MemberLitStorage> selectMemberLitStoragesByMemberId(String id) {
		return null;
	}
	
}
