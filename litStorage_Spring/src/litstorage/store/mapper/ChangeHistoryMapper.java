package litstorage.store.mapper;

import java.util.List;

import litstorage.domain.ChangeHistory;

public interface ChangeHistoryMapper {
	int insertChangeHistory(ChangeHistory changeHistory);
	List<ChangeHistory> selectChangeHistoriesByEpisodeId(String id);
	ChangeHistory selectChangeHistoryById(String id);
}
