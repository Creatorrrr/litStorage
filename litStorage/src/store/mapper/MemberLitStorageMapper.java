package store.mapper;

import java.util.List;

import domain.Member;
import domain.MemberLitStorage;

public interface MemberLitStorageMapper {
	public int insertMemberLitStorage(MemberLitStorage memberLitStorage);
	
	public List<MemberLitStorage> selectMemberLitStoragesByLitStorageId(String id);
	
	public List<MemberLitStorage> selectMemberLitStoragesByMemberId(String id);
	
	public List<Member>	selectMembersByLitStorageId(String id);
}
