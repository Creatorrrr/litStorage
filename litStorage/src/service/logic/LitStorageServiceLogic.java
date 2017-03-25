package service.logic;

import java.util.List;

import domain.ChangeHistory;
import domain.Episode;
import domain.LitStorage;
import domain.Literature;
import domain.MemberLitStorage;
import service.facade.LitStorageService;
import store.facade.ChangeHistoryStore;
import store.facade.EpisodeStore;
import store.facade.LitStorageStore;
import store.facade.LiteratureStore;
import store.facade.MemberLitStorageStore;
import store.logic.ChangeHistoryStoreLogic;
import store.logic.EpisodeStoreLogic;
import store.logic.LitStorageStoreLogic;
import store.logic.LiteratureStoreLogic;
import store.logic.MemberLitStorageStoreLogic;
import utils.PathBuilder;

public class LitStorageServiceLogic implements LitStorageService {

	private LitStorageStore lsStore;
	private MemberLitStorageStore mlsStore;
	// private MemberStore mStore;
	private LiteratureStore lStore;
	// private DiscussionPlaceStore dpStore;
	private EpisodeStore epStore;
	private ChangeHistoryStore chStore;

	public LitStorageServiceLogic() {
		lsStore = new LitStorageStoreLogic();
		mlsStore = new MemberLitStorageStoreLogic();
		// mStore = new MemberStoreLogic();
		lStore = new LiteratureStoreLogic();
		// dpStore = new DiscussionPlaceStoreLogic();
		epStore = new EpisodeStoreLogic();
		chStore = new ChangeHistoryStoreLogic();
	}

	@Override
	public boolean registerLitStorage(LitStorage litStorage) {
		boolean checkLitStorage = lsStore.insertLitStorage(litStorage);	// insert litStorage to db
		boolean checkGit = lsStore.insertLitStorageToGit(litStorage);	// insert litStorage to git
		
		MemberLitStorage mls = new MemberLitStorage();
		mls.setMember(litStorage.getCreator());
		mls.setLitStorage(litStorage);
		
		mlsStore.insertMemberLitStorage(mls);	// insert memberLitStorage
		
		return checkLitStorage && checkGit;
	}

	@Override
	public boolean removeLitStorage(String litStorageId) {
		LitStorage litStorage = lsStore.selectLitStorageById(litStorageId);
		
		for(Literature l : litStorage.getLiteratures()) {
			epStore.deleteEpisodesByLiteratureId(l.getId());
			for(Episode e : l.getEpisodes()) {
				String message = l.getCreator().getId() + " removed " + PathBuilder.buildEpisodeFileName(e) + " en bloc";
				String treeHash = epStore.deleteEpisodeFromGit(e, message);
				ChangeHistory history = new ChangeHistory();	// set change history
				history.setEditor(litStorage.getCreator());
				history.setContent(treeHash);
				history.setEpisode(e);
				history.setMessage(message);
				chStore.insertChangeHistory(history);	// insert change history
			}
			
			lStore.deleteLiterature(l.getId());
		}
		
		boolean checkLitStorage = lsStore.deleteLitStorage(litStorage.getId()); // delete litStorage from db
		boolean checkGit = lsStore.deleteLitStorageFromGit(PathBuilder.buildLitStoragePath(litStorage));
		
		mlsStore.deleteMemberLitStorageByLitStorageId(litStorageId);
		
		return checkLitStorage && checkGit;
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
