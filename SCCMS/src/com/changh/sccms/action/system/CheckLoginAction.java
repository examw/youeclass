package com.changh.sccms.action.system;

import com.changh.sccms.action.BaseAction;

public class CheckLoginAction extends BaseAction{
	public String execute()
	{
		//���session���й���Ա���󣬱�ʾ�Ѿ���¼��ֱ����ת��mainҳ��
		if(session.get("admin")!=null)
		{
			return "success2";
		}
		return "success";
	}
}
