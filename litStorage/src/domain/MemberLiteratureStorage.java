package domain;

public class MemberLiteratureStorage {
	private Member member;
	private LiteratureStorage literatureStorage;
	
	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	public LiteratureStorage getLiteratureStorage() {
		return literatureStorage;
	}
	
	public void setLiteratureStorage(LiteratureStorage literatureStorage) {
		this.literatureStorage = literatureStorage;
	}
}
