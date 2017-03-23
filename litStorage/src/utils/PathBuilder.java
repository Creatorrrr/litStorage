package utils;

import constants.Constants;
import domain.Episode;
import domain.LitStorage;
import domain.Literature;

public class PathBuilder {
	public static String buildLitStoragePath(LitStorage litStorage) {
		StringBuilder strBuilder = new StringBuilder();
		
		String litStorageId = litStorage.getId();
		
		strBuilder.append(Constants.ROOT_PATH);
		strBuilder.append("/");
		strBuilder.append(litStorageId);	// litStorage ID
		
		return strBuilder.toString();
	}
	
	public static String buildLiteraturePath(Literature literature) {
		StringBuilder strBuilder = new StringBuilder();
		
		String litStorageId = literature.getLitStorage().getId();
		String literatureId = literature.getId();
		
		strBuilder.append(Constants.ROOT_PATH);
		strBuilder.append("/");
		strBuilder.append(litStorageId);	// litStorage ID
		strBuilder.append("/");
		strBuilder.append(literatureId);	// literatureId ID
		
		return strBuilder.toString();
	}
	
	public static String buildEpisodeFilePath(Episode episode) {
		StringBuilder strBuilder = new StringBuilder();
		
		String litStorageId = episode.getLiterature().getLitStorage().getId();
		String literatureId = episode.getLiterature().getId();
		String episodeId = episode.getId();
		
		strBuilder.append(Constants.ROOT_PATH);
		strBuilder.append("/");
		strBuilder.append(litStorageId);	// litStorage ID
		strBuilder.append("/");
		strBuilder.append(literatureId);	// literatureId ID
		strBuilder.append("/");
		strBuilder.append(episodeId + ".txt");	// episode file name
		
		return strBuilder.toString();
	}
	
	public static String buildEpisodeFileName(Episode episode) {
		StringBuilder strBuilder = new StringBuilder();
		
		String literatureId = episode.getLiterature().getId();
		String episodeId = episode.getId();
		
		strBuilder.append(literatureId);	// literatureId ID
		strBuilder.append("/");
		strBuilder.append(episodeId + ".txt");	// episode file name
		
		return strBuilder.toString();
	}
}
