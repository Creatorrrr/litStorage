package store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import domain.Board;
import store.facade.BoardStore;
import store.mapper.BoardMapper;

public class BoardStoreLogic implements BoardStore{
	
	private SqlSessionFactory factory;
	
	public BoardStoreLogic(){
		factory = SqlSessionFactoryProvider.getSqlSessionFactory();
	}
	
	@Override
	public boolean insertBoard(Board board) {
		SqlSession session = factory.openSession();
		int check;
		try {
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			check = mapper.insertBoard(board);
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
	public boolean deleteBoard(String id) {
		SqlSession session = factory.openSession();
		int check;
		try{
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			check = mapper.deleteBoard(id);
			if(check>0){
				session.commit();
			} else {
				session.rollback();
			}
		}finally{
			session.close();
		}
		return check>0;
	}

	@Override
	public List<Board> selectAll() {
		List<Board> list = null;
		SqlSession session = factory.openSession();
		try{
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			list = mapper.selectAll();
		}finally{
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Board> selectTitles() {
		List<Board> list = null;
		SqlSession session = factory.openSession();
		try{
			BoardMapper mapper = session.getMapper(BoardMapper.class);
			list = mapper.selectTitles();
		}finally{
			session.close();
		}
		return list;
	}	
	
	@Override
	public Board selectBoardById(String id){
		SqlSession session = factory.openSession();
		Board board=null;
		try{
			BoardMapper mapper =session.getMapper(BoardMapper.class);
			board=mapper.selectBoardById(id);
		}finally{
			session.close();
		}
		return board;
	}
}
