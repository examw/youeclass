package com.changh.sccms.action.tips;

import com.changh.sccms.entity.Tips;
import com.changh.sccms.service.TipsService;

public class LoadTipsAction {
	private Integer id;
	private TipsService tipsService;
	private Tips tips;
	public String execute()throws Exception
	{
		tips = tipsService.findById(id);
		return "success";
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
	public void setTipsService(TipsService tipsService) {
		this.tipsService = tipsService;
	}
	public Tips getTips() {
		return tips;
	}
	public void setTips(Tips tips) {
		this.tips = tips;
	}
}
