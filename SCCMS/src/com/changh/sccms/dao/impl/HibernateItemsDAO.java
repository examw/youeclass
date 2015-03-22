package com.changh.sccms.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.changh.sccms.dao.ItemsDAO;
import com.changh.sccms.entity.Items;

public class HibernateItemsDAO extends HibernateDaoSupport implements ItemsDAO{
	//���ݶ����Ż�ö�����Ŀ�ļ���
	public List<Items> findByOrderId(int orderId) throws Exception {
		String hql = "from Items i where i.orderId= "+orderId;
		
		return this.getHibernateTemplate().find(hql);
	}
	//���Ӷ�����Ŀ
	public void save(Items item) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(item);
	}
}
