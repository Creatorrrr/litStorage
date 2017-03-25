package domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;


@XmlRootElement(name="literature")
@XmlType(propOrder = {"id","name","genre","introduce","creator","hits"})
@XmlSeeAlso({ArrayList.class,Member.class})
public class Literature {
	private String id;
	private String name;
	private String genre;
	private String imagePath;
	private String introduce;
	private Member creator;
	private int hits;
	private List<Episode> episodes;
	private LitStorage litStorage;
	
	@XmlTransient
	public LitStorage getLitStorage() {
		return litStorage;
	}

	public void setLitStorage(LitStorage litStorage) {
		this.litStorage = litStorage;
	}
	
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
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@XmlTransient
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	
	public int getHits() {
		return hits;
	}
	
	public void setHits(int hits) {
		this.hits = hits;
	}
	@XmlTransient
	public List<Episode> getEpisodes() {
		return episodes;
	}
	
	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}
}
