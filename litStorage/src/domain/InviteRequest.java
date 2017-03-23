package domain;

import java.sql.Date;

public class InviteRequest {
	private Member sender;
	private Member receiver;
	private Date sendTime;
	private String message;
	private String form;
	private LitStorage litStorage;

	public LitStorage getLitStorage() {
		return litStorage;
	}

	public void setLitStorage(LitStorage litStorage) {
		this.litStorage = litStorage;
	}

	public Member getSender() {
		return sender;
	}
	
	public void setSender(Member sender) {
		this.sender = sender;
	}
	
	public Member getReceiver() {
		return receiver;
	}
	
	public void setReceiver(Member receiver) {
		this.receiver = receiver;
	}
	
	public Date getSendTime() {
		return sendTime;
	}
	
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getForm() {
		return form;
	}
	
	public void setForm(String form) {
		this.form = form;
	}
}
