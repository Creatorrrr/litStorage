package store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Post;
import store.mapper.PostMapper;
import store.facade.PostStore;

public class PostStoreLogic implements PostStore{

	private SqlSessionFactory factory;
	
	public PostStoreLogic() {
		factory=SqlSessionFactoryProvider.getSqlSessionFactory();
	}
	
	@Override
	public boolean insertPost(Post post) {
		
		SqlSession session=factory.openSession();
		boolean result;
		
		try{
			PostMapper mapper=session.getMapper(PostMapper.class);
			if(result = mapper.insertPost(post) > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		}finally{
			session.close();
		}
		return result;
	}

	@Override
	public boolean updatePost(Post post) {
		
		SqlSession session = factory.openSession();
		boolean result;
		
		try{
			PostMapper mapper= session.getMapper(PostMapper.class);
			if(result = mapper.updatePost(post) > 0){
				session.commit();
			}else{
				session.rollback();
			}
			return result;
		}finally{
			session.close();
		}
	}

	@Override
	public boolean deletePost(String id) {
		SqlSession session = factory.openSession();
		boolean result;
		
		try{
			PostMapper mapper = session.getMapper(PostMapper.class);
			if (result = mapper.deletePost(id) > 0){
				session.commit();
			}else{
				session.rollback();
			}
			return result;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Post> selectPostsByBoardId(String boardId) {         
		SqlSession session = factory.openSession();
		List<Post> list = null;
		
		try{
			PostMapper mapper = session.getMapper(PostMapper.class);
			list = mapper.selectPostsByBoardId(boardId);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public Post selectPostById(String id) {
		SqlSession session = factory.openSession();
		Post post= null;
		try{
			PostMapper mapper = session.getMapper(PostMapper.class);
			post=mapper.selectPostById(id);
		}finally{
			session.close();
		}
		return post;
	}

	@Override
	public List<Post> selectPostsByContent(String content, String boardId) {
		SqlSession session = factory.openSession();
		List<Post> list = null;
		
		HashMap<String, String> map = new HashMap<>();
		map.put("content", content);
		map.put("boardId", boardId);
		
		try{
			PostMapper mapper = session.getMapper(PostMapper.class);
			list=mapper.selectPostsByContent(map);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<Post> selectPostsByHashtag(String hashTag, String boardId) {
		SqlSession session = factory.openSession();
		List<Post> list = null;
		
		HashMap<String, String> map = new HashMap<>();
		map.put("hashTag", hashTag);
		map.put("boardId", boardId);
		
		try{
			PostMapper mapper = session.getMapper(PostMapper.class);
			list=mapper.selectPostsByHashtag(map);
		}finally{
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Post> selectPostsByTitle(String title, String boardId) {
		SqlSession session = factory.openSession();
		List<Post> list = null;
		
		HashMap<String, String> map = new HashMap<>();
		map.put("title", title);
		map.put("boardId", boardId);
		
		try{
			PostMapper mapper = session.getMapper(PostMapper.class);
			list=mapper.selectPostsByTitle(map);
		}finally{
			session.close();
		}
		return list;
	}
	

}
