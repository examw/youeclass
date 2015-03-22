package com.changh.sccms.action.user.administrator;

import com.changh.sccms.action.BaseAction;
import com.changh.sccms.entity.Administrator;
import com.changh.sccms.service.AdministratorService;

public class ChangePasswordAction extends BaseAction{
	private String oldPwd;
	private String newPwd;
	private boolean ok;
	private AdministratorService administratorService;
	
	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String  execute()throws Exception
	{
		//��ȡsession�е�admin����
		Administrator admin = (Administrator) session.get("admin");
		if(admin==null)
		{
			return "fail";
		}
		//ԭ���벻�Բ��ܸ�
		if(!admin.getAdmPassword().equals(oldPwd))
		{
			ok=false;
		}else
		{
			admin.setAdmPassword(newPwd);
			administratorService.updateAdministrator(admin);
			ok=true;
		}
		return "success";
	}
}
