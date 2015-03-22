package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.Trade;

public interface TradeDAO {
	//����
	public void save(Trade trade)throws Exception;
	
	//�������� ����ҳ
	public List<Trade> findPageByStuId(int stuId,int tradeType,int page,int pagesize,String sortname,String sortorder)throws Exception;
	public long findTotalByStuId(int stuId,int tradeType)throws Exception;
}
