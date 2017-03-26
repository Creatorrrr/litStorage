package domain;

public class MemberLiteratureStorage {
	private Member member;
	private LiteratureStorage litStorage;
	
	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	public LiteratureStorage getLitStorage() {
		return litStorage;
	}
	
	public void setLiteratureStorage(LiteratureStorage litStorage) {
		this.litStorage = litStorage;
	}
}
