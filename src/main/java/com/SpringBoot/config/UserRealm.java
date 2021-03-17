package com.SpringBoot.config;

import javax.servlet.http.HttpSession;

import com.SpringBoot.service.MenuService;
import com.SpringBoot.service.UserService;
import com.SpringBoot.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringBoot.bean.User;

import java.util.HashSet;
import java.util.Set;

//自定义realm
public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private HttpSession session;

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		// 角色列表
		Set<String> roles = new HashSet<>();
		// 菜单列表
		Set<String> menus = new HashSet<>();

		// 管理员拥有所有权限
		if (user.getType() == Constants.USER_TYPE_ADMIN)
		{
			info.addRole("root");
			info.addStringPermission("*:*:*");
		}
		else
		{
			menus = menuService.selectPerCodesByUserId(user.getId());
			// 权限加入AuthorizationInfo认证对象
			info.setStringPermissions(menus);
		}

		roles = menuService.selectRolesByUid(user.getId());
		info.addRoles(roles);
		
		return info;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	
		UsernamePasswordToken userToken =(UsernamePasswordToken) token;
		String username = userToken.getUsername();
		User user = userService.selectUserByLoginName(username);
		
		if(user==null) {
			return null;
		}else {
			session.setAttribute("username",user.getName());
			session.setAttribute("type",user.getType());
			session.setAttribute("user", user);
			ByteSource bytes = ByteSource.Util.bytes(user.getSalt());
			return new SimpleAuthenticationInfo(user,user.getPwd(),bytes ,this.getName());
		}
		
	}
	
	
}
