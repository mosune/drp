package com.drp.shiro.realm;

import com.drp.data.dao.AdminUserDao;
import com.drp.data.entity.AdminUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: UserRealm
 *
 * @author gcg
 * @create 2017/12/29 10:24
 * Description: user Realm
 * History:
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AdminUser user = (AdminUser) principals.getPrimaryPrincipal(); // 获取凭证
        // this.getAuthorizationCache().remove(principals);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // simpleAuthorizationInfo.setRoles(this.adminUser.findRoleByName(user.getUserName())); // 添加职位列表
        // simpleAuthorizationInfo.setStringPermissions(this.userService.findPerByName(user.getUserName())); // 添加权限列表
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", userName);
        List<AdminUser> users = adminUserDao.getList(map);

        if (CollectionUtils.isEmpty(users)) {
            throw new UnknownAccountException(); // 用户不存在
        }

        AdminUser user = users.get(0);

        if (user.getStatus().equals("1")) {
            throw new LockedAccountException(); // 账户被冻结
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getAccount() + user.getSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

}
