package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.imp.UserServiceImp;

public class LoginInterceptor implements HandlerInterceptor{

	@Autowired
	private UserServiceImp userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//在Handler执行之前执行
		//判断是否登陆
		//从cookie中取token
		String token=CookieUtils.getCookieValue(request, "TT_TOKEN");
		//根据token获取用户信息  调用sso接口
		TbUser user=userService.getUserByToken(token);
		//娶不到用户  强制登陆
		if(null==user){
			response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_LOGIN_URL 
					+ "?redirect=" + request.getRequestURL());
			//返回false
			return false;
		}
		//取到  放行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
