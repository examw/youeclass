package com.changh.sccms.service.impl;

import java.util.Date;
import java.util.Map;

import com.changh.sccms.dao.TipsDAO;
import com.changh.sccms.entity.Tips;
import com.changh.sccms.service.TipsService;
import com.changh.sccms.until.GridDataUtil;

public class TipsServiceImpl implements TipsService {
	private TipsDAO TipsDao;
	public TipsDAO getTipsDao() {
		return TipsDao;
	}

	public void setTipsDao(TipsDAO TipsDao) {
		this.TipsDao = TipsDao;
	}

	@Override
	public void save(Tips ac) throws Exception {
		// TODO Auto-generated method stub
		ac.setAddtime(new Date());
		TipsDao.save(ac);
	}

	@Override
	public void update(Tips ac) throws Exception {
		// TODO Auto-generated method stub
		TipsDao.update(ac);
	}

	@Override
	public Tips findById(Integer TipsId) throws Exception {
		// TODO Auto-generated method stub
		return TipsDao.findById(TipsId);
	}

	@Override
	public Map<String,Object> findPageByCriteria(int page, int pagesize,
			String sortname, String sortorder, String criteria)
			throws Exception {
		// TODO Auto-generated method stub
		return GridDataUtil.gridMap(TipsDao.findPageByCriteria(page, pagesize, sortname, sortorder, criteria), 
				(int) TipsDao.findTotal(criteria));
	}

	@Override
	public long findTotal(String criteria) throws Exception {
		// TODO Auto-generated method stub
		return TipsDao.findTotal(criteria);
	}
	@Override
	public void delete(Integer tipsId) throws Exception {
		// TODO Auto-generated method stub
		TipsDao.delete(tipsId);
	}
}
