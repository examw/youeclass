package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.RechargeRecord;

public interface RechargeRecordDAO {
	public List<RechargeRecord> findByStuId(int stuId)throws Exception;
	public void save(RechargeRecord record)throws Exception;
	/*
	 * �Ƿ�����ѵĳ�ֵ��
	 */
	public RechargeRecord findFreeCard(int stuId)throws Exception;
	public List<RechargeRecord> findByCardId(int cardId)throws Exception;
	//��ҳ��ֵ��¼
	public List<RechargeRecord> findPageByStuId(int stuId,int page,int pagesize,String sortname,String sortorder)throws Exception;
	public long findTotalByStuId(int stuId)throws Exception;
}
