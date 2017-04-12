package litstorage.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;


@XmlRootElement(name="litStorage")
@XmlType(propOrder = {"id","name","introduce","creator"})
@XmlSeeAlso({ArrayList.class,Member.class})
public class LitStorage {
	private String id;
	private String name;
	private String introduce;
	private Member creator;
	private List<Member> participants;
	private List<Literature> literatures;
	private List<DiscussionPlace> discussionPlaces;
	
	@XmlAttribute
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
	@XmlTransient
	public List<Member> getParticipants() {
		return participants;
	}
	
	public void setParticipants(List<Member> participants) {
		this.participants = participants;
	}
	@XmlTransient
	public List<Literature> getLiteratures() {
		return literatures;
	}
	
	public void setLiteratures(List<Literature> literatures) {
		this.literatures = literatures;
	}
	@XmlTransient
	public List<DiscussionPlace> getDiscussionPlaces() {
		return discussionPlaces;
	}
	
	public void setDiscussionPlaces(List<DiscussionPlace> discussionPlaces) {
		this.discussionPlaces = discussionPlaces;
	}
}
