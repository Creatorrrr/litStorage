package domain;

import java.util.List;

public class LitStorage {
	private String id;
	private String name;
	private String introduce;
	private Member creator;
	private List<Member> participants;
	private List<Literature> literatures;
	private List<DiscussionPlace> discussionPlaces;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIntroduce() {
		return introduce;
	}
	
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public Member getCreator() {
		return creator;
	}
	
	public void setCreator(Member creator) {
		this.creator = creator;
	}
	
	public List<Member> getParticipants() {
		return participants;
	}
	
	public void setParticipants(List<Member> participants) {
		this.participants = participants;
	}
	
	public List<Literature> getLiteratures() {
		return literatures;
	}
	
	public void setLiteratures(List<Literature> literatures) {
		this.literatures = literatures;
	}
	
	public List<DiscussionPlace> getDiscussionPlaces() {
		return discussionPlaces;
	}
	
	public void setDiscussionPlaces(List<DiscussionPlace> discussionPlaces) {
		this.discussionPlaces = discussionPlaces;
	}
}
