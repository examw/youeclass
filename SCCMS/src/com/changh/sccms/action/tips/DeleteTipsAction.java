package com.changh.sccms.action.tips;

import com.changh.sccms.service.TipsService;

public class DeleteTipsAction {
	private boolean ok = false;
	private Integer id;
	private TipsService tipsService;
	public String execute()throws Exception
	{
		tipsService.delete(id);
		ok = true;
		return "success";
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipsService getTipsService() {
		return tipsService;
	}
	public void setTipsService(TipsService TipsService) {
		this.tipsService = TipsService;
	}
}
