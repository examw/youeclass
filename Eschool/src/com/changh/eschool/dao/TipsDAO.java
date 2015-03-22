package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.Tips;

public interface TipsDAO {
	public void save(Tips ac)throws Exception;
	public void update(Tips ac)throws Exception;
	public Tips findById(Integer tipsId)throws Exception;
	public List<Tips> findPageByCriteria(int page,int pagesize,String sortname,String sortorder,String criteria)throws Exception;
	public long findTotal(String criteria)throws Exception;
	public void delete(Integer tipsId)throws Exception;
}
