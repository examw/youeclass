package com.changh.eschool.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.changh.eschool.dao.ClassDetailDAO;
import com.changh.eschool.dao.ClassPackageDAO;
import com.changh.eschool.dao.GradeDAO;
import com.changh.eschool.dao.ItemDAO;
import com.changh.eschool.dao.OrderDAO;
import com.changh.eschool.dao.SendDAO;
import com.changh.eschool.dao.StudentDAO;
import com.changh.eschool.dao.TradeDAO;
import com.changh.eschool.entity.CartItem;
import com.changh.eschool.entity.ClassPackage;
import com.changh.eschool.entity.Deal;
import com.changh.eschool.entity.Grade;
import com.changh.eschool.entity.Items;
import com.changh.eschool.entity.MyLesson;
import com.changh.eschool.entity.Order;
import com.changh.eschool.entity.Send;
import com.changh.eschool.entity.StuDeal;
import com.changh.eschool.entity.Student;
import com.changh.eschool.entity.Trade;
import com.changh.eschool.service.OrderService;
import com.changh.eschool.until.Arith;
import com.changh.eschool.until.Constant;
import com.changh.eschool.until.TimeFormat;

public class OrderServiceImpl implements OrderService {
	private OrderDAO orderDao;
	private StudentDAO studentDao;
	private SendDAO sendDao;
	private ClassPackageDAO classPackageDao;
	private GradeDAO gradeDao;
	private ClassDetailDAO classDetailDao;
	private ItemDAO itemDao;
	private TradeDAO tradeDao;


	public ItemDAO getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}

	public ClassDetailDAO getClassDetailDao() {
		return classDetailDao;
	}

	public void setClassDetailDao(ClassDetailDAO classDetailDao) {
		this.classDetailDao = classDetailDao;
	}

	public GradeDAO getGradeDao() {
		return gradeDao;
	}

	public void setGradeDao(GradeDAO gradeDao) {
		this.gradeDao = gradeDao;
	}

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

	// find by id
	public Order findById(int id) throws Exception {
		return orderDao.findById(id);
	}

	/**
	 * ���¶���    
	 */
	public void update(Order order) throws Exception {
		// ����ֻ���²����ֶ�
		orderDao.update(order);
	}

	public void addOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	public java.util.List<Order> findByStuId(int page, int pagesize,
			String criteria, String sortname, String sortorder, int stuId)
			throws Exception {

		criteria = criteria + " and o.student.stuId = " + stuId;
		return orderDao.findPageByCriteria(page, pagesize, criteria, sortname,
				sortorder);
	}

	public List<Order> findPageByCriteria(int page, int pagesize,
			String criteria, String sortname, String sortorder)
			throws Exception {
		// TODO Auto-generated method stu
		return orderDao.findPageByCriteria(page, pagesize, criteria, sortname,
				sortorder);
	}

	public List<Items> findItemsByOrderId(int orderId) throws Exception {
		return orderDao.findItemsByOrderId(orderId);
	}

	public List<Integer> findMyPackage(int stuId) throws Exception {
		List<Integer> list1= new ArrayList<Integer>();
		List<Order> list = orderDao.findItemsByStuId(stuId, Constant.FINISH);
		if (list != null) {
			for (Order order : list) {
				List<Items> it = orderDao.findItemsByOrderId(order
						.getOrderId());	
				for (Items item : it) {
					if(item.getItemPType()==0){
						list1.add(item.getProductId());
					}	
				}
			}
			return list1;
		}
		return null;
	}
	public MyLesson findMyLesson(int stuId, int status) {
		MyLesson lesson = new MyLesson();
		List<ClassPackage> cp = new ArrayList<ClassPackage>();
		List<Grade> grade = new ArrayList<Grade>();
		List<Items> items = new ArrayList<Items>();

		try {
			List<Order> list = orderDao.findItemsByStuId(stuId, status);
			if (list != null) {
				for (Order order : list) {
					List<Items> it = orderDao.findItemsByOrderId(order
							.getOrderId());
					for (Items item : it) {
						item.setOrderTime(order.getOrderAddTime());
						items.add(item);
					}
				}
				
			}

			for (Items i : items) {
				if (i.getItemPType() == 0) {
					ClassPackage c = null;
					c = classPackageDao.findById(i.getProductId());
					if(c.getPkgLMatureDate().getTime()>new Date().getTime()){
						c.setOrderTime(i.getOrderTime());
						c.setItemId(i.getItemId());
						List<Grade> gradeList = classPackageDao.findByPkgId(i
								.getProductId());
						List<Grade> myGradeList = new ArrayList<Grade>();
						for (Grade myGrade : gradeList) {
							if (myGrade.getGradeDueTime().getTime() >= new Date()
									.getTime()) {
								myGrade.setClassDetails(classDetailDao.findByGid(
										myGrade.getGradeId(), 0, 0));
								myGradeList.add(myGrade);
							}
							c.setGrade(myGradeList);
						}
						cp.add(c);
					}
					
				} else if (i.getItemPType() == 1) {
					Grade g = null;
					g = gradeDao.findById(i.getProductId());
					g.setItemId(i.getItemId());
					g.setOrderTime(i.getOrderTime());
					if (g.getGradeDueTime().getTime() >= new Date().getTime()) {
						g.setClassDetails(classDetailDao.findByGid(
								g.getGradeId(), 0, 0));
						grade.add(g);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		lesson.setClassPackages(cp);
		lesson.setGrade(grade);
		return lesson;
	}

	public List<Grade> findOverLesson(int stuId, int status) {
		List<Grade> grade = new ArrayList<Grade>();
		List<Items> items = new ArrayList<Items>();

		try {
			List<Order> list = orderDao.findItemsByStuId(stuId, status);
			if (list != null) {
				for (Order order : list) {
					List<Items> it = orderDao.findItemsByOrderId(order
							.getOrderId());
					for (Items item : it) {
						item.setOrderTime(order.getOrderAddTime());
						items.add(item);
					}
				}
			}

			for (Items i : items) {
				if (i.getItemPType() == 0) {
					List<Grade> gradeList = classPackageDao.findByPkgId(i
							.getProductId());
					for (Grade myGrade : gradeList) {
						if (myGrade.getGradeDueTime().getTime() < new Date()
								.getTime()) {
							grade.add(myGrade);
						}

					}
				} else if (i.getItemPType() == 1) {
					Grade g = null;
					g = gradeDao.findById(i.getProductId());
					g.setOrderTime(i.getOrderTime());
					if (g.getGradeDueTime().getTime() < new Date().getTime()) {
						grade.add(g);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grade;
	}

	public void setClassPackageDao(ClassPackageDAO classPackageDao) {
		this.classPackageDao = classPackageDao;
	}

	public Order addOrder(Student stu, String truename, String mobile,
			double money, List<CartItem> cart) throws Exception {
		// TODO Auto-generated method stub
		if(!((truename!=null&&truename.equals(stu.getStuName()))&&(mobile!=null&&mobile.equals(stu.getStuMobile()))))
		{	// ����ѧ����Ϣ
			stu.setStuName(truename);
			stu.setStuMobile(mobile);
			studentDao.update(stu);
		}
		// ��Ӷ��������ݿ�
		Order order = new Order();
		order.setStudent(stu);
		order.setOrderInvoice(0);
		order.setOrderStatus(Constant.WAIT_PAY);
		order.setOrderMoney(money);
		order.setOrderAddTime(new Date());
		order.setOrderPayType("0:0");
		order.setOrderPrice(money);
		String present = getSendDetail(cart);
		order.setOrderSendDetail(present);
		order.setOrderIsNeedSend(isNeedSend(present)==null?0:1);
		int orderid = orderDao.save(order);
		// ////////////////////////////////
		order.setOrderId(orderid);
		// ////////////
		// ѭ�����ﳵ���ɶ�����Ŀ
		for (CartItem cartitem : cart) {
			 Items item = cartitem.getItem();
			 item.setOrderId(orderid);
			 itemDao.save(item);	
			 //ѧ��Э��   
			 Deal deal =classPackageDao.findDealByPkgId(item.getProductId());
			 if(item.getItemPType()==0&&deal!=null){
				StuDeal sd = new StuDeal();
				sd.setDeal(classPackageDao.findDealByPkgId(item.getProductId()));
				sd.setStuDealId(classPackageDao.findStuDealId());
				sd.setStuId(stu.getStuId());
				sd.setPkgName(item.getItemName());
				sd.setStuDealStatus(Constant.NOSTUDEAL);
				classPackageDao.saveStuDeal(sd);
			 }
		}
		return order;
	}
	
	
	public Order saveOrder(Student stu,double money, Items item) throws Exception {
		// TODO Auto-generated method stub
		// ��Ӷ��������ݿ�
		Order order = new Order();
		order.setStudent(stu);
		order.setOrderInvoice(0);
		order.setOrderStatus(Constant.WAIT_PAY);
		order.setOrderMoney(money);
		order.setOrderAddTime(new Date());
		order.setOrderPayType("0:0");
		order.setOrderPrice(money);
		order.setOrderStatus(Constant.FINISH);// ����״̬
		order.setOrderPayTime(new Date());
		String present = item.getItemPresent();
		order.setOrderSendDetail(present);
		order.setOrderIsNeedSend(isNeedSend(present)==null?0:1);
		int orderid = orderDao.save(order);
		// ////////////////////////////////
		order.setOrderId(orderid);
		// ////////////
		// ѭ�����ﳵ���ɶ�����Ŀ
		
		item.setOrderId(orderid);
		itemDao.save(item);	
		//ѧ��Э��   
		Deal deal =classPackageDao.findDealByPkgId(item.getProductId());
		if(item.getItemPType()==0&&deal!=null){
			StuDeal sd = new StuDeal();
			sd.setDeal(classPackageDao.findDealByPkgId(item.getProductId()));
			sd.setStuDealId(classPackageDao.findStuDealId());
			sd.setStuId(stu.getStuId());
			sd.setPkgName(item.getItemName());
			sd.setStuDealStatus(Constant.NOSTUDEAL);
			classPackageDao.saveStuDeal(sd);
		}
		return order;
	}
	
	private String getSendDetail(List<CartItem> cart)
	{
		StringBuffer buf = new StringBuffer();
		for (CartItem cartitem : cart) {
			String str = cartitem.getItem().getItemPresent();
			if(str!=null&&!str.trim().equals(""))
			{
				buf.append(str);
				buf.append(";");
			}
		}
		if(buf.length()<1) return "";	//2013.05.17�޸�
		return buf.substring(0, buf.length()-1);
	}
	
	public int findTotal(String criteria, int stuId) throws Exception {
		// TODO Auto-generated method stub
		criteria = criteria + " and o.student.stuId = " + stuId;
		return (int) orderDao.findTotal(criteria);
	}

	public boolean deleteOrder(int orderId) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.delete(orderId);
	}
	/**
	 * ���ݹؼ��ֲ��ң��п����Ƕ����Ż��߿γ�����
	 */
	public List<Order> findByKeywords(int page, int pagesize, String criteria,
			String sortname, String sortorder, int stuId, String keywords)
			throws Exception {
		// TODO Auto-generated method stub
		if (keywords == null) {
			return null;
		}
		keywords = keywords.replace("%", "\\%");
		StringBuffer buf = new StringBuffer();
		//����ǿγ�����
		if(keywords.matches("[\\s|\\S]*[\u4E00-\u9FA5]+[\\s|\\S]*"))//ƥ������
		{
			return orderDao.findPageByItemKeywords(page, pagesize, stuId,keywords,
					"order_addTime", sortorder);
		}
			buf.append(criteria);
			buf.append(" and o.student.stuId =");
			buf.append(stuId);
			buf.append(" and o.orderNo ='");
			buf.append(keywords.trim());
			buf.append("'");
			return orderDao.findPageByCriteria(page, pagesize, buf.toString(),
				sortname, sortorder);
	}

	public int findTotal(String criteria, String keywords) throws Exception {
		// TODO Auto-generated method stub
		if (keywords == null) {
			return 0;
		}
		keywords = keywords.replace("%", "\\%");
		StringBuffer buf = new StringBuffer();
		//����ǿγ�����
		if(keywords.matches("[\\s|\\S]*[\u4E00-\u9FA5]+[\\s|\\S]*"))//ƥ������
		{
			buf.append(", Items i ");
			buf.append(criteria);
			buf.append(" and i.itemName like '%");
			buf.append(keywords);
			buf.append("%' and o.orderId = i.orderId ");
		}else
		{
			buf.append(criteria);
			buf.append(" and o.orderNo ='");
			buf.append(keywords.trim());
			buf.append("'");
		}
		return (int) orderDao.findTotal(buf.toString());
	}

	public boolean cancelOrder(int orderId) throws Exception {
		// TODO Auto-generated method stub
		Order order = orderDao.findById(orderId);
		if (order == null)
			return false;
		order.setOrderStatus(Constant.USERCANCEL);
		// orderDao.update(order);
		return true;
	}

	public boolean updateAfterPay(String orderNo, String tradeNo, String money,
			String tradeType, String body, Student stu) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 1����ӽ��׼�¼���ֽ��˻���ֵ 2��С��ֱ�ӿ�ͨ ���¶�����״̬�� 2�����Ƿ���Ӽ��ͼ�¼�� 3����ӽ��׼�¼
		 */
		Order order = null;
		int payment = 0;
		Student copy = null;
		order = orderDao.findByNo(orderNo);
		if(order!=null)
		{
			copy = (Student) order.getStudent();
		}else
		{
			copy = (Student)stu.clone();
		}
		copy.setStuCash(Arith.add(copy.getStuCash() , Double.parseDouble(money)));
		studentDao.update(copy);// �����˻�
		stu.setStuCash(copy.getStuCash());//����session
		Trade tradeIn = new Trade();
		if (tradeType.equals("ZFB")) {
			tradeIn.setTradeOrderNo("ZFB" + TimeFormat.format(new Date()).substring(8));
			tradeIn.setTradePattern("֧����֧��");
			tradeIn.setTradeNote("֧������ֵ");
			payment = Constant.ZFB;

		} else if (tradeType.equals("WY"))  {
			tradeIn.setTradeOrderNo("WY" + TimeFormat.format(new Date()).substring(8));
			tradeIn.setTradePattern("����֧��");
			tradeIn.setTradeNote("������ֵ");
			payment = Constant.WY;
		} else if(tradeType.equals("CFT"))
		{
			tradeIn.setTradeOrderNo("CFT" + TimeFormat.format(new Date()).substring(8));
			tradeIn.setTradePattern("�Ƹ�֧ͨ��");
			tradeIn.setTradeNote("�Ƹ�ͨ��ֵ");
			payment = Constant.CFT;
		}
		tradeIn.setCardId(tradeNo); // ֧������������Ƹ�ͨ�Ľ��׵���
		tradeIn.setTradeMoney(Double.parseDouble(money));
		if(order!=null){tradeIn.setOrderId(order.getOrderId());}
		tradeIn.setStuId(stu.getStuId());
		tradeIn.setTradeCardBalance(copy.getStuCard());
		tradeIn.setTradeCashBalance(copy.getStuCash());
		tradeIn.setTradeType(Constant.RECHARGE);// ��ֵ
		tradeIn.setTradeTime(new Date());
		tradeIn.setTradeIp(ServletActionContext.getRequest().getRemoteAddr());
		tradeDao.save(tradeIn);
		if (order != null) {
			if(order.getOrderStatus()!=Constant.WAIT_PAY)
				return false;
			if(order.getCashMoney()!=order.getOrderMoney()) payment = Constant.CARDANDCASH;
			if (order.getRemainMoney() <= copy.getStuCash()) {
				// ����
				copy.setStuCash(Arith.sub(copy.getStuCash() , order.getRemainMoney()));
				studentDao.update(copy);
				stu.setStuCash(copy.getStuCash());
				Trade tradeOut = new Trade();
				tradeOut.setOrderId(order.getOrderId());
				tradeOut.setTradeOrderNo(order.getOrderNo());
				tradeOut.setStuId(copy.getStuId());
				tradeOut.setTradeMoney(0 - order.getRemainMoney());
				tradeOut.setOrderId(order.getOrderId());// ֧����ȷʵ���ڵĶ���
				tradeOut.setTradeOrderNo(order.getOrderNo());
				tradeOut.setTradeTime(new Date());
				tradeOut.setTradePattern("�˺ſ۳�");
				tradeOut.setTradeType(Constant.BUYCLASS);
				tradeOut.setTradeCardBalance(copy.getStuCard());
				tradeOut.setTradeCashBalance(copy.getStuCash());
				tradeOut.setTradeNote("�û���" + copy.getStuUsername() + "�����ţ�"
						+ order.getOrderNo());
				tradeDao.save(tradeOut);
				// �޸Ķ���
				if (order.getCashMoney() <= 500) {
					order.setOrderPayment(payment);
				} else {
					
					order.setOrderPayment(Constant.CASH_BALANCE);
				}
				if (order.getOrderIsNeedSend() != 0) {
					order.setOrderStatus(Constant.FINISH);// ����״̬
					// ����ͻ���
					Send send = new Send();
					send.setOrderId(order.getOrderId());// ���ù�������
					send.setSendStatus(Constant.PRESEND);// ���ü���״̬
					send.setSendType(Constant.SEND_BOOK);//���ü�������
					send.setSendAddTime(new Date());// �������ʱ��
					send.setSendContent(isNeedSend(order.getOrderSendDetail()));
					sendDao.save(send);// �������ݿ�
				} else {
					order.setOrderStatus(Constant.FINISH);
				}
				order.setOrderPayTime(new Date());
				order.setOrderPayType(getPayDetail(0, order, Double.parseDouble(money)));
				return true;
			}else
			{
				//������֧������
				return false;
			}
		}else
		{
			//������ǳ�ֵ
			return false;
		}
	}
	private String isNeedSend(String content) {
		// �����͵�������϶���û����
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
	// ��֧����post��������Ϣ����������session��cookie,����д������
	public boolean updateFromAliPost(String orderNo, String tradeNo,
			String money, String tradeType, String body) throws Exception {
		// TODO Auto-generated method stub
		Order order = null;
		int payment = 0;
		Student copy = null;
		order = orderDao.findByNo(orderNo);
		if(order!=null)
		{
			copy = (Student) order.getStudent();
		}else
		{
			copy = studentDao.findByUsername(body);
		}
		copy.setStuCash(Arith.add(copy.getStuCash() , Double.parseDouble(money)));
		studentDao.update(copy);// �����˻�
		Trade tradeIn = new Trade();
		if (tradeType.equals("ZFB")) {
			tradeIn.setTradeOrderNo("ZFB" + TimeFormat.format(new Date()).substring(8));
			tradeIn.setTradePattern("֧����֧��");
			tradeIn.setTradeNote("֧������ֵ");
			payment = Constant.ZFB;

		} else if (tradeType.equals("WY"))  {
			tradeIn.setTradeOrderNo("WY" + TimeFormat.format(new Date()).substring(8));
			tradeIn.setTradePattern("����֧��");
			tradeIn.setTradeNote("������ֵ");
			payment = Constant.WY;
		} else if(tradeType.equals("CFT"))
		{
			tradeIn.setTradeOrderNo("CFT" + TimeFormat.format(new Date()).substring(8));
			tradeIn.setTradePattern("�Ƹ�֧ͨ��");
			tradeIn.setTradeNote("�Ƹ�ͨ��ֵ");
			payment = Constant.CFT;
		}
		tradeIn.setCardId(tradeNo); // ֧������������Ƹ�ͨ�Ľ��׵���
		tradeIn.setTradeMoney(Double.parseDouble(money));
		if(order!=null){tradeIn.setOrderId(order.getOrderId());}
		tradeIn.setStuId(copy.getStuId());
		tradeIn.setTradeCardBalance(copy.getStuCard());
		tradeIn.setTradeCashBalance(copy.getStuCash());
		tradeIn.setTradeType(Constant.RECHARGE);// ��ֵ
		tradeIn.setTradeTime(new Date());
		tradeIn.setTradeIp(ServletActionContext.getRequest().getRemoteAddr());
		tradeDao.save(tradeIn);
		if (order != null) {
			if(order.getOrderStatus()!=Constant.WAIT_PAY)
				return false;
			if(order.getCashMoney()!=order.getOrderMoney()) payment = Constant.CARDANDCASH;
			if (order.getRemainMoney() <= copy.getStuCash()) {
				// ����
				copy.setStuCash(Arith.sub(copy.getStuCash() , order.getRemainMoney()));
				studentDao.update(copy);
				Trade tradeOut = new Trade();
				tradeOut.setOrderId(order.getOrderId());
				tradeOut.setTradeOrderNo(order.getOrderNo());
				tradeOut.setStuId(copy.getStuId());
				tradeOut.setTradeMoney(0 - order.getRemainMoney());
				tradeOut.setOrderId(order.getOrderId());// ֧����ȷʵ���ڵĶ���
				tradeOut.setTradeOrderNo(order.getOrderNo());
				tradeOut.setTradeTime(new Date());
				tradeOut.setTradePattern("�˺ſ۳�");
				tradeOut.setTradeType(Constant.BUYCLASS);
				tradeOut.setTradeCardBalance(copy.getStuCard());
				tradeOut.setTradeCashBalance(copy.getStuCash());
				tradeOut.setTradeNote("�û���" + copy.getStuUsername() + "�����ţ�"
						+ order.getOrderNo());
				tradeDao.save(tradeOut);
				// �޸Ķ���
				if (order.getCashMoney() <= 500) {
					order.setOrderPayment(payment);
				} else {
					
					order.setOrderPayment(Constant.CASH_BALANCE);
				}
				if (order.getOrderIsNeedSend() != 0) {
					order.setOrderStatus(Constant.FINISH);// ����״̬
					// ����ͻ���
					Send send = new Send();
					send.setOrderId(order.getOrderId());// ���ù�������
					send.setSendStatus(Constant.PRESEND);// ���ü���״̬
					send.setSendType(Constant.SEND_BOOK);//���ü�������
					send.setSendAddTime(new Date());// �������ʱ��
					send.setSendContent(isNeedSend(order.getOrderSendDetail()));
					sendDao.save(send);// �������ݿ�
				} 
				order.setOrderPayTime(new Date());
				order.setOrderPayType(getPayDetail(0, order, Double.parseDouble(money)));
				return true;
			}else
			{
				//������֧������
				return false;
			}
		}else
		{
			//������ǳ�ֵ
			return false;
		}
	}
	
	public boolean updatePayByCard(int orderId, Student stu) throws Exception {
		// TODO Auto-generated method stub
		Order order = null;
		Student copy = null;
		order = orderDao.findBy2Id(orderId,stu.getStuId());//����
		if(order!=null&&order.getOrderStatus()==Constant.WAIT_PAY)
		{
			String present = isNeedSend(order.getOrderSendDetail());//������������
			copy = order.getStudent();//���ݿ���ʵ�ʵ�ѧԱ����
			double cardValue = copy.getStuCard();
			double cashValue = copy.getStuCash();
			if(cardValue>0)	//ѧԱѧϰ����Ϊ0��
			{
				double remainMoney = order.getRemainMoney();
				if(cardValue<remainMoney) //����ѧϰ��������֧������ʣ��֧�����
				{
					//��ѧϰ�������۳�
					copy.setStuCard(0.0);
					studentDao.update(copy);
					//��ӽ��׼�¼
					Trade tradeOut = new Trade();
					tradeOut.setOrderId(order.getOrderId());
					tradeOut.setTradeOrderNo(order.getOrderNo());
					tradeOut.setStuId(copy.getStuId());
					tradeOut.setTradeMoney(0 - cardValue); //���׽�����ѧϰ�������
					tradeOut.setOrderId(order.getOrderId());// ֧����ȷʵ���ڵĶ���
					tradeOut.setTradeOrderNo(order.getOrderNo());
					tradeOut.setTradeTime(new Date());
					tradeOut.setTradePattern("ѧϰ���˻�֧��");
					tradeOut.setTradeType(Constant.BUYCLASS);
					tradeOut.setTradeCardBalance(copy.getStuCard());
					tradeOut.setTradeCashBalance(copy.getStuCash());
					tradeOut.setTradeNote("ѧϰ�����֧��"+cardValue+"Ԫ");
					tradeDao.save(tradeOut);
					//���¶�����֧�����
					order.setOrderPayType(getPayDetail(cardValue, order, 0));
					//��ʱ������û��֧�����
					//����session�е�ѧϰ�����
					stu.setStuCard(0);
				}
				else	//ѧϰ������㹻֧��ʣ����
				{
					//�۳�ѧϰ��֧���Ľ��
					copy.setStuCard(Arith.sub(cardValue, remainMoney));
					studentDao.update(copy);
					//��ӽ��׼�¼
					Trade tradeOut = new Trade();
					tradeOut.setOrderId(order.getOrderId());
					tradeOut.setTradeOrderNo(order.getOrderNo());
					tradeOut.setStuId(copy.getStuId());
					tradeOut.setTradeMoney(0 - remainMoney); //���׽�����ѧϰ�������
					tradeOut.setOrderId(order.getOrderId());// ֧����ȷʵ���ڵĶ���
					tradeOut.setTradeOrderNo(order.getOrderNo());
					tradeOut.setTradeTime(new Date());
					tradeOut.setTradePattern("ѧϰ���˻�֧��");
					tradeOut.setTradeType(Constant.BUYCLASS);
					tradeOut.setTradeCardBalance(copy.getStuCard());
					tradeOut.setTradeCashBalance(copy.getStuCash());
					tradeOut.setTradeNote("ѧϰ�����֧��"+remainMoney+"Ԫ");
					tradeDao.save(tradeOut);
					//���¶�����֧�����
					order.setOrderPayType(getPayDetail(remainMoney, order, 0));
					//��ʱ�����Ѿ�֧�����
					if(order.getCashMoney()==0) //ȫ����ѧϰ��֧�����
					{
						order.setOrderPayment(Constant.CARD);
					}else
					{
						order.setOrderPayment(Constant.CARDANDCASH);//���֧��
					}
					if ((present!=null&&!present.equals(""))) {
						// ����ͻ���
						Send send = new Send();
						send.setOrderId(order.getOrderId());// ���ù�������
						send.setSendStatus(Constant.PRESEND);// ���ü���״̬
						send.setSendAddTime(new Date());// �������ʱ��
						send.setSendContent(present);
						send.setSendType(Constant.SEND_BOOK);
						sendDao.save(send);// �������ݿ�
					}
					order.setOrderStatus(Constant.FINISH);// ����״̬
					order.setOrderPayTime(new Date());
					//����session��ѧϰ�����
					stu.setStuCard(copy.getStuCard());
					return true; //�Ѿ�֧����ɣ���������ִ��
				}
			}
			//��ѧϰ��������֧��ʣ����ʱ��ִ�е�����
			if(cashValue>0)	//	�ֽ�������֧��
			{
				double remainMoney2= order.getRemainMoney();//��ʱ��remainMoney�����Ѿ��仯��
				if(cashValue<remainMoney2)	//������֧��ʣ����
				{
					//���ֽ��˻������۳�
					copy.setStuCash(0);
					studentDao.update(copy);
					//��ӽ��׼�¼
					Trade tradeOut = new Trade();
					tradeOut.setOrderId(order.getOrderId());
					tradeOut.setTradeOrderNo(order.getOrderNo());
					tradeOut.setStuId(copy.getStuId());
					tradeOut.setTradeMoney(0 - cashValue); //���׽������ֽ�����
					tradeOut.setOrderId(order.getOrderId());// ֧����ȷʵ���ڵĶ���
					tradeOut.setTradeOrderNo(order.getOrderNo());
					tradeOut.setTradeTime(new Date());
					tradeOut.setTradePattern("�ֽ��˻�֧��");
					tradeOut.setTradeType(Constant.BUYCLASS);
					tradeOut.setTradeCardBalance(copy.getStuCard());
					tradeOut.setTradeCashBalance(copy.getStuCash());
					tradeOut.setTradeNote("�ֽ����֧��"+cashValue+"Ԫ");
					tradeDao.save(tradeOut);
					//���¶�����֧�����
					order.setOrderPayType(getPayDetail(0, order, cashValue));
					//��ʱ������û��֧�����
					//����session�е��ֽ����
					stu.setStuCash(0);
				}
				else	//�ֽ�����㹻֧��ʣ����
				{
					//�۳��ֽ�֧���Ľ��
					copy.setStuCash(Arith.sub(cashValue, remainMoney2));
					studentDao.update(copy);
					//��ӽ��׼�¼
					Trade tradeOut = new Trade();
					tradeOut.setOrderId(order.getOrderId());
					tradeOut.setTradeOrderNo(order.getOrderNo());
					tradeOut.setStuId(copy.getStuId());
					tradeOut.setTradeMoney(0 - remainMoney2); //���׽�����ѧϰ�������
					tradeOut.setOrderId(order.getOrderId());// ֧����ȷʵ���ڵĶ���
					tradeOut.setTradeOrderNo(order.getOrderNo());
					tradeOut.setTradeTime(new Date());
					tradeOut.setTradePattern("�ֽ��˻�֧��");
					tradeOut.setTradeType(Constant.BUYCLASS);
					tradeOut.setTradeCardBalance(copy.getStuCard());
					tradeOut.setTradeCashBalance(copy.getStuCash());
					tradeOut.setTradeNote("�ֽ����֧��"+remainMoney2+"Ԫ");
					tradeDao.save(tradeOut);
					//���¶�����֧�����
					order.setOrderPayType(getPayDetail(0, order, remainMoney2));
					//��ʱ�����Ѿ�֧�����
					if(order.getCashMoney()==order.getOrderMoney()) //ȫ����ѧϰ��֧�����
					{
						order.setOrderPayment(Constant.CASH_BALANCE);
					}else
					{
						order.setOrderPayment(Constant.CARDANDCASH);//���֧��
					}
					if ((present!=null&&!present.equals(""))) {
						// ����ͻ���
						Send send = new Send();
						send.setOrderId(order.getOrderId());// ���ù�������
						send.setSendStatus(Constant.PRESEND);// ���ü���״̬
						send.setSendAddTime(new Date());// �������ʱ��
						send.setSendContent(present);
						send.setSendType(Constant.SEND_BOOK);
						sendDao.save(send);// �������ݿ�
					}
					order.setOrderStatus(Constant.FINISH);// ����״̬
					order.setOrderPayTime(new Date());
					//����session���ֽ����
					stu.setStuCash(copy.getStuCash());
					return true; //�Ѿ�֧����ɣ���������ִ��
				}
			}
			return false; //��Ȼû��֧����
		}
		return false;//����������
}
	private String getPayDetail(double card,Order order,double cash)
	{
		String paytype = order.getOrderPayType(); //��ȡ֧�����飬������ʽΪ   100:0;
		//����֧������
		String[] arr = paytype.split("[:]");
		//��������쳣
		double card1 = 0,cash1=0;
		try{
			card1 = Double.valueOf(arr[0]);
		}catch(Exception e)
		{
			card1 = 0;
		}
		try{
			cash1 = Double.valueOf(arr[1]);
		}catch(Exception e)
		{
			cash1 = 0;
		}
		return (card1+card)+":"+(cash+cash1);
	}
	public Send findSendNoAddress(int stuId) throws Exception {
		// TODO Auto-generated method stub
		return sendDao.findNoAddress(stuId);
	}
	public Order findBy2Id(int orderId, int stuId) throws Exception {

		// TODO Auto-generated method stub
		return orderDao.findBy2Id(orderId, stuId);
	}
	/**
	 * ���ִ�֧��
	 */
	public boolean updateMultipay(String orderNo, String tradeNo, String money,
			String tradeType, String body, Student stu) throws Exception {
		// TODO Auto-generated method stub
		Order order = null;
		int payment = 0;
		Student copy = null;
		order = orderDao.findByNo(orderNo.substring(orderNo.indexOf("-")+1));
		if(order!=null)
		{
			copy = (Student) order.getStudent();
		}else
		{
			copy = (Student)stu.clone();
		}
		copy.setStuCash(Arith.add(copy.getStuCash() , Double.parseDouble(money)));
		studentDao.update(copy);// �����˻�
		stu.setStuCash(copy.getStuCash());//����session
		Trade tradeIn = new Trade();
		if (tradeType.equals("ZFB")) {
			tradeIn.setTradeOrderNo("ZFB" + TimeFormat.format(new Date()).substring(8));
			tradeIn.setTradePattern("֧����֧��");
			tradeIn.setTradeNote("֧������ֵ");
			payment = Constant.ZFB;

		} else if (tradeType.equals("WY"))  {
			tradeIn.setTradeOrderNo("WY" + TimeFormat.format(new Date()).substring(8));
			tradeIn.setTradePattern("����֧��");
			tradeIn.setTradeNote("�����ִ�֧����ֵ");
			payment = Constant.WY;
		} else if(tradeType.equals("CFT"))
		{
			tradeIn.setTradeOrderNo("CFT" + TimeFormat.format(new Date()).substring(8));
			tradeIn.setTradePattern("�Ƹ�֧ͨ��");
			tradeIn.setTradeNote("�Ƹ�ͨ��ֵ");
			payment = Constant.CFT;
		}
		tradeIn.setCardId(tradeNo); // ֧������������Ƹ�ͨ�Ľ��׵���
		tradeIn.setTradeMoney(Double.parseDouble(money));
		if(order!=null){tradeIn.setOrderId(order.getOrderId());}
		tradeIn.setStuId(stu.getStuId());
		tradeIn.setTradeCardBalance(copy.getStuCard());
		tradeIn.setTradeCashBalance(copy.getStuCash());
		tradeIn.setTradeType(Constant.RECHARGE);// ��ֵ
		tradeIn.setTradeTime(new Date());
		tradeIn.setTradeIp(ServletActionContext.getRequest().getRemoteAddr());
		tradeDao.save(tradeIn);
		if (order != null) {
			if(order.getOrderStatus()!=Constant.WAIT_PAY)
				return false;
			if(order.getCashMoney()!=order.getOrderMoney()) payment = Constant.CARDANDCASH;
			if (order.getRemainMoney() <= copy.getStuCash()) {
				// ����
				copy.setStuCash(Arith.sub(copy.getStuCash() , order.getRemainMoney()));
				studentDao.update(copy);
				stu.setStuCash(copy.getStuCash());
				Trade tradeOut = new Trade();
				tradeOut.setOrderId(order.getOrderId());
				tradeOut.setTradeOrderNo(order.getOrderNo());
				tradeOut.setStuId(copy.getStuId());
				tradeOut.setTradeMoney(0 - order.getRemainMoney());
				tradeOut.setOrderId(order.getOrderId());// ֧����ȷʵ���ڵĶ���
				tradeOut.setTradeOrderNo(order.getOrderNo());
				tradeOut.setTradeTime(new Date());
				tradeOut.setTradePattern("�˺ſ۳�");
				tradeOut.setTradeType(Constant.BUYCLASS);
				tradeOut.setTradeCardBalance(copy.getStuCard());
				tradeOut.setTradeCashBalance(copy.getStuCash());
				tradeOut.setTradeNote("���ִ�֧�����û���" + copy.getStuUsername() + "�����ţ�"
						+ order.getOrderNo());
				tradeDao.save(tradeOut);
				// �޸Ķ���
					order.setOrderPayment(payment);
				if (order.getOrderIsNeedSend() != 0) {
					order.setOrderStatus(Constant.FINISH);// ����״̬
					// ����ͻ���
					Send send = new Send();
					send.setOrderId(order.getOrderId());// ���ù�������
					send.setSendStatus(Constant.PRESEND);// ���ü���״̬
					send.setSendType(Constant.SEND_BOOK);//���ü�������
					send.setSendAddTime(new Date());// �������ʱ��
					send.setSendContent(isNeedSend(order.getOrderSendDetail()));
					sendDao.save(send);// �������ݿ�
				} else {
					order.setOrderStatus(Constant.FINISH);
				}
				order.setOrderPayTime(new Date());
				order.setOrderPayType(getPayDetail(0, order, Double.parseDouble(money)));
				return true;
			}else
			{
				//������֧���������۳��ֽ��˻����
				double cashValue = copy.getStuCash();
				copy.setStuCash(0.0);
				studentDao.update(copy);
				stu.setStuCash(copy.getStuCash());
				Trade tradeOut = new Trade();
				tradeOut.setOrderId(order.getOrderId());
				tradeOut.setTradeOrderNo(order.getOrderNo());
				tradeOut.setStuId(copy.getStuId());
				tradeOut.setTradeMoney(0-cashValue);
				tradeOut.setOrderId(order.getOrderId());// ֧����ȷʵ���ڵĶ���
				tradeOut.setTradeOrderNo(order.getOrderNo());
				tradeOut.setTradeTime(new Date());
				tradeOut.setTradePattern("�˺ſ۳�");
				tradeOut.setTradeType(Constant.BUYCLASS);
				tradeOut.setTradeCardBalance(copy.getStuCard());
				tradeOut.setTradeCashBalance(copy.getStuCash());
				tradeOut.setTradeNote("���ִ�֧�����û���" + copy.getStuUsername() + "�����ţ�"
						+ order.getOrderNo());
				tradeDao.save(tradeOut);
				// �޸Ķ���֧�����
				order.setOrderPayType(getPayDetail(0, order, Double.parseDouble(money)));
				return true;
			}
		}else
		{
			//������ǳ�ֵ
			return false;
		}
	}
}
