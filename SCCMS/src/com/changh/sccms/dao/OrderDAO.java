package com.changh.sccms.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.changh.sccms.entity.Order;
import com.changh.sccms.until.Constant;

public interface OrderDAO {
	//������������
	public static final String ALLORDER="where 1=1 ";
	public static final String ALLPAID=" where o.orderStatus = "+Constant.FINISH;	//
	public static final String ALLNOTPAID=" where o.orderStatus = "+Constant.WAIT_PAY;
	public static final String ALLOVERTIME=" where o.orderStatus = "+Constant.OVERTIME;
	public static final String CANCELED=" where o.orderStatus = "+Constant.CANCELED;
	public static final String USERCANCEL=" where o.orderStatus = "+Constant.USERCANCEL;
	//������ҳ
	public List<Order> findPageByCriteria(int page,int pagesize,String criteria,String sortname,String sortorder)throws Exception;
	//��ȡָ���������ܼ�¼��
	public long findTotal(String criteria)throws Exception;
	//findbyid
	public Order findById(int id) throws Exception;
	//findByno
	public Order findByNo(String orderNo)throws Exception;
	//���
	public void save(Order order)throws Exception;
	//����
	public void update(Order order) throws Exception;
	//���� ��һ����������
	public List<Order> search(String criteria,Date addDate,Date dealDate)throws Exception;
	public List<Order> searchPage(int page,int pagesize,String criteria,Date addDate,Date dealDate,String sortname,String sortorder)throws Exception;
	/**
	 * ����ѧԱ�Ķ���
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	public List<Order> findByStuId(int stuId) throws Exception;
}
