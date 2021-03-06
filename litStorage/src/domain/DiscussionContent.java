package domain;

public class DiscussionContent {
	private String id;
	private Member writer;
	private String content;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Member getWriter() {
		return writer;
	}
	
	public void setWriter(Member writer) {
		this.writer = writer;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
