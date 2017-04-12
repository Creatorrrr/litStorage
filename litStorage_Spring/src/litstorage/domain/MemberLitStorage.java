package litstorage.domain;

import java.sql.Date;

public class MemberLitStorage {
	private Member member;
	private LitStorage litStorage;
	private Date joinTime;

	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	public LitStorage getLitStorage() {
		return litStorage;
	}
	
	public void setLitStorage(LitStorage litStorage) {
		this.litStorage = litStorage;
	}
	
	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
}
