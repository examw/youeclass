package com.changh.sccms.action.system;

import com.changh.sccms.action.BaseAction;

/**
 * �ǳ����������session
 * @author Administrator
 *
 */
public class LogoutAction extends BaseAction{
	public String execute()
	{
		session.clear();
		return "success";
	}
}
