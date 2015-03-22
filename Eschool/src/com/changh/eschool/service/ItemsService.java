package com.changh.eschool.service;

import java.util.List;

import com.changh.eschool.entity.Items;
import com.changh.eschool.entity.Order;


public interface ItemsService {
	//delete
	public void delete(int itemId);
	public List<Items> findByOrderId(int orderId)throws Exception;
	/**
	 * ����ǰǰ5���ײ�
	 * @param examId
	 * @return classPckage
	 */
	public List findByExamId(int examId,int itemPType);
	/**
	 * ����������õĲ�Ʒ
	 */
	public List findBest();
	/**
	 * ��itemId������������
	 * @param itemId
	 * @return
	 */
	public Order findByItemId(int itemId);
}

