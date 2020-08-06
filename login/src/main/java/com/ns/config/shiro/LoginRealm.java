package com.ns.config.shiro;

import com.ns.dto.UserDto;
import com.ns.entity.Admin;
import com.ns.service.AdminService;
import com.ns.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


public class LoginRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        UserDto userDto=(UserDto) subject.getPrincipal();

        //设置角色
        Set<String> roles = new HashSet<>();
        roles.add(userDto.getRid().getName());
       // SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //设置权限
       // info.addStringPermission(account.getPerms());
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //编写shiro 判断逻辑，判断用户名和密码
        //判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userPhone = token.getUsername();
        String password = new String(token.getPassword());
        if(userPhone.equals("admin")){
            Admin admin=adminService.findByName(userPhone);
            if (admin == null) {
                throw new UnknownAccountException("账号不存在");
            } else if (!password.equals(admin.getPassword())) {
                throw new IncorrectCredentialsException("密码不正确");
            }
            return new SimpleAuthenticationInfo(admin, admin.getPassword(), getName());
        }

        UserDto user = userService.findByUserName(userPhone);
        if (user == null) {
            throw new UnknownAccountException("账号不存在");
        } else if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码不正确");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());

    }
}
