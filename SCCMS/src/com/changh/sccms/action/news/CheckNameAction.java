package com.changh.sccms.action.news;

import java.util.List;

import com.changh.sccms.entity.News;
import com.changh.sccms.service.NewsService;

public class CheckNameAction {
	private boolean ok;
	private String name;
	private NewsService newsService;
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	public String execute()
	{
		List<News> list = newsService.findNewsByPro("newTitle", name);
		if(list!=null && list.size()>0){
			ok = true;
		}
		return "success";
	}
}
