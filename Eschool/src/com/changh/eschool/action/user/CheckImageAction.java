package com.changh.eschool.action.user;

import com.changh.eschool.action.BaseAction;

public class CheckImageAction extends BaseAction{
	//��֤���ַ�
	private String code;
	//�Ƿ���ȷ
	private boolean flag;
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public boolean isFlag()
	{
		return flag;
	}
	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}
	public String execute()
	{
		//��ȡsession�ϰ󶨵���֤���ַ�
		String realCode =(String)session.get("code");
		if(realCode.equalsIgnoreCase(code))
		{
			//��֤����ȷ
			flag = true;
		}else
		{
			//��֤�����
			flag=false;
		}
		return "success";
	}
}
