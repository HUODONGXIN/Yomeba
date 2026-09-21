package com.java2nb.common.service;

import com.java2nb.common.domain.BugLogDO;
import com.java2nb.common.domain.PageDO;
import com.java2nb.common.utils.Query;
import org.springframework.stereotype.Service;

@Service
public interface BugLogService {
	void save(BugLogDO bugLogDO);
	PageDO<BugLogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
