package com.shiro.bean;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

//自定义Realm 必须要的 给ShrioConfig第三步用的
public class UserRealm extends AuthorizingRealm{

    //执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权逻辑PrincipalCollection");

        //从数据库查用户判断是否为admin账户，设置isAdmin属性为【true/false】
        boolean isAdmin = true;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色列表
        Set roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        //判断管理员拥有所有权限
        if(isAdmin) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
           //非管理员添加权限
           //从数据库查询角色和权限，赋值给roles和menus
            info.setRoles(roles);
            info.setStringPermissions(menus);
        }
        return info;
    }

    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证逻辑AuthenticationToken");
        //假设数据库的用户名和密码
        String name="root";
        String password="root";

        //1.判断用户名
        //把token转为我们认识的，也是从controller那个token来的
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if (!userToken.getUsername().equals(name)){
            //用户名不存在
            return null; //shiro底层就会抛出 UnknownAccountException
        }

        //2. 验证密码,我们可以使用一个AuthenticationInfo实现类 SimpleAuthenticationInfo
        //	shiro会自动帮我们验证！重点是第二个参数就是要验证的密码！
        //第一个参数是传给上面的授权方法获得当前用户的,现在暂时设置为空先
        return new SimpleAuthenticationInfo("",password,"");
    }
}
