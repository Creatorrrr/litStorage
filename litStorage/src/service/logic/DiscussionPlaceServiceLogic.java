package service.logic;

import java.util.List;

import domain.DiscussionContent;
import domain.DiscussionPlace;
import service.facade.DiscussionPlaceService;
import store.facade.DiscussionContentStore;
import store.facade.DiscussionPlaceStore;
import store.facade.MemberStore;
import store.logic.DiscussionContentStoreLogic;
import store.logic.DiscussionPlaceStoreLogic;
import store.logic.MemberStoreLogic;

public class DiscussionPlaceServiceLogic implements DiscussionPlaceService {

	private DiscussionPlaceStore dpStore;
	private DiscussionContentStore dcStore;
	private MemberStore mStore;
	
	public DiscussionPlaceServiceLogic() {
		dpStore = new DiscussionPlaceStoreLogic();
		dcStore = new DiscussionContentStoreLogic();
		mStore = new MemberStoreLogic();
	
	}
	
	
	@Override
	public boolean registerDiscussionPlace(DiscussionPlace discussionPlace) {
		return dpStore.insertDiscussionPlace(discussionPlace);
	}

	@Override
	public List<DiscussionPlace> findDiscussionPlacesByLitStorageId(String litStorageId) {
		return dpStore.selectDiscussionPlacesByLitStorageId(litStorageId);
	}

	@Override
	public boolean registerDiscussionContent(DiscussionContent discussionContent) {
		return dcStore.insertDiscussionContent(discussionContent);
	}

	@Override
	public boolean removeDiscussionContent(String id) {
		return dcStore.deleteDiscussionContentById(id);
	}

	@Override
	public List<DiscussionContent> findDiscussionContentsByDiscussionPlaceId(String discussionPlaceId) {
		return dcStore.selectDiscussionContentsByDiscussionPlaceId(discussionPlaceId);
	}

	@Override
	public DiscussionPlace findDiscussionPlaceById(String id) {
		return dpStore.selectDiscussionPlaceById(id);
	}

	@Override
	public List<DiscussionPlace> findDiscussionPlacesByName(String name) {
		return dpStore.selectDiscussionPlacesByName(name);
	}

	@Override
	public List<DiscussionPlace> findDiscussionPlacesByMemberId(String memberId) {
		return dpStore.selectDiscussionPlacesByMemberId(memberId);
	}

}
