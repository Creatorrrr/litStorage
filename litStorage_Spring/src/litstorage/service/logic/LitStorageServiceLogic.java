package litstorage.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import litstorage.constants.Constants;
import litstorage.domain.ChangeHistory;
import litstorage.domain.Episode;
import litstorage.domain.LitStorage;
import litstorage.domain.Literature;
import litstorage.domain.MemberLitStorage;
import litstorage.service.facade.LitStorageService;
import litstorage.store.facade.ChangeHistoryStore;
import litstorage.store.facade.EpisodeStore;
import litstorage.store.facade.LitStorageStore;
import litstorage.store.facade.LiteratureStore;
import litstorage.store.facade.MemberLitStorageStore;
import litstorage.utils.PathBuilder;

@Service
public class LitStorageServiceLogic implements LitStorageService {

	@Autowired
	private LitStorageStore lsStore;
	@Autowired
	private MemberLitStorageStore mlsStore;
	@Autowired
	private LiteratureStore lStore;
	@Autowired
	private EpisodeStore epStore;
	@Autowired
	private ChangeHistoryStore chStore;

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
	public List<LitStorage> findAllWithPage(String page) {
		String begin = (Integer.parseInt(page) - 1) * Constants.LITSTORAGE_ROW_SIZE + 1 + "";
		String end = Integer.parseInt(page) * Constants.LITSTORAGE_ROW_SIZE + "";
		
		return lsStore.selectAllWithPage(begin, end);
	}
	
	@Override
	public String findRows() {
		return lsStore.selectRows();
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
