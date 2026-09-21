package com.java2nb.common.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.java2nb.common.dao.BugLogDao;
import com.java2nb.common.domain.BugLogDO;
import com.java2nb.common.domain.PageDO;
import com.java2nb.common.service.BugLogService;
import com.java2nb.common.utils.Query;

@Service
public class BugLogServiceImpl implements BugLogService {
	@Autowired
	BugLogDao bugLogDao;

	@Async
	@Override
	public void save(BugLogDO bugLogDO) {
		bugLogDao.save(bugLogDO);
	}

	@Override
	public PageDO<BugLogDO> queryList(Query query) {
		int total = bugLogDao.count(query);
		List<BugLogDO> list = bugLogDao.list(query);
		PageDO<BugLogDO> page = new PageDO<>();
		page.setTotal(total);
		page.setRows(list);
		return page;
	}

	@Override
	public int remove(Long id) { return bugLogDao.remove(id); }

	@Override
	public int batchRemove(Long[] ids) { return bugLogDao.batchRemove(ids); }
}
