package com.drp.shiro.realm;

import com.drp.data.entity.AdminUser;
import com.drp.service.AdminUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

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
    private AdminUserService adminUserService;

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
        // User user = this.userService.findUserByName(userName);

        /*if (user == null) {
            throw new UnknownAccountException(); // 用户不存在
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user, user.getPassword(), ByteSource.Util.bytes(user.getUserName()), getName()); // 检验账户*/
        return null;
    }

}
