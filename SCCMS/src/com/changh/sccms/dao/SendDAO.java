package com.changh.sccms.dao;

import java.util.Date;
import java.util.List;

import com.changh.sccms.entity.Order;
import com.changh.sccms.entity.Send;
import com.changh.sccms.until.Constant;

public interface SendDAO {
	//������������
	public static final String ALL="where 1=1 ";
	public static final String PRESEND=" where sendStatus = "+Constant.PRESEND;
	public static final String SENDING=" where sendStatus = "+Constant.SENDING;
	public static final String SENT=" where sendStatus = "+Constant.SENT;
	//������ҳ
	public List<Send> findPageByCriteria(int page,int pagesize,String criteria,String sortname,String sortorder)throws Exception;
	//findById
	public Send findById(int sendId)throws Exception;
	//findByOrderId
	public List<Send> findByOrderId(int orderId)throws Exception;
	//����
	public void update(Send send)throws Exception;
	//��ѯ�ƶ������µļ�¼��
	public long findTotal(String criteria)throws Exception;
	//����
	public List<Send> search(String str,String searchName)throws Exception;
	//����
	public void save(Send send)throws Exception;
	//��һ����������
	public List<Send> searchPage(String criteria,Date addDate,Date confirmDate)throws Exception;
	public List<Send> searchPage(int page,int pagesize,String criteria,Date addDate,Date confirmDate,String sortname,String sortorder)throws Exception;
	//ɾ��
	public void deleteWhenCancelOrder(int orderId)throws Exception;
	//�Ƿ�������鵥
	public boolean findIsExist(int orderId,int sendType)throws Exception;
}
