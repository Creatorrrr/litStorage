package litstorage.domain;

import java.util.List;

public class DiscussionPlace {
	private String id;
	private String title;
	private Member creator;
	private List<DiscussionContent> discussionContents;
	private LitStorage litStorage;
	
	public LitStorage getLitStorage() {
		return litStorage;
	}

	public void setLitStorage(LitStorage litStorage) {
		this.litStorage = litStorage;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Member getCreator() {
		return creator;
	}
	
	public void setCreator(Member creator) {
		this.creator = creator;
	}
	
	public List<DiscussionContent> getDiscussionContents() {
		return discussionContents;
	}
	
	public void setDiscussionContents(List<DiscussionContent> discussionContents) {
		this.discussionContents = discussionContents;
	}
}
