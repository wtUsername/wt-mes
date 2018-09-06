package com.wt.config;

import com.google.common.base.Optional;

import com.wt.sys.domain.User;
import com.wt.sys.service.IUserService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jtech.marble.shiro.ShiroUser;
import com.jtech.marble.util.PasswordUtil;


/**
 * <p> </p>
 *
 * @author mengk
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class SecurityUserRealm extends AuthorizingRealm {


    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUserRealm.class);
    @Autowired
    private IUserService iUserService;

    public SecurityUserRealm() {
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher(PasswordUtil.HASH_ALGORITHM);
        md5CredentialsMatcher.setHashIterations(PasswordUtil.HASH_INTERATIONS);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //查出是否有此用户
        Optional<User> userOpt = iUserService.selectByName(token.getUsername());
        if (userOpt.isPresent()) {
            final User user = userOpt.get();
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            final String username = user.getUserName();
            String credentials = user.getPassWord();
            ShiroUser shiroUser = ShiroUser.builder()
                    .id(user.getId())
                    .account(user.getWorkNo())
                    .name(user.getUserName())
                    .build();

//            String saltSource = user.getSalt();
//
//            byte[] salt = EncodeUtil.decodeHex(org.apache.commons.lang3.StringUtils.upperCase(saltSource));
//            ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
            return new SimpleAuthenticationInfo(shiroUser, credentials, super.getName());
        }
        return null;
    }
}
