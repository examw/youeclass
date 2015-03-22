package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.Items;
import com.changh.eschool.entity.Order;

public interface ItemDAO {
	public void save(Items item)throws Exception;
	public void delete(int itemId);
	public List<Items> findByOrderId(int orderId)throws Exception;
	/**
	 * ���ҵ�ǰ���������������ǰ10�İ༶���ײ�
	 * @param examId
	 * @return
	 */
	public List findByExamId(int examId,int itemPType);
	/**
	 * ����ǰ10�����Ա��������ײ�
	 * @return
	 */
	public List findHot();
	/**
	 * ��itemId������������
	 * @param itemId
	 * @return
	 */
	public Order findByItemId(int itemId);

}
