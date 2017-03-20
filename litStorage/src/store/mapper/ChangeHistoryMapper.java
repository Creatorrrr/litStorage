package store.mapper;

import java.util.List;

import domain.ChangeHistory;

public interface ChangeHistoryMapper {
	boolean insertChangeHistory(ChangeHistory changeHistory);
	List<ChangeHistory> selectChangeHistoriesByEpisodeId(String id);
	ChangeHistory selectChangeHistoryById(String id);
}
