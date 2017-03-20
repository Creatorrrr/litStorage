package store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Post;
import store.Mapper.PostMapper;
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
			result=mapper.insertPost(post);
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
			result=mapper.updatePost(post);
			if(result != false){
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
			result = mapper.deletePost(id);
			if (result != false){
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
	public List<Post> selectPostsByBoardId(String boardId) {         //아직
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
	public List<Post> selectPostsByContent(String content) {
		SqlSession session = factory.openSession();
		List<Post> list = null;
		
		try{
			PostMapper mapper = session.getMapper(PostMapper.class);
			list=mapper.selectPostsByContent(content);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public List<Post> selectPostsByHashtag(String hashtag) {
		SqlSession session = factory.openSession();
		List<Post> list = null;
		
		try{
			PostMapper mapper = session.getMapper(PostMapper.class);
			list=mapper.selectPostsByHashtag(hashtag);
		}finally{
			session.close();
		}
		return list;
	}

}
