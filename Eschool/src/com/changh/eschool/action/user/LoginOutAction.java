package com.changh.eschool.action.user;

import javax.servlet.http.Cookie;

import com.changh.eschool.action.BaseAction;

public class LoginOutAction extends BaseAction{
	private boolean ok;

	public boolean isOk() {
		return ok;
	}
	public String execute()throws Exception
	{
		ok = session.remove("student")!=null;
		//����cookie
				Cookie token = new Cookie("youeclass_token","");
				token.setMaxAge(0);
				token.setPath("/");
				httpResponse.addCookie(token);
		return "success";
	}
}
