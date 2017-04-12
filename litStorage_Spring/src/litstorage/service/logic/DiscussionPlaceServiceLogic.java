package litstorage.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import litstorage.domain.DiscussionContent;
import litstorage.domain.DiscussionPlace;
import litstorage.service.facade.DiscussionPlaceService;
import litstorage.store.facade.DiscussionContentStore;
import litstorage.store.facade.DiscussionPlaceStore;

@Service
public class DiscussionPlaceServiceLogic implements DiscussionPlaceService {

	@Autowired
	private DiscussionPlaceStore dpStore;
	@Autowired
	private DiscussionContentStore dcStore;
	
	
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
