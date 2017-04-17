package litstorage.store.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import litstorage.domain.DiscussionPlace;
import litstorage.store.facade.DiscussionPlaceStore;
import litstorage.store.mapper.DiscussionPlaceMapper;

@Repository
public class DiscussionPlaceStoreLogic implements DiscussionPlaceStore {
	
	private SqlSessionFactory factory;
	
	public DiscussionPlaceStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}
	

	@Override
	public boolean insertDiscussionPlace(DiscussionPlace discussionPlace) {
		SqlSession session = factory.openSession();
		boolean check=false;
		try {
			DiscussionPlaceMapper mapper = session.getMapper(DiscussionPlaceMapper.class);
			check = mapper.insertDiscussionPlace(discussionPlace);
			if(check){
				session.commit();
			}else{
				session.rollback();
			}
		}finally {
			session.close();
		}
		return check;
	}

	@Override
	public DiscussionPlace selectDiscussionPlaceById(String id) {
		SqlSession session = factory.openSession();
		DiscussionPlace discussionPlace=null;
		try {
			DiscussionPlaceMapper mapper = session.getMapper(DiscussionPlaceMapper.class);
			discussionPlace = mapper.selectDiscussionPlaceById(id);
		}finally {
			session.close();
		}
		return discussionPlace;
	}

	@Override
	public List<DiscussionPlace> selectDiscussionPlacesByLitStorageId(String litStorageId) {
		SqlSession session = factory.openSession();
		List<DiscussionPlace> list=new ArrayList<>();
		try {
			DiscussionPlaceMapper mapper = session.getMapper(DiscussionPlaceMapper.class);
			list = mapper.selectDiscussionPlacesByLitStorageId(litStorageId);
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<DiscussionPlace> selectDiscussionPlacesByName(String title) {
		SqlSession session = factory.openSession();
		List<DiscussionPlace> list=new ArrayList<>();
		try {
			DiscussionPlaceMapper mapper = session.getMapper(DiscussionPlaceMapper.class);
			list = mapper.selectDiscussionPlacesByName(title);
		}finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<DiscussionPlace> selectDiscussionPlacesByMemberId(String memberId) {
		SqlSession session = factory.openSession();
		List<DiscussionPlace> list=new ArrayList<>();
		try {
			DiscussionPlaceMapper mapper = session.getMapper(DiscussionPlaceMapper.class);
			list = mapper.selectDiscussionPlacesByMemberId(memberId);
		}finally {
			session.close();
		}
		return list;
	}

}