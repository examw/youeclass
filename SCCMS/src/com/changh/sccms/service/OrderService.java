package com.changh.sccms.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.changh.sccms.entity.Order;
import com.changh.sccms.entity.Send;
import com.changh.sccms.entity.Student;
import com.changh.sccms.entity.Trade;

public interface OrderService {
	//������ҳ
	public Map<String,Object> findPageByCriteria(int page,int pagesize,String criteria,String sortname,String sortorder)throws Exception;
	//findById
	public Order findById(int id)throws Exception;
	//����
	public void update(Order order)throws Exception;
	public void update(Order order,Send send,Trade trade) throws Exception;
	//����ҳ����������
	public List<Order> search(String criteria,Date addDate,Date dealDate)throws Exception;
	//��ҳ����������
	public Map<String,Object> search(int page,int pagesize,String criteria,Date addDate,Date dealDate,String sortname,String sortorder)throws Exception;
	//�˵�
	public void cancelOrder(int orderId,int mode,double card,double cash,String content)throws Exception;
	//���
	public void addOrder(Order order)throws Exception;
	//�޸ļ۸�
	public Order updatePrice(String orderNo,double money)throws Exception;
	//���뷢Ʊ
	public Order updateForApplyInvoice(String orderNo,Send send)throws Exception;
	//�ж��ǲ�������ͻ���
	public boolean findIsExistBookSend(int orderId)throws Exception;
	//ȫ�ֶ���ͨ����
	public boolean updateForManualPay(Order order,Send send,Trade trade)throws Exception;
	//�û���ϸ��Ϣ
	public Student LoadStu(int stuId)throws Exception;
	/**
	 * ����ѧԱ�Ķ���
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	public List<Order> findByStuId(int stuId) throws Exception;
}
