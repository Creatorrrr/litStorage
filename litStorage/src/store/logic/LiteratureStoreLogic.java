package store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Literature;
import store.facade.LiteratureStore;
import store.mapper.LiteratureMapper;

public class LiteratureStoreLogic implements LiteratureStore{
	
	private SqlSessionFactory factory;
	
	public LiteratureStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}


}
