package com.changh.sccms.service;

import java.util.HashMap;
import java.util.List;

import com.changh.sccms.entity.News;
import com.changh.sccms.entity.Province;

public interface NewsService {
	//删除
	public boolean delete(int id);
	//修改or
	public boolean saveOrupdate(News news);
	//查询
	public News findById(int id);
	//分页查询
	public List<News> findListNews(final int page, final int pagesize,
			final String sortname, final String sortorder,final HashMap<String ,Object> map);
	public int getTotal(Integer newsClassId ,Integer provinceId,Integer examId);
	
	//省份查询
	public List<Province> findByPro(String pro,Object value);

	public List<News> findAll(final Integer newsClassId , final Integer examId ,final Integer provinceId,final int page, final int pagesize, final String sortname,
			final String sortorder) throws Exception ;
	
	public List<News> findNewsByPro(String pro, Object value);
	
	//2014.07.08
	public List<News> findAll(final Integer newsClassId , final Integer examId ,final Integer provinceId,String content,final int page, final int pagesize, final String sortname,
			final String sortorder) throws Exception ;
	public int getTotal(Integer newsClassId ,Integer provinceId,Integer examId,String content);
	
	public List<News> findTopNewsByPro(Integer num,String pro, Object value);
}
