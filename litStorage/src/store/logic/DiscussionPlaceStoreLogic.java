package store.logic;

import java.util.List;

import domain.DiscussionPlace;
import store.facade.DiscussionPlaceStore;

public class DiscussionPlaceStoreLogic implements DiscussionPlaceStore {

	@Override
	public boolean insertDiscussionPlace(DiscussionPlace discussionPlace) {
		return false;
	}

	@Override
	public DiscussionPlace selectDiscussionPlaceById(String id) {
		return null;
	}

	@Override
	public List<DiscussionPlace> selectDiscussionPlaceByLitStorageId(String listStorageId) {
		return null;
	}

	@Override
	public List<DiscussionPlace> selectDiscussionPlaceByName(String name) {
		return null;
	}

	@Override
	public List<DiscussionPlace> selectDiscussionPlaceByMemberId(String memberId) {
		return null;
	}

}
