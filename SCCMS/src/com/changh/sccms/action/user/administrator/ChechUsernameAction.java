package com.changh.sccms.action.user.administrator;

import com.changh.sccms.service.AdministratorService;

public class ChechUsernameAction {
	//input
	private String admUsername;
	public void setAdmUsername(String admUsername) {
		this.admUsername = admUsername;
	}
	//output
	private boolean find=false;
	
	public boolean isFind() {
		return find;
	}
	//injection
	private AdministratorService administratorService;
	
	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}
	//ҵ���߼�
	public String execute() throws Exception
	{
		//�ҵ��˷���true
		if(administratorService.getAdministrator(admUsername)!=null) find=true;
		return "success";
	}
}
