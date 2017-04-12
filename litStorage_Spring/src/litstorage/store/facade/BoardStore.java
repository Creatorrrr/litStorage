package litstorage.store.facade;

import java.util.List;

import litstorage.domain.Board;

public interface BoardStore {
	boolean insertBoard(Board board);
	boolean deleteBoard(String id);
	List<Board> selectAll();
	List<Board> selectTitles();
	Board selectBoardById(String id);
}
