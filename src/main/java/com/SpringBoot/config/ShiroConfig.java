package com.SpringBoot.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {


    //Subject,SecurityManager,Realms

    //ShiroFilterFactoryBean	创建subject
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
        /*
         * 进入页面要通关认证
         * */
        filterChainDefinitionMap.put("/bus/**", "authc");
        filterChainDefinitionMap.put("/sys/index", "authc");

        filterChainDefinitionMap.put("/customer/**", "authc");
        filterChainDefinitionMap.put("/dept/**", "authc");
        filterChainDefinitionMap.put("/goods/**", "authc");
        filterChainDefinitionMap.put("/inport/**", "authc");
        filterChainDefinitionMap.put("/loginfo/**", "authc");
        filterChainDefinitionMap.put("/menu/**", "authc");
        filterChainDefinitionMap.put("/notice/**", "authc");
        filterChainDefinitionMap.put("/outport/**", "authc");
        filterChainDefinitionMap.put("/permission/**", "authc");
        filterChainDefinitionMap.put("/provider/**", "authc");
        filterChainDefinitionMap.put("/role/**", "authc");
        filterChainDefinitionMap.put("/salesback/**", "authc");
        filterChainDefinitionMap.put("/sales/**", "authc");
        filterChainDefinitionMap.put("/user/**", "authc");

        //拦截器
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //设置登录请求
        bean.setLoginUrl("/index.html");

        //设置未授权
//		bean.setUnauthorizedUrl("");

        return bean;
    }


    //DafaultwebsecurityManager   管理器——subject
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    //创建realm对象，自定义
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        // 设置加密方式
        userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }


    //用来声明bean 相当于在spring配置文件中配置<bean>标签
    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置属性值
        //设置加密算法
        matcher.setHashAlgorithmName("MD5");
        //设置加密次数
        matcher.setHashIterations(5);
        return matcher;
    }

    /**
     * 生成随机盐
     */
    public static String randomSalt() {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(3).toHex();
        return hex;
    }
}
