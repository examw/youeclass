package com.changh.eschool.action.order;

import java.util.Date;
import java.util.List;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.Items;
import com.changh.eschool.entity.Order;
import com.changh.eschool.entity.Student;
import com.changh.eschool.service.OrderService;
import com.changh.eschool.until.Constant;
import com.changh.eschool.until.TimeFormat;

public class PreparePayAction extends BaseAction {
	private int orderId;// ����id
	private String orderNo;// ������
	private OrderService orderService;
	private String subject;// ��������
	private String body;// ��ע����
	private String stuEmail;// ѧԱemail;����֧��Ҫ���
	private String tradeMoney;// ���׽��
	private String payment;// ֧����ʽ
	private String amount;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	public String getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(String tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String execute() throws Exception {
		Student stu = (Student) session.get("student");
		stuEmail = stu.getStuEmail();
		Order order = orderService.findBy2Id(orderId, stu.getStuId());
		if (order != null && order.getOrderStatus() == Constant.WAIT_PAY) {
			orderNo = order.getOrderNo();
			List<Items> list = orderService.findItemsByOrderId(order
					.getOrderId());
			if(amount!=null&&!amount.equals(""))	//�ִ�֧��
			{
				tradeMoney = amount;
				orderNo = TimeFormat.format(new Date())+"-"+orderNo;//�ִ�֧��������Ҫ��һ�� 
			}else
			{
				tradeMoney = String.valueOf(order.getRemainMoney());
			}
			subject = stu.getStuUsername() + "����������У" + list.size() + "���γ�";
			body = getBody(list);
			httpRequest.setAttribute("orderNo", orderNo);
			httpRequest.setAttribute("subject", subject);
			httpRequest.setAttribute("body", body);
			httpRequest.setAttribute("tradeMoney", tradeMoney);
			httpRequest.setAttribute("email", stuEmail);
			if (payment.equals("ZFB")) // ֧����֧��
			{
				return "zfb_success";
			} else if (payment.equals("WY"))// ����֧��
			{
				return "wy_success";
			} else if (payment.equals("CFT"))// �Ƹ�ͨ
			{
				return "cft_success";
			} else {
				return "success";
			}
		} else
			return "fail";
	}

	private String getBody(List<Items> list) {
		StringBuffer buf = new StringBuffer();
		for (Items i : list) {
			buf.append(i.getItemName());
			buf.append(";");
		}
		return buf.substring(0, buf.length() - 1);
	}
}
