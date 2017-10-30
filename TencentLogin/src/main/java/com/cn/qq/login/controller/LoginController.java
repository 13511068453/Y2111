/**   
 * Copyright © 2017 公司名. All rights reserved.
 * 
 * @Title: LoginController.java 
 * @Prject: TencentLogin
 * @Package: com.cn.qq.login.controller 
 * @Description: TODO
 * @author: private   
 * @date: 2017年10月23日 上午10:02:19 
 * @version: V1.0   
 */
package com.cn.qq.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

/** 
 * @ClassName: LoginController 
 * @Description: TODO
 * @author: private
 * @date: 2017年10月23日 上午10:02:19  
 */
@Controller
@RequestMapping("qq")
public class LoginController {

	@RequestMapping("do_login.chm")
	public void dologin(HttpServletRequest request,HttpServletResponse response) throws IOException,
	QQConnectException{	
		response.sendRedirect(new Oauth().getAuthorizeURL(request));	
	}
	@ResponseBody
	@RequestMapping("login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws QQConnectException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application:json;charset=utf-8");
		//根据Oauth获取Token
		AccessToken accessToken=new Oauth().getAccessTokenByQueryString(request.getQueryString(), request.getParameter("state"));
		//从requset获取到code 来拿Token
		String token=null;
		
		//有效期
		long expireIn;
		//获取到的Token
		token=accessToken.getAccessToken();
		//获取有效期Token
		expireIn=accessToken.getExpireIn();
		//准备获取OpenId
		OpenID openID=new OpenID(token);
		//获取OpenId
		String openid=openID.getUserOpenID();
		
		//获取QQ空间信息
		UserInfo qzone=new UserInfo(token, openid);
		//获取用户对象
		UserInfoBean qzonUser=qzone.getUserInfo();
		response.getWriter().write(qzonUser.toString());
	}
}
