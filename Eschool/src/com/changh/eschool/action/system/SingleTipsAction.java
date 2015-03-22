package com.changh.eschool.action.system;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.Tips;
import com.changh.eschool.service.TipsService;

public class SingleTipsAction extends BaseAction{
	private Integer id;
	private Tips tips;
	private TipsService tipsService;
	public String execute() throws Exception
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
	public Tips getTips() {
		return tips;
	}
	public void setTips(Tips tips) {
		this.tips = tips;
	}
	public void setTipsService(TipsService tipsService) {
		this.tipsService = tipsService;
	}
	
}
