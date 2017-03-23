package service.facade;

import java.util.List;

import domain.LitStorage;
import domain.Literature;
import domain.MemberLitStorage;

public interface LitStorageService {
	boolean registerLitStorage(LitStorage litStorage);
	boolean removeLitStorage(LitStorage litStorage);
	List<LitStorage> findAll();
	LitStorage findLitStorageById(String id);
	List<LitStorage> findLitStoragesByMemberId(String id);
	List<LitStorage> findLitStoragesByName(String name);
	boolean registerMemberLitStorage(MemberLitStorage memberLitStorage);
	List<MemberLitStorage> findMemberLitStoragesByMemberId(String id);
	List<MemberLitStorage> findMemberLitStorageByLitStorageId(String id);
	List<Literature> findLiteraturesByMemberId(String id);
}
