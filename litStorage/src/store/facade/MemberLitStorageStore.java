package store.facade;

import java.util.List;

import domain.MemberLitStorage;

public interface MemberLitStorageStore {
	public boolean insertMemberLitStorage(MemberLitStorage memberLitStorage);
	
	public List<MemberLitStorage> selectMemberLitStoragesByLitStorageId(String id);
	
	public List<MemberLitStorage> selectMemberLitStoragesByMemberId(String id);
}
