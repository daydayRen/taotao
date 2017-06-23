package com.taotao.portal.service.imp;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

@Service
public class UserServiceImp implements UserService {

	
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_USER_TOKEN}")
	public String SSO_USER_TOKEN;
	
	@Value("${SSO_LOGIN_URL}")
	public String SSO_LOGIN_URL;
	
	@Override
	public TbUser getUserByToken(String token) {
		try {
			//获取token中json数据
			String json=HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+token);
			//转换格式
			TaotaoResult result=TaotaoResult.formatToPojo(json, TbUser.class);
			if(result.getStatus()==200){
				TbUser user=(TbUser) result.getData();
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
