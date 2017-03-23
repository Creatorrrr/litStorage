package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

import utils.AutoCloser;
import utils.PathBuilder;

public class ChangeHistory {
	private String id;
	private Member editor;
	private String content;
	private Date changeTime;
	private String message;
	private Episode episode;
	
	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}

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
	
	public String getContentFromGit() {
		Git git = null;
		
		File repoDir = new File(PathBuilder.buildLitStoragePath(episode.getLiterature().getLitStorage()));
		
		if(!repoDir.exists()) {
			throw new RuntimeException("Git repository path does not exists");
		}
		
		Repository repo = null;
		
		ObjectId tree = null;
		ObjectId fileId = null;
		ObjectReader reader = null;
		ObjectLoader oLoader = null;
		
		BufferedReader bReader = null;
		
		StringBuilder strBuilder = new StringBuilder();
		String line = null;
		
		try {
			git = Git.open(repoDir);
			repo = git.getRepository();
			
			tree = ObjectId.fromString(content);
	        
	        fileId = getFileIdFromTreeId(repo, tree);
	        
	        reader = repo.newObjectReader();
	        oLoader = reader.open(fileId);
	        
	        bReader = new BufferedReader(new InputStreamReader(oLoader.openStream(), "UTF-8"));
			
			strBuilder.append(bReader.readLine());
			while((line = bReader.readLine()) != null) {
				strBuilder.append("\r\n");
				strBuilder.append(line);
			}
		} catch (MissingObjectException e) {
			throw new RuntimeException("Can not get previous file from history");
		} catch (IOException e) {
			throw new RuntimeException("Can not get previous file from history");
		} finally {
			AutoCloser.close(git);
		}
		
		return strBuilder.toString();
	}
	
	private ObjectId getFileIdFromTreeId(Repository repo, ObjectId treeId) {
		ObjectReader reader = repo.newObjectReader();
		CanonicalTreeParser treeParser = null;
		ObjectId fileId = null;
		
		TreeWalk treeWalk = new TreeWalk(repo);
		
		try {
			treeWalk.addTree(treeId);
			treeWalk.setRecursive(true);
            treeWalk.setFilter(PathFilter.create(episode.getId() + ".txt"));
            treeId = treeWalk.getObjectId(0);
            
			treeParser = new CanonicalTreeParser(null, reader, treeId);
			
			if(treeParser.findFile(episode.getId() + ".txt")) {
				fileId = treeParser.getEntryObjectId();
			} else {
				return null;
			}
		} catch (IncorrectObjectTypeException e) {
			throw new RuntimeException("Can not get previous file from history");
		} catch (IOException e) {
			throw new RuntimeException("Can not get previous file from history");
		} finally {
			AutoCloser.close(treeWalk);
		}
		
		return fileId;
	}
}
