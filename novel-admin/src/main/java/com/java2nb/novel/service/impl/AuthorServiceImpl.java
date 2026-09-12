package com.java2nb.novel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

import com.java2nb.common.utils.AesUtil;
import com.java2nb.novel.dao.AuthorDao;
import com.java2nb.novel.dao.UserDao;
import com.java2nb.novel.domain.AuthorDO;
import com.java2nb.novel.domain.UserDO;
import com.java2nb.novel.service.AuthorService;



@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorDao authorDao;

	@Autowired
	private UserDao userDao;
	
	@Override
	public AuthorDO get(Long id){
		return authorDao.get(id);
	}
	
	@Override
	public List<AuthorDO> list(Map<String, Object> map){
		List<AuthorDO> list = authorDao.list(map);
		//解密密码明文（password_plain 为 AES 密文，解密后返回前端展示）
		for (AuthorDO author : list) {
			author.setPasswordPlain(AesUtil.decrypt(author.getPasswordPlain()));
		}
		return list;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return authorDao.count(map);
	}
	
	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
	public int save(AuthorDO author){
		//创建作者登录账号（前台作家专区登录使用 user 表，密码为纯 MD5，与 user 表现有账号一致）
		if (author.getUsername() != null && !author.getUsername().trim().isEmpty()
				&& author.getPassword() != null && !author.getPassword().trim().isEmpty()) {
			UserDO user = new UserDO();
			user.setUsername(author.getUsername().trim());
			user.setPassword(md5(author.getPassword().trim()));
			user.setPasswordPlain(AesUtil.encrypt(author.getPassword().trim()));
			user.setNickName(author.getPenName());
			user.setAccountBalance(0L);
			user.setStatus(0);
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			userDao.save(user);
			author.setUserId(user.getId());
		}
		return authorDao.save(author);
	}
	
	@Override
	public int update(AuthorDO author){
		return authorDao.update(author);
	}
	
	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
	public int remove(Long id){
		AuthorDO author = authorDao.get(id);
		int result = authorDao.remove(id);
		//连带删除作者登录账号
		if (author != null && author.getUserId() != null) {
			userDao.remove(author.getUserId());
		}
		return result;
	}

	/**
	 * 标准 MD5 小写十六进制（与前台 user 表登录密码加密方式一致）
	 */
	private String md5(String text) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(text.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException("MD5 加密失败", e);
		}
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return authorDao.batchRemove(ids);
	}

	@Override
	public Map<Object, Object> tableSta(Date minDate) {
		List<Map<Object, Object>> maps = authorDao.tableSta(minDate);

		return maps.stream().collect(Collectors.toMap(x -> x.get("staDate"), x -> x.get("authorCount")));


	}

}
