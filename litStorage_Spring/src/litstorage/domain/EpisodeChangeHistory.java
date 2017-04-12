package litstorage.domain;

import java.sql.Date;

public class EpisodeChangeHistory {
	private String id;
	private Member editor;
	private String content;
	private Date changeTime;
	private String message;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Member getEditor() {
		return editor;
	}
	
	public void setEditor(Member editor) {
		this.editor = editor;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getChangeTime() {
		return changeTime;
	}
	
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
