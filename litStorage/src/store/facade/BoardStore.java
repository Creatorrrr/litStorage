package store.facade;

import java.util.List;

import domain.Board;

public interface BoardStore {
	boolean insertBoard(Board board);
	boolean deleteBoard(String id);
	List<Board> selectAll();
	List<Board> selectTitles();
	Board selectBoardById(String id);
}
