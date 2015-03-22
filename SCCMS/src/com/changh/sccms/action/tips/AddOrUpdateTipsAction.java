package com.changh.sccms.action.tips;

import java.util.Date;

import com.changh.sccms.entity.Tips;
import com.changh.sccms.service.TipsService;

public class AddOrUpdateTipsAction {
	private Tips tips;
	private TipsService tipsService;
	private boolean ok = false;
	public String execute() throws Exception
	{
		if(tips.getId()==null||tips.getId()==0)
		{
			tips.setAddtime(new Date());
			tipsService.save(tips);
		}else
		{
			tips.setAddtime(new Date());
			tipsService.update(tips);
		}
		ok= true;
		return "success";
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public Tips getTips() {
		return tips;
	}

	public void setTips(Tips tips) {
		this.tips = tips;
	}

	public TipsService getTipsService() {
		return tipsService;
	}

	public void setTipsService(TipsService tipsService) {
		this.tipsService = tipsService;
	}
}
