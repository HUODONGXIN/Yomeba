package com.java2nb.common.service;

import com.java2nb.common.domain.DictDO;
import com.java2nb.system.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * データ辞書テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-09-29 18:28:07
 */
public interface DictService {
	
	DictDO get(Long id);
	
	List<DictDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DictDO dict);
	
	int update(DictDO dict);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<DictDO> listType();
	
	String getName(String type,String value);

	/**
	 * 趣味のリストを取得する
	 * @return
     * @param userDO
	 */
	List<DictDO> getHobbyList(UserDO userDO);

	/**
	 * 性別のリストを取得する
 	 * @return
	 */
	List<DictDO> getSexList();

	/**
	 * type をもとにデータを取得する
	 * @param map
	 * @return
	 */
	List<DictDO> listByType(String type);

}
