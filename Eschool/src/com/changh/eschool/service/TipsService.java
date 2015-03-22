package com.changh.eschool.service;

import java.util.List;

import com.changh.eschool.entity.Tips;

public interface TipsService {
	public void save(Tips ac)throws Exception;
	public void update(Tips ac)throws Exception;
	public Tips findById(Integer TipsId)throws Exception;
	public List<Tips> findPageByCriteria(int page,int pagesize,String sortname,String sortorder,String criteria)throws Exception;
	public long findTotal(String criteria) throws Exception ;
	public void delete(Integer tipsId) throws Exception;
}
