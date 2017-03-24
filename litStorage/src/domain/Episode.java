package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import utils.AutoCloser;
import utils.PathBuilder;

public class Episode {
	private String id;
	private String title;
	private String content;
	private Member writer;
	private String bound;
	private List<ChangeHistory> histories;
	private Literature literature;
	
	public Literature getLiterature() {
		return literature;
	}

	public void setLiterature(Literature literature) {
		this.literature = literature;
	}

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
	
	public String getContentFromGit() {
		File episodeFile = new File(PathBuilder.buildEpisodeFilePath(this));

		StringBuilder strBuilder = new StringBuilder();
		String line = null;
		
		BufferedReader bReader = null;
		
		try {
			bReader = new BufferedReader(new InputStreamReader(new FileInputStream(episodeFile), "UTF-8"));
			
			strBuilder.append(bReader.readLine());
			while((line = bReader.readLine()) != null) {
				strBuilder.append("\r\n");
				strBuilder.append(line);
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UnsupportedEncoding");
		} catch (FileNotFoundException e) {
			throw new RuntimeException("No such File");
		} catch (IOException e) {
			throw new RuntimeException("IO failed");
		} finally {
			AutoCloser.close(bReader);
		}
		
		return strBuilder.toString();
	}
	
}
