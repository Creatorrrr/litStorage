package service;

import java.util.List;

import domain.LitStorage;
import domain.MemberLitStorage;

public interface LitStorageService {
	boolean registerLitStorage(LitStorage litStorage);
	boolean removeLitStorage(String id);
	List<LitStorage> findAll();
	LitStorage findLitStorageById(String id);
	List<LitStorage> findLitStoragesByMemberId(String memberId);
	List<LitStorage> findLitStoragesByName(String name);
	boolean registerMemberLitStorage(MemberLitStorage memberLitStorage);
}
