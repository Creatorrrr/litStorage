package service.facade;

import java.util.List;

import domain.LitStorage;
import domain.Literature;
import domain.MemberLitStorage;

public interface LitStorageService {
	public boolean registerLitStorage(LitStorage litStorage);
	public boolean removeLitStorage(String litStorageId);
	public List<LitStorage> findAll();
	public List<LitStorage> findAllWithPage(String page);
	public String findRows();
	public LitStorage findLitStorageById(String id);
	public List<LitStorage> findLitStoragesByMemberId(String id);
	public List<LitStorage> findLitStoragesByName(String name);
	public boolean registerMemberLitStorage(MemberLitStorage memberLitStorage);
	public List<MemberLitStorage> findMemberLitStoragesByMemberId(String id);
	public List<MemberLitStorage> findMemberLitStorageByLitStorageId(String id);
	public List<Literature> findLiteraturesByMemberId(String id);
}
