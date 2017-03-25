package store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.ChangeHistory;
import store.facade.ChangeHistoryStore;
import store.mapper.ChangeHistoryMapper;

public class ChangeHistoryStoreLogic implements ChangeHistoryStore{
	
	private SqlSessionFactory factory;
	
	public ChangeHistoryStoreLogic(){
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
		
	}
	
	@Override
	public boolean insertChangeHistory(ChangeHistory changeHistory) {
		SqlSession session = factory.openSession();
		int check;
		try {
			ChangeHistoryMapper mapper = session.getMapper(ChangeHistoryMapper.class);
			check = mapper.insertChangeHistory(changeHistory);
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
	public List<ChangeHistory> selectChangeHistoriesByEpisodeId(String id) {
		List<ChangeHistory> list = null;
		SqlSession session = factory.openSession();
		try{
			ChangeHistoryMapper mapper = session.getMapper(ChangeHistoryMapper.class);
			list = mapper.selectChangeHistoriesByEpisodeId(id);
		}finally{
			session.close();
		}
		return list;
	}

	@Override
	public ChangeHistory selectChangeHistoryById(String id) {
		ChangeHistory changeHistory = null;
		SqlSession session = factory.openSession();
		try{
			ChangeHistoryMapper mapper = session.getMapper(ChangeHistoryMapper.class);
			changeHistory = mapper.selectChangeHistoryById(id);
		}finally{
			session.close();
		}
		return changeHistory;
	}

}
