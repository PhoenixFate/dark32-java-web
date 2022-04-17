package com.phoenix.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义realm，继承自AuthenticatingRealm，用于实现认证
 * <p>
 * 授权需要继承 AuthorizingRealm 类, 并实现其 doGetAuthorizationInfo 方法
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 认证需要实现发方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    //登录成功之后，便不会再执行，需要退出登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("[first realm]: doGetAuthenticationInfo()");
        System.out.println("authenticationToken: " + authenticationToken);
        //1. 把AuthenticationToken 转换为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        //2. 从UsernamePasswordToken 中来获取username
        String username = usernamePasswordToken.getUsername();
        //这里的密码，非加密
        System.out.println("password: " + new String(usernamePasswordToken.getPassword()));
        System.out.println("Credentials: " + new String((char[]) usernamePasswordToken.getCredentials()));

        //3. 调用数据库的方法，从数据库中查询username 对应的用户记录

        //4. 若用户不存在，则可以抛出UnKnownAccountException 异常
        if ("unknown".equals(username)) {
            throw new UnknownAccountException("用户不存在");
        }

        //5. 根据用户信息的情况，决定是否需要抛出其他的AuthenticationException异常
        if ("test".equals(username)) {
            throw new LockedAccountException("用户被锁定");
        }

        //6. 根据用户的情况，来构建AuthenticationInfo对象并返回, 通常使用的实现类为 SimpleAuthenticationInfo
        //需要从数据库中获取
        //1) principal: 认证的实体信息，可以是username，也可以是数据表对应的实体类对象
        Object principal = username;
        //2) credentials: 密码
        Object credentials = "123456";
        credentials = "834db2b807747d448a670e03bf7502f6";//这里的密码应该是从数据库中查出来的
        //3) realmName: 当前realm对象的name，调用父类的getName 即可
        String realmName = this.getName();
        //4) 盐值
        ByteSource salt = ByteSource.Util.bytes("username");
        //需要将加密后的密码传入SimpleAuthenticationInfo，shiro会根据前端传入的密码加密，然后与传入的密码后的密码来匹配是否一致
        // SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials, realmName); //不带盐
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials, salt, realmName);//带盐
        return simpleAuthenticationInfo;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("username");
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

    /**
     * 授权需要实现的方法
     *
     * @param principalCollection
     * @return 返回带roles的 AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("first realm doGetAuthorizationInfo");
        //1. 从PrincipalCollection中获取登录用户的信息
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();

        //2. 利用登录的用户信息来获取当前用户的角色或权限
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if ("admin".equals(primaryPrincipal)) {
            roles.add("admin");
        }

        //3. 创建SimpleAuthorizationInfo，并设置roles属性
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }
}
