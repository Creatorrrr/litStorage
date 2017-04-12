package litstorage.store.facade;

import java.util.List;

import litstorage.domain.Member;
import litstorage.domain.MemberLitStorage;

public interface MemberLitStorageStore {
	public boolean insertMemberLitStorage(MemberLitStorage memberLitStorage);
	public boolean deleteMemberLitStorageByLitStorageId(String id);
	public List<MemberLitStorage> selectMemberLitStoragesByLitStorageId(String id);
	public List<MemberLitStorage> selectMemberLitStoragesByMemberId(String id);
	public List<Member>	selectMembersByLitStorageId(String id);
}
