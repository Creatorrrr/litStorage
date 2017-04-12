package litstorage.store.mapper;

import java.util.List;

import litstorage.domain.Board;

public interface BoardMapper {
	int insertBoard(Board board);
	int deleteBoard(String id);
	List<Board> selectAll();
	List<Board> selectTitles();
	Board selectBoardById(String id);
}
