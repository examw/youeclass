package com.changh.sccms.service.impl;

import java.util.HashMap;
import java.util.List;

import com.changh.sccms.dao.NewsDAO;
import com.changh.sccms.entity.News;
import com.changh.sccms.entity.Province;
import com.changh.sccms.service.NewsService;

public class NewsServiceImpl implements NewsService{
	private NewsDAO newsDao;
	
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return newsDao.delete(id);
	}

	@Override
	public boolean saveOrupdate(News news) {
		// TODO Auto-generated method stub
		return newsDao.saveOrupdate(news);
	}

	@Override
	public News findById(int id) {
		// TODO Auto-generated method stub
		return newsDao.findById(id);
	}

	@Override
	public List<News> findListNews(int page, int pagesize, String sortname,
			String sortorder,HashMap<String ,Object> map) {
		// TODO Auto-generated method stub
		return newsDao.findListNews(page, pagesize, sortname, sortorder,map);
	}

	@Override
	public int getTotal(Integer newsClassId ,Integer provinceId,Integer examId) {
		// TODO Auto-generated method stub
		return newsDao.getTotal(newsClassId, provinceId, examId);
	}

	@Override
	public List<Province> findByPro(String pro, Object value) {
		// TODO Auto-generated method stub
		return newsDao.findByPro(pro, value);
	}

	public void setNewsDao(NewsDAO newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public List<News> findAll(Integer newsClassId,Integer examId, Integer provinceId,
			int page, int pagesize, String sortname, String sortorder)
			throws Exception {
		// TODO Auto-generated method stub
		return newsDao.findAll(newsClassId, examId,provinceId, page, pagesize, sortname, sortorder);
	}

	@Override
	public List<News> findNewsByPro(String pro, Object value) {
		// TODO Auto-generated method stub
		return this.newsDao.findNewsByPro(pro, value);
	}
	@Override
	public List<News> findTopNewsByPro(Integer num,String pro, Object value) {
		// TODO Auto-generated method stub
		if(num == null || num.equals(0)) num =200;
		return this.newsDao.findNewsByPro(num,pro, value);
	}
	
	@Override
	public List<News> findAll(Integer newsClassId, Integer examId,
			Integer provinceId,String content, int page, int pagesize, String sortname,
			String sortorder) throws Exception {
		return newsDao.findAll(newsClassId, examId,provinceId,content, page, pagesize, sortname, sortorder);
	}
	@Override
	public int getTotal(Integer newsClassId, Integer provinceId,
			Integer examId, String content) {
		return newsDao.getTotal(newsClassId, provinceId, examId,content);
	}
}
