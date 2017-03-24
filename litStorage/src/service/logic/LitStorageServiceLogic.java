package service.logic;

import java.util.List;

import domain.LitStorage;
import domain.Literature;
import domain.MemberLitStorage;
import service.facade.LitStorageService;
import store.facade.EpisodeStore;
import store.facade.LitStorageStore;
import store.facade.LiteratureStore;
import store.facade.MemberLitStorageStore;
import store.logic.EpisodeStoreLogic;
import store.logic.LitStorageStoreLogic;
import store.logic.LiteratureStoreLogic;
import store.logic.MemberLitStorageStoreLogic;

public class LitStorageServiceLogic implements LitStorageService {

	private LitStorageStore lsStore;
	private MemberLitStorageStore mlsStore;
	// private MemberStore mStore;
	private LiteratureStore lStore;
	// private DiscussionPlaceStore dpStore;
	private EpisodeStore epStore;

	public LitStorageServiceLogic() {
		lsStore = new LitStorageStoreLogic();
		mlsStore = new MemberLitStorageStoreLogic();
		// mStore = new MemberStoreLogic();
		lStore = new LiteratureStoreLogic();
		// dpStore = new DiscussionPlaceStoreLogic();
		epStore = new EpisodeStoreLogic();
	}

	@Override
	public boolean registerLitStorage(LitStorage litStorage) {
		boolean checkLitStorage = lsStore.insertLitStorage(litStorage);	// insert litStorage to db
		boolean checkGit = lsStore.insertLitStorageToGit(litStorage);	// insert litStorage to git
		return checkLitStorage && checkGit;
	}

	@Override
	public boolean removeLitStorage(String litStorageId) {
		// *****************
//		for(Literature l : litStorage.getLiteratures()) {
//			epStore.deleteEpisodesByLiteratureId(l.getId());
//			lStore.deleteLiterature(l.getId());
//		}
//		boolean checkLitStorage = lsStore.deleteLitStorage(litStorage.getId()); // delete litStorage from db
//		
		return lsStore.deleteLitStorage(litStorageId);
	}

	@Override
	public List<LitStorage> findAll() {
		return lsStore.selectAll();
	}

	@Override
	public LitStorage findLitStorageById(String id) {
		return lsStore.selectLitStorageById(id);
	}

	@Override
	public List<LitStorage> findLitStoragesByMemberId(String id) {
		return lsStore.selectLitStoragesByMemberId(id);
	}

	@Override
	public List<LitStorage> findLitStoragesByName(String name) {
		return lsStore.selectLitStoragesByName(name);
	}

	@Override
	public boolean registerMemberLitStorage(MemberLitStorage memberLitStorage) {
		return mlsStore.insertMemberLitStorage(memberLitStorage);
	}

	@Override
	public List<MemberLitStorage> findMemberLitStoragesByMemberId(String id) {
		return mlsStore.selectMemberLitStoragesByMemberId(id);
	}

	@Override
	public List<MemberLitStorage> findMemberLitStorageByLitStorageId(String id) {
		return mlsStore.selectMemberLitStoragesByLitStorageId(id);
	}

	@Override
	public List<Literature> findLiteraturesByMemberId(String id) {
		return lStore.selectLiteraturesByMemberId(id);
	}

}
