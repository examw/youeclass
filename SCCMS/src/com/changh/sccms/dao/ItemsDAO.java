package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.Items;

public interface ItemsDAO {
	//���ݶ����Ų�ѯ������Ŀ
	public List<Items> findByOrderId(int orderId)throws Exception;
	//����
	public void save(Items item)throws Exception;
	
}
