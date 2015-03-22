package com.changh.eschool.service;

import java.util.List;

import com.changh.eschool.entity.CartItem;
import com.changh.eschool.entity.Grade;
import com.changh.eschool.entity.Items;
import com.changh.eschool.entity.MyLesson;
import com.changh.eschool.entity.Order;
import com.changh.eschool.entity.Send;
import com.changh.eschool.entity.Student;

public interface OrderService {
	//������ҳ
	public List<Order> findPageByCriteria(int page,int pagesize,String criteria,String sortname,String sortorder)throws Exception;
	//findById
	public Order findById(int id)throws Exception;
	//����
	public void update(Order order) throws Exception;
	//���
	public void addOrder(Order order)throws Exception;
	public Order addOrder(Student student,String truename,String mobile,double money,List<CartItem> cart)throws Exception;
	//find by stuid
	public List<Order> findByStuId(int page,int pagesize,String criteria,String sortname,String sortorder,int stuId)throws Exception;
	//find items by orderid
	public List<Items> findItemsByOrderId(int orderId)throws Exception;
	/**
	 * ѧԱ������ײ�
	 * @param stuId
	 * @return
	 */
	public List<Integer> findMyPackage(int stuId)throws Exception ;
 	//����ѧԱ�����˵Ŀγ�
	public MyLesson findMyLesson(int stuId,int status);
		//����ѧԱ���ڵİ༶
	public List<Grade> findOverLesson(int stuId,int status);
	//��ѯһ�������µĶ�������
	public int findTotal(String criteria,int stuId)throws Exception;
	public int findTotal(String criteria,String keywords)throws Exception;
	//ɾ������id����
	public boolean deleteOrder(int orderId)throws Exception;
	//search
	public List<Order> findByKeywords(int page,int pagesize,String criteria,String sortname,String sortorder,int stuId,String keywords)throws Exception;
	//����idȡ������
	public boolean cancelOrder(int orderId)throws Exception;
	//֧������¶���
	/**
	 * @param orderId  ������
	 * @param tradeNo 	�Ա�������֧����ʽ�Ľ��׺ţ���ѧϰ������
	 * @param money	���׽��
	 * @param tradeType ��������
	 * @param body	������Ʒ�������������Ͷ���
	 * @param stu ����ѧԱ
	 * @throws Exception
	 */
	public boolean updateAfterPay(String orderId,String tradeNo,String money,String tradeType,String body,Student stu)throws Exception;
	//��ѧϰ��֧������¶���
	public boolean updatePayByCard(int orderId,Student stu)throws Exception;
	//���ִ�֧��
	public boolean updateMultipay(String orderId,String tradeNo,String money,String tradeType,String body,Student stu)throws Exception;
	//�ҳ���Щ��֧��δ�͵ļ��͵�
	public Send findSendNoAddress(int stuId)throws Exception;
	//����orderid �� stuid��ͬ���Ҷ���
	public Order findBy2Id(int orderId,int stuId)throws Exception;
	public boolean updateFromAliPost(String orderNo, String tradeNo,
			String money, String tradeType, String body)throws Exception;
	
	public Order saveOrder(Student stu,double money, Items item) throws Exception;
}
