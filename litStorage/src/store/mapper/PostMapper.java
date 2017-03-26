package store.mapper;

import java.util.HashMap;
import java.util.List;

import domain.Post;

public interface PostMapper {

	int insertPost(Post post);
	int updatePost(Post post);
	int deletePost(String id);
	List<Post>selectPostsByBoardId(String boardId);
	Post selectPostById(String id);
	List<Post>selectPostsByContent(HashMap<String, String> map);
	List<Post>selectPostsByHashtag(HashMap<String, String> map);
	List<Post> selectPostsByTitle(HashMap<String, String> map);
}
