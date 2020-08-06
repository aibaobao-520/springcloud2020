package com.ns.util;

import com.ns.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


/**
 * Shiro工具类
 */
public class ShiroUtils {
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		//创建
		return SecurityUtils.getSubject();
	}

	public static UserDto getUserEntity() {
		//获取当前登录用户信息
		return (UserDto) SecurityUtils.getSubject().getPrincipal();
	}
	
	public static long getUserId() {
		//获取用户id
		return getUserEntity().getId();
	}

	public static void setSessionAttribute(Object key, Object value) {
		//创建session
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		//获取session
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		//判断是否登录
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		//退出
		SecurityUtils.getSubject().logout();
	}

	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}
}
