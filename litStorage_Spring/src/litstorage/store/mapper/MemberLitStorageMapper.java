package litstorage.store.mapper;

import java.util.List;

import litstorage.domain.Member;
import litstorage.domain.MemberLitStorage;

public interface MemberLitStorageMapper {
	public int insertMemberLitStorage(MemberLitStorage memberLitStorage);
	
	public int deleteMemberLitStorageByLitStorageId(String id);
	
	public List<MemberLitStorage> selectMemberLitStoragesByLitStorageId(String id);
	
	public List<MemberLitStorage> selectMemberLitStoragesByMemberId(String id);
	
	public List<Member>	selectMembersByLitStorageId(String id);
}
