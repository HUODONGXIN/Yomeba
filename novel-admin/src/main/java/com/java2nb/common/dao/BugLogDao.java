package com.java2nb.common.dao;

import com.java2nb.common.annotation.SanitizeMap;
import com.java2nb.common.domain.BugLogDO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BugLogDao {
	BugLogDO get(Long id);
	List<BugLogDO> list(@SanitizeMap Map<String,Object> map);
	int count(Map<String,Object> map);
	int save(BugLogDO bugLog);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
