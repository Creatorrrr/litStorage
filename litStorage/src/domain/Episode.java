package domain;

import java.util.List;

public class Episode {
	private String id;
	private String title;
	private String content;
	private Member writer;
	private String bound;
	private List<ChangeHistory> histories;
	
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
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Member getWriter() {
		return writer;
	}
	
	public void setWriter(Member writer) {
		this.writer = writer;
	}
	
	public String getBound() {
		return bound;
	}
	
	public void setBound(String bound) {
		this.bound = bound;
	}
	
	public List<ChangeHistory> getHistories() {
		return histories;
	}
	
	public void setHistories(List<ChangeHistory> histories) {
		this.histories = histories;
	}
	
}
