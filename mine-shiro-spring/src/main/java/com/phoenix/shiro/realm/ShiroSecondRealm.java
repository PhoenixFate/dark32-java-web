package com.phoenix.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义realm，继承自AuthenticatingRealm，用于实现认证
 */
public class ShiroSecondRealm extends AuthenticatingRealm {

    //登录成功之后，便不会再执行，需要退出登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("[second realm]: doGetAuthenticationInfo()");
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
        credentials = "4475479b4cbd89f9f6b1482848158740cd17f900";//这里的密码应该是从数据库中查出来的
        //3) realmName: 当前realm对象的name，调用父类的getName 即可
        String realmName = this.getName();
        //4) 盐值
        ByteSource salt = ByteSource.Util.bytes("username");
        //需要将加密后的密码传入SimpleAuthenticationInfo，shiro会根据前端传入的密码加密，然后与传入的密码后的密码来匹配是否一致
        // SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials, realmName); //不带盐
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("secondRealmName", credentials, salt, realmName);//带盐
        return simpleAuthenticationInfo;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "SHA1";
        Object credentials = "12345678";
        Object salt = ByteSource.Util.bytes("username");
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }


}
