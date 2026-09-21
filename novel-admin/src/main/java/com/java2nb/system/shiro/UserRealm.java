package com.java2nb.system.shiro;

import java.util.*;

import com.java2nb.common.config.ApplicationContextRegister;
import com.java2nb.system.dao.DataPermDao;
import com.java2nb.system.dao.DeptDao;
import com.java2nb.system.domain.DataPermDO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.java2nb.common.utils.ShiroUtils;
import com.java2nb.system.dao.SysUserDao;
import com.java2nb.system.domain.UserDO;
import com.java2nb.system.service.MenuService;

public class UserRealm extends AuthorizingRealm {
/*	@Autowired
	UserDao userMapper;
	@Autowired
	MenuService menuService;*/

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        Long userId = ShiroUtils.getUserId();
        MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Map<String, Object> map = new HashMap<>(16);
        map.put("username", username);
        String password = new String((char[]) token.getCredentials());

        SysUserDao userMapper = ApplicationContextRegister.getBean(SysUserDao.class);
        // ユーザー情報を検索
        UserDO user = userMapper.list(map).get(0);

        // アカウントが存在しない
        if (user == null) {
            throw new UnknownAccountException("アカウントまたはパスワードが正しくありません");
        }

        // パスワードが間違っている
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("アカウントまたはパスワードが正しくありません");
        }

        // アカウントがロックされている
        if (user.getStatus() == 0) {
            throw new LockedAccountException("アカウントがロックされています。管理者に連絡してください");
        }

        //配下部門を検索
        DeptDao deptDao = ApplicationContextRegister.getBean(DeptDao.class);
        user.setSupDeptIds(deptDao.getDeptIdsByParentId(user.getDeptId()));
        //データ権限を検索
        DataPermDao dataPermDao = ApplicationContextRegister.getBean(DataPermDao.class);
        List<DataPermDO> dataPerms = dataPermDao.selectDataPermsByUserId(user.getUserId());
        Map<String, List<DataPermDO>> permsMap = new HashMap<>();
        for (DataPermDO perm : dataPerms) {
            String key = perm.getTableName();
            List<DataPermDO> value = permsMap.get(key);
            if (value == null) {
                value = new ArrayList<>();
                permsMap.put(key, value);
            }
            value.add(perm);

        }
        user.setDataPerms(permsMap);


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

}
