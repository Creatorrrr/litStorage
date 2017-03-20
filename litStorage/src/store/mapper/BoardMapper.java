package store.mapper;

import java.util.List;

import domain.Board;

public interface BoardMapper {
	int insertBoard(Board board);
	int deleteBoard(String id);
	List<Board> selectAll();
}
