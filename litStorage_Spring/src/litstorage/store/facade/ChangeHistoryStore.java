package litstorage.store.facade;

import java.util.List;

import litstorage.domain.ChangeHistory;

public interface ChangeHistoryStore {
	boolean insertChangeHistory(ChangeHistory changeHistory);
	List<ChangeHistory> selectChangeHistoriesByEpisodeId(String id);
	ChangeHistory selectChangeHistoryById(String id);
}
