package store.logic;


import org.apache.ibatis.session.SqlSessionFactory;

import store.facade.InviteRequestStore;

public class InviteRequestStoreLogic implements InviteRequestStore {

	private SqlSessionFactory factory;

	public InviteRequestStoreLogic() {
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}

	
}
