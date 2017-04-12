package litstorage.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import litstorage.domain.DiscussionContent;
import litstorage.store.facade.DiscussionContentStore;
import litstorage.store.mapper.DiscussionContentMapper;

@Repository
public class DiscussionContentStoreLogic implements DiscussionContentStore{

	private SqlSessionFactory factory;
	
	public DiscussionContentStoreLogic(){
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}
	@Override
	public boolean insertDiscussionContent(DiscussionContent discussionContent) {
		SqlSession session = factory.openSession();
		int check;
		try {
			DiscussionContentMapper mapper = session.getMapper(DiscussionContentMapper.class);
			check = mapper.insertDiscussionContent(discussionContent);
			if(check>0){
				session.commit();
			} else {
				session.rollback();
			}
		}finally {
			session.close();
		}
		return check>0;
	}

	@Override
	public List<DiscussionContent> selectDiscussionContentsByDiscussionPlaceId(String id) {
		List<DiscussionContent> list = null;
		SqlSession session = factory.openSession();
		try{
			DiscussionContentMapper mapper = session.getMapper(DiscussionContentMapper.class);
			list = mapper.selectDiscussionContentsByDiscussionPlaceId(id);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public boolean deleteDiscussionContentById(String id) {
		SqlSession session = factory.openSession();
		int check;
		try {
			DiscussionContentMapper mapper = session.getMapper(DiscussionContentMapper.class);
			check = mapper.deleteDiscussionContentById(id);
			if(check>0){
				session.commit();
			} else {
				session.rollback();
			}
		}finally {
			session.close();
		}
		return check>0;
	}
}
