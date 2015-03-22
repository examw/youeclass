package com.changh.eschool.action.member;

import java.util.Date;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.Student;
import com.changh.eschool.until.TimeFormat;

public class PrepareRechargeAction extends BaseAction{
	private String orderNo;// ������
	private String subject;// ��������
	private String body;// ��ע����
	private String stuEmail;// ѧԱemail;����֧��Ҫ���
	private String tradeMoney;// ���׽��
	private String payment;// ֧����ʽ
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
	public String execute()
	{
		Student stu = (Student) session.get("student");
		stuEmail = stu.getStuEmail();
		String timestamp = TimeFormat.format(new Date()).substring(8);
		orderNo = payment+timestamp;
		httpRequest.setAttribute("orderNo", orderNo);
		subject = stu.getStuUsername()+"��ֵ�ֽ�ȯ";
		StringBuffer buf = new StringBuffer();
		buf.append("�����ţ�");
		buf.append(orderNo);
		buf.append(";֧������ֵ ");
		buf.append(tradeMoney);
		buf.append("Ԫ");
		httpRequest.setAttribute("subject", subject);
		httpRequest.setAttribute("body", buf.toString());
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
	}
}
