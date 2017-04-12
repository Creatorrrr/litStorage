package litstorage.domain;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

@XmlRootElement(name="member")
@XmlType(name="member" ,propOrder = {"id","name","email"})

public class Member {
	private String id;
	private String name;
	private String password;
	private String email;
	
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
	@XmlTransient
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
