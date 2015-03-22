package com.changh.sccms.action.tips;

import java.util.Map;

import com.changh.sccms.service.TipsService;

public class TipsListAction {
	private Map<String,Object> map;
	private int page;
	private int pagesize;
	private String sortname;
	private String sortorder;
	private TipsService tipsService;
	public String execute()throws Exception
	{
		map = tipsService.findPageByCriteria(page, pagesize, sortname, sortorder, "");//(page, pagesize, sortname, sortorder);
		return "success";
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public String getSortorder() {
		return sortorder;
	}
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
	public TipsService getTipsService() {
		return tipsService;
	}
	public void setTipsService(TipsService tipsService) {
		this.tipsService = tipsService;
	}
}
