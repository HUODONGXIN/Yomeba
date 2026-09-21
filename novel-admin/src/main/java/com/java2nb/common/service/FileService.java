package com.java2nb.common.service;

import com.java2nb.common.domain.FileDO;

import java.util.List;
import java.util.Map;

/**
 * ファイルアップロード
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-09-19 16:02:20
 */
public interface FileService {
	
	FileDO get(Long id);
	
	List<FileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FileDO sysFile);
	
	int update(FileDO sysFile);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	/**
	 * ファイルが存在するかどうかを判定する
	 * @param url FileDO に保存されているパス
	 * @return
	 */
    Boolean isExist(String url);
}
