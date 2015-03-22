package com.changh.sccms.action.news;

import java.util.List;

import com.changh.sccms.entity.ExamCategory;
import com.changh.sccms.entity.NewClass;
import com.changh.sccms.entity.Province;
import com.changh.sccms.service.ExamCategoryService;
import com.changh.sccms.service.NewClassService;
import com.changh.sccms.service.NewsService;

public class NewsListAction {
	private Object[] obj =new Object[1];
	private List<NewClass> list;
	private NewsService newsService;
	private NewClassService newClassService;
	private ExamCategoryService examCategoryService;
	private List<ExamCategory> examList;
	public void setExamCategoryService(ExamCategoryService examCategoryService) {
		this.examCategoryService = examCategoryService;
	}
	
	public String getProvince(){
		Province p = newsService.findByPro("provinceId", 1001).get(0);
		p.setChildren(newsService.findByPro("parentId", 1001));
		obj[0]=p;
		return "success";
	}
	
	public  String examList() throws Exception{
		setExamList(examCategoryService.examList());
		return "success";
	}
	
	public String getNewClass(){
		list= newClassService.findByPro("parentId", 0);
		return "success";
	}
	public List<NewClass> getList() {
		return list;
	}

	public void setList(List<NewClass> list) {
		this.list = list;
	}

	public Object[] getObj() {
		return obj;
	}
	public void setObj(Object[] obj) {
		this.obj = obj;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setNewClassService(NewClassService newClassService) {
		this.newClassService = newClassService;
	}

	public List<ExamCategory> getExamList() {
		return examList;
	}

	public void setExamList(List<ExamCategory> examList) {
		this.examList = examList;
	}
	
}
