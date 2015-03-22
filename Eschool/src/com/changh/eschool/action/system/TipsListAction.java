package com.changh.eschool.action.system;

import java.util.HashMap;
import java.util.Map;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.service.TipsService;

public class TipsListAction extends BaseAction{
	private Map<String,Object> map;
	private int total;
	private int page;
	private int pagesize = 10;
	private String sortname = "addtime";
	private String sortorder ="desc";
	private TipsService tipsService;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public TipsService getTipsService() {
		return tipsService;
	}
	public void setTipsService(TipsService tipsService) {
		this.tipsService = tipsService;
	}
	public String execute() throws Exception
	{
		String criteria = "";
		map = new HashMap<String,Object>();
		map.put("total", tipsService.findTotal(criteria));
		map.put("TipsList",tipsService.findPageByCriteria(page, pagesize, sortname, sortorder, criteria));
		return "success";
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
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
	
}
