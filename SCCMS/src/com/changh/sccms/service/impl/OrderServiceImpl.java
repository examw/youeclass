package com.changh.sccms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.changh.sccms.dao.OrderDAO;
import com.changh.sccms.dao.SendDAO;
import com.changh.sccms.dao.StudentDAO;
import com.changh.sccms.dao.TradeDAO;
import com.changh.sccms.entity.Administrator;
import com.changh.sccms.entity.Order;
import com.changh.sccms.entity.Send;
import com.changh.sccms.entity.Student;
import com.changh.sccms.entity.Trade;
import com.changh.sccms.service.OrderService;
import com.changh.sccms.until.Arith;
import com.changh.sccms.until.Constant;
import com.changh.sccms.until.GridDataUtil;
import com.changh.sccms.until.TimeFormat;
import com.opensymphony.xwork2.ActionContext;

public class OrderServiceImpl implements OrderService {
	private OrderDAO orderDao;
	private StudentDAO studentDao;
	private SendDAO sendDao;
	private TradeDAO tradeDao;
	public void setSendDao(SendDAO sendDao) {
		this.sendDao = sendDao;
	}
	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	public void setTradeDao(TradeDAO tradeDao) {
		this.tradeDao = tradeDao;
	}
	/**
	 * ��������ҳ������װ��ligerGrid����Ҫ��map��ʽ
	 */
	public Map<String,Object> findPageByCriteria(int page,int pagesize,String criteria,String sortname,String sortorder) throws Exception {
		//��������ҳ
		List<Order> list = orderDao.findPageByCriteria(page, pagesize,criteria,sortname, sortorder);
		//���Ҹ������¶���������
		long total = orderDao.findTotal(criteria);
		return GridDataUtil.gridMap(list, (int)total);
	}
	
	 //find by id
	public Order findById(int id) throws Exception {
		return orderDao.findById(id);
	}
	public void update(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.update(order);
	}
	/**
	 * ���¶���
	 */
	public void update(Order order,Send send,Trade trade) throws Exception {
	//�����ֶΣ�orderId,orderNo,stuId,orderMoney,orderInvoice,orderStatus,orderPayment,orderAddTime,orderPayTime,orderPayType,AdmId,orderDealTime
	//���ͱ�sendId,orderId,epcId,sendOrderId,sendStatus,sendPerson,sendDetail,sendConfirmTime,sendDetail,epcName,sendContent,sendCost,sendReceiveName,sendFullAddress,sendMobile,sendPostalCode
	//���ױ�tradeId,tradeTime,tradeMoney,tradeIp,tradeType,tradeNote,orderId,cardId,stuId
		//����ֻ���²����ֶ�
		Order realOrder = orderDao.findById(order.getOrderId());
		realOrder.setOrderMoney(order.getOrderMoney());
		realOrder.setOrderInvoice(order.getOrderInvoice());
		//���Ҫ��Ʊ����Ӽ��͵�
		if(order.getOrderInvoice()==1&&realOrder.getOrderInvoice()==0){//ԭ����Ҫ������Ҫ��Ʊ
			send.setSendAddTime(new Date());
			send.setSendDetail("��Ʊ"+send.getSendContent());
			send.setSendStatus(0);
			sendDao.save(send);
		}
		Student stu = realOrder.getStudent();
		List<Send> sendList = sendDao.findByOrderId(realOrder.getOrderId());
		//�����л��
		if(trade.getTradeType()!=null&&trade.getTradeType()==2)
		{
			//����ӳ�ֵ��¼
			stu.setStuCash(stu.getStuCash()+trade.getTradeMoney());
			studentDao.update(stu);//����ѧԱ�˻� 
			//�ӳ�ֵ��¼
			trade.setTradePattern("���л��");
			trade.setStuId(stu.getStuId());
			trade.setOrderId(realOrder.getOrderId());//�����������������صĽ���
			trade.setTradeOrderNo("HK"+TimeFormat.format(new Date()));//�Լ�д��������
			trade.setTradeType(Constant.RECHARGE);//��ֵ
			trade.setTradeCardBalance(stu.getStuCard());
			trade.setTradeCashBalance(stu.getStuCash());
			tradeDao.save(trade);
			//�ȶ��˻����
			Student realStu = studentDao.findById(stu.getStuId());//realStu�ǳ־ö�����
			double money = realOrder.getCashMoney();
			//System.out.println("1,"+realStu.getStuCash());
			if(realStu.getStuCash()>=money)
			{
				//�ܹ�֧��  ֧��
				/**
				 * �޸�ѧԱ�˻� 
				 * ��ӽ��׼�¼
				 */
				//�޸�ѧԱ�˻�
				realStu.setStuCash(Math.round((realStu.getStuCash()-money)*100)/100.0);//�������뱣��2λС��
				studentDao.update(realStu);// ��������������Բ�д
				//System.out.println("2,"+realStu.getStuCash());
				//��ӽ��׼�¼
				Trade tradeOut = new Trade();
				tradeOut.setStuId(realStu.getStuId());
				tradeOut.setTradeMoney(0-money);
				tradeOut.setOrderId(realOrder.getOrderId());//֧����ȷʵ���ڵĶ���
				tradeOut.setTradeOrderNo(realOrder.getOrderNo());
				tradeOut.setTradeTime(new Date());
				tradeOut.setTradePattern(order.getPayment());
				tradeOut.setTradeType(Constant.BUYCLASS);
				tradeOut.setTradeCardBalance(realStu.getStuCard());
				tradeOut.setTradeCashBalance(realStu.getStuCash());
				tradeOut.setTradeNote("�û���"+realStu.getStuUsername()+"�����ţ�"+realOrder.getOrderNo());
				tradeDao.save(tradeOut);
				//���¶�������ͨ�γ�
				if(sendList.size()!=0)
				{
					realOrder.setOrderStatus(Constant.FINISH);//��ʾ�ȴ��ͻ�
				}else
				{
					realOrder.setOrderStatus(Constant.FINISH);
				}
				realOrder.setOrderPayment(order.getOrderPayment());//���л��
				realOrder.setOrderPayTime(new Date());
				realOrder.setOrderPayType(realOrder.getOrderPayType()+realOrder.getCashMoney());
			}
			
		}else if(trade.getTradeType()!=null&&trade.getTradeType()==4)
		{
			Student realStu = studentDao.findById(stu.getStuId());
			double money = realOrder.getCashMoney();
			//���֧��
			//������п۳�
			//�޸�ѧԱ�˻�
			realStu.setStuCash(Math.round((realStu.getStuCash()-money)*100)/100.0);//�������뱣��2λС��
			studentDao.update(realStu);// ��������������Բ�д
			//��ӽ��׼�¼
			Trade tradeOut = new Trade();
			tradeOut.setStuId(realStu.getStuId());
			tradeOut.setTradeMoney(0-money);
			tradeOut.setOrderId(realOrder.getOrderId());//֧����ȷʵ���ڵĶ���
			tradeOut.setTradeOrderNo(realOrder.getOrderNo());
			tradeOut.setTradeTime(new Date());
			tradeOut.setTradePattern("�ʺſ۳�");
			tradeOut.setTradeType(Constant.BUYCLASS);
			tradeOut.setTradeCardBalance(realStu.getStuCard());
			tradeOut.setTradeCashBalance(realStu.getStuCash());
			tradeOut.setTradeNote("�û���"+realStu.getStuUsername()+"�����ţ�"+realOrder.getOrderId());
			tradeDao.save(tradeOut);
			//���¶�������ͨ�γ�
			if(sendList.size()==0)
			{
				realOrder.setOrderStatus(Constant.FINISH);//��ʾ�ȴ��ͻ�
			}else
			{
				realOrder.setOrderStatus(Constant.FINISH);
			}
			realOrder.setOrderPayment(Constant.CASH_BALANCE);//���֧��
			realOrder.setOrderPayTime(new Date());
			realOrder.setOrderPayType(realOrder.getOrderPayType()+realOrder.getCashMoney());
		}
		//realOrder.setOrderStatus(order.getOrderStatus());
		//���¹���Ա������Ϣ
		/*
		 * 1,���session�е�Administrator����
		 * 2������Order�е�admID,����ʱ��
		 */
		Administrator admin = (Administrator) ActionContext.getContext().getSession().get("admin");
		realOrder.setAdmId(admin.getAdmId());
		realOrder.setOrderDealTime(new Date());
		realOrder.setOrderNote(order.getOrderNote());	//��ע
		//orderDao.update(realOrder);//���¶�������������������Բ�д
	}
	/**
	 *  �������������������ҳ
	 */
	public List<Order> search(String criteria,Date addDate,Date dealDate) throws Exception {
		return orderDao.search(criteria,addDate,dealDate);
	}
	/**
	 * �������������������ҳ
	 */
	public Map<String, Object> search(int page, int pagesize, String criteria,
			Date addDate, Date dealDate, String sortname, String sortorder)
			throws Exception {
		List<Order> list=null;
		//����and����ҳ
//		if(criteria.contains("and"))
//		{
//			list = orderDao.search(criteria, addDate, dealDate);
//			return GridDataUtil.gridMap(list, list.size());
//		}else
//		{
			list = orderDao.searchPage(page, pagesize, criteria, addDate, dealDate, sortname, sortorder);
			int total =(int)orderDao.findTotal(criteria);
			return GridDataUtil.gridMap(list, total);
//		}
	}
	/**
	 * �˵����� 
	 */
	public void cancelOrder(int orderId,int mode,double card,double cash,String content) throws Exception {
		Order realOrder = orderDao.findById(orderId);
		//�˵�����
		/*
		 * Ҫ����Ȩ�޵Ĳ�ͬ�����յ��˿����˻�������ϵͳ����Ա����䲻Ҫ��
		 * �����Ƿ�Ҫ��Ʊ��Ҫ��Ʊ�鿴��Ʊ�Ƿ�����ˣ�û�м��Ϳ��ˣ������ˣ���ƱҪ�ؼĲſ����˵�
		 * 1, ��order��״̬��Ϊ���˵�
		 * 2�����session�е�Administrator����
		 * 3������Order�е�admID,����ʱ��
		 * 4������ѡ���ģʽ��֧����ʽ��money�˵������˻���
		 */
		Administrator admin = (Administrator) ActionContext.getContext().getSession().get("admin");
		realOrder.setAdmId(admin.getAdmId());
		realOrder.setAdmUsername(admin.getAdmUsername());//2013.05.17�޸�
		realOrder.setOrderDealTime(new Date());
		realOrder.setOrderStatus(Constant.CANCELED);
		realOrder.setOrderNote(content);
//		//����ѧԱ�˻�
		Student stu = realOrder.getStudent();
		//��ӽ��׼�¼
		double paycash = realOrder.getCashMoney();
		double paycard = Arith.sub(realOrder.getOrderMoney(),realOrder.getCashMoney());
		if(cash!=0&&mode==0)
		{
			stu.setStuCash(Arith.add(stu.getStuCash(),cash));
			studentDao.update(stu);
			Trade tradeOut = new Trade();
			tradeOut.setStuId(stu.getStuId());
			tradeOut.setTradeMoney(cash);
			tradeOut.setOrderId(realOrder.getOrderId());//ȷʵ���ڵĶ���
			tradeOut.setTradeOrderNo(realOrder.getOrderNo());
			tradeOut.setTradeTime(new Date());
			tradeOut.setTradePattern("�˻����˷�");
			tradeOut.setTradeType(Constant.RETURN_MONEY);
			tradeOut.setTradeCardBalance(stu.getStuCard());
			tradeOut.setTradeCashBalance(stu.getStuCash());
			if(cash<paycash)
			{
				tradeOut.setTradeNote("����Ա��"+admin.getAdmUsername()+"�������˻����˷�;�۷ѣ�"+Arith.sub(paycash,cash)+"�û���"+stu.getStuUsername()+"�����ţ�"+realOrder.getOrderId());
			}else{
				tradeOut.setTradeNote("����Ա��"+admin.getAdmUsername()+"�������˻����˷�;"+"�û���"+stu.getStuUsername()+"�����ţ�"+realOrder.getOrderNo());
			}
			tradeDao.save(tradeOut);
		}
		if(cash!=realOrder.getOrderMoney())
		{
			if(card!=0&&paycard!=0)
			{
			Trade tradeOut1 = new Trade();
			if(mode==1)
			{
				stu.setStuCard(Arith.add(stu.getStuCard(),paycard));
				studentDao.update(stu);
				tradeOut1.setTradeMoney(paycard);
			}else
			{
				stu.setStuCard(Arith.add(stu.getStuCard(),paycard));
				studentDao.update(stu);
				tradeOut1.setTradeMoney(card);
			}
			tradeOut1.setStuId(stu.getStuId());
			tradeOut1.setOrderId(realOrder.getOrderId());//ȷʵ���ڵĶ���
			tradeOut1.setTradeOrderNo(realOrder.getOrderNo());
			tradeOut1.setTradeTime(new Date());
			tradeOut1.setTradePattern("�˻�����ѧϰ��");
			tradeOut1.setTradeType(Constant.RETURN_MONEY);
			tradeOut1.setTradeCardBalance(stu.getStuCard());
			tradeOut1.setTradeCashBalance(stu.getStuCash());
			tradeOut1.setTradeNote("����Ա��"+admin.getAdmUsername()+"�������˻����˷�����ѧϰ��;"+"�û���"+stu.getStuUsername()+"�����ţ�"+realOrder.getOrderId());
			tradeDao.save(tradeOut1);
			}
		}
		//����ö������Ҫ���ͻ�δ���͵ļ��͵�ɾ��
		sendDao.deleteWhenCancelOrder(realOrder.getOrderId());
		orderDao.update(realOrder);
	}
	public void addOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}
	public Order updatePrice(String orderNo, double money) throws Exception {
		// TODO Auto-generated method stub
		Order order = orderDao.findByNo(orderNo);
		if(order!=null)
		{
			order.setOrderMoney(money);
			orderDao.update(order);
		}
		return order;
	}
	//��Ʊ����
	public Order updateForApplyInvoice(String orderNo, Send send)
			throws Exception {
		// TODO Auto-generated method stub
		Order order = orderDao.findByNo(orderNo);
		if(order!=null)
		{
			order.setOrderInvoice(1);
			orderDao.update(order);
			send.setOrder(order);
			send.setSendType(Constant.SEND_INVOICE);
			send.setSendStatus(Constant.PRESEND);
			sendDao.save(send);
		}
		return order;
	}
	public boolean findIsExistBookSend(int orderId) throws Exception {
		// TODO Auto-generated method stub
		return sendDao.findIsExist(orderId,Constant.SEND_BOOK);
	}
	/**
	 * ��ͨ����
	 */
	public boolean updateForManualPay(Order order, Send send, Trade trade)
			throws Exception {
		// TODO Auto-generated method stub
		/**
		 * ������ѡ֧����ʽ����ӽ��׼�¼
		 * ���ݼ�������Ӽ��͵�
		 * ���¶�����
		 */
		Order realOrder = orderDao.findById(order.getOrderId());
		realOrder.setOrderNote(trade.getTradeNote());
		Student stu = realOrder.getStudent();
		if(Arith.add(trade.getTradeMoney(),stu.getStuCash())<realOrder.getCashMoney())
		{
			return false; //���׵�Ǯ��Ҫ����Ǯ�٣�ֱ�ӷ���
		}
		//2013.05.17�޸�	 ��ӹ���Ա�Ĳ�����Ϣ
		Administrator admin = (Administrator) ActionContext.getContext().getSession().get("admin");
				/////////////
		//������ѡ���֧����ʽ����ӽ��׼�¼
		if(trade.getTradeType()==2)//ѡ�������������
		{
			//����ӳ�ֵ��¼
			stu.setStuCash(Arith.add(stu.getStuCash(),trade.getTradeMoney()));
			studentDao.update(stu);//����ѧԱ�˻� 
			//�ӳ�ֵ��¼
			trade.setTradePattern(order.getPayment());//��ֵ�ķ�ʽ
			trade.setStuId(stu.getStuId());
			trade.setOrderId(realOrder.getOrderId());//�����������������صĽ���
			trade.setTradeOrderNo(realOrder.getOrderNo());//�Լ�д��������
			trade.setTradeType(Constant.RECHARGE);//��ֵ
			trade.setTradeCardBalance(stu.getStuCard());
			trade.setTradeCashBalance(stu.getStuCash());
			trade.setTradeNote("����Ա��"+admin.getAdmUsername()+"�ֶ���ͨ�γ̲���(1)");	//������Ϣ
			tradeDao.save(trade);
			//�ȶ��˻����
			Student realStu = studentDao.findById(stu.getStuId());//realStu�ǳ־ö�����
			double money = realOrder.getCashMoney();
			//System.out.println("1,"+realStu.getStuCash());
			if(realStu.getStuCash()>=money)
			{
				//�ܹ�֧��  ֧��
				/**
				 * �޸�ѧԱ�˻� 
				 * ��ӽ��׼�¼
				 */
				//�޸�ѧԱ�˻�
				realStu.setStuCash(Arith.sub(realStu.getStuCash(),money));//�������뱣��2λС��
				studentDao.update(realStu);// ��������������Բ�д
				//System.out.println("2,"+realStu.getStuCash());
				//��ӽ��׼�¼
				Trade tradeOut = new Trade();
				tradeOut.setStuId(realStu.getStuId());
				tradeOut.setTradeMoney(0-money);
				tradeOut.setOrderId(realOrder.getOrderId());//֧����ȷʵ���ڵĶ���
				tradeOut.setTradeOrderNo(realOrder.getOrderNo());
				tradeOut.setTradeTime(new Date());
				tradeOut.setTradePattern(order.getPayment());
				tradeOut.setTradeType(Constant.BUYCLASS);
				tradeOut.setTradeCardBalance(realStu.getStuCard());
				tradeOut.setTradeCashBalance(realStu.getStuCash());
				tradeOut.setTradeNote("����Ա��"+admin.getAdmUsername()+"�ֶ���ͨ�γ̲���(2)���û���"+realStu.getStuUsername()+"�����ţ�"+realOrder.getOrderNo());
				tradeDao.save(tradeOut);
				//���¶�������ͨ�γ�
				if(send!=null)
				{
					realOrder.setOrderStatus(Constant.FINISH);//��ʾ�ȴ��ͻ�
					send.setSendAddTime(new Date());
					send.setOrder(realOrder);
					send.setSendContent(getSendContent(realOrder.getOrderSendDetail()));
					send.setSendStatus(Constant.PRESEND);
					send.setSendType(Constant.SEND_BOOK);
					sendDao.save(send);
				}else
				{
					realOrder.setOrderStatus(Constant.FINISH);
				}
				realOrder.setOrderPayment(order.getOrderPayment());//���л��
				realOrder.setOrderPayTime(new Date());
				realOrder.setOrderPayType(realOrder.getOrderPayType()+realOrder.getCashMoney());
			}
			
		}else if(trade.getTradeType()==4)
		{
			Student realStu = studentDao.findById(stu.getStuId());
			double money = realOrder.getCashMoney();
			if(money>realStu.getStuCash()) return false;
			//���֧��
			//������п۳�
			//�޸�ѧԱ�˻�
			realStu.setStuCash(Arith.sub(realStu.getStuCash(),money));//�������뱣��2λС��
			studentDao.update(realStu);// ��������������Բ�д
			//��ӽ��׼�¼
			Trade tradeOut = new Trade();
			tradeOut.setStuId(realStu.getStuId());
			tradeOut.setTradeMoney(0-money);
			tradeOut.setOrderId(realOrder.getOrderId());//֧����ȷʵ���ڵĶ���
			tradeOut.setTradeOrderNo(realOrder.getOrderNo());
			tradeOut.setTradeTime(new Date());
			tradeOut.setTradePattern("�ʺſ۳�");
			tradeOut.setTradeType(Constant.BUYCLASS);
			tradeOut.setTradeCardBalance(realStu.getStuCard());
			tradeOut.setTradeCashBalance(realStu.getStuCash());
			tradeOut.setTradeNote("����Ա��"+admin.getAdmUsername()+"�������û���"+realStu.getStuUsername()+"�����ţ�"+realOrder.getOrderId());
			tradeDao.save(tradeOut);
			//���¶�������ͨ�γ�
			if(send!=null)
			{
				realOrder.setOrderStatus(Constant.FINISH);//��ʾ�ȴ��ͻ�
				send.setSendAddTime(new Date());
				send.setOrder(realOrder);
				send.setSendContent(getSendContent(realOrder.getOrderSendDetail()));
				send.setSendStatus(Constant.PRESEND);
				send.setSendType(Constant.SEND_BOOK);
				sendDao.save(send);
			}else
			{
				realOrder.setOrderStatus(Constant.FINISH);
			}
			realOrder.setOrderPayment(Constant.CASH_BALANCE);//���֧��
			realOrder.setOrderPayTime(new Date());
			realOrder.setOrderPayType(realOrder.getOrderPayType().split("[:]")[0]+":"+realOrder.getCashMoney());
		}
		//realOrder.setOrderStatus(order.getOrderStatus());
		//���¹���Ա������Ϣ
		/*
		 * 1,���session�е�Administrator����
		 * 2������Order�е�admID,����ʱ��
		 */
		realOrder.setAdmId(admin.getAdmId());
		realOrder.setAdmUsername(admin.getAdmUsername());
		realOrder.setOrderDealTime(new Date());
		orderDao.update(realOrder);//���¶�������������������Բ�д
		return true;
	}
	private String getSendContent(String content)
	{
		if(content==null)return null;
		String[] list = content.split(";");
		StringBuffer buf = new StringBuffer();
		for(String s:list)
		{
			if(s.indexOf("�̲�")!=-1||s.indexOf("��")!=-1)
			{
				buf.append(s);
				buf.append(";");
			}
		}
		return buf.length()==0?null:buf.substring(0, buf.length()-1);
	}
	public Student LoadStu(int stuId) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.findById(stuId);
	}
	
	public List<Order> findByStuId(int stuId) throws Exception {
		return orderDao.findByStuId(stuId);
	}
}
