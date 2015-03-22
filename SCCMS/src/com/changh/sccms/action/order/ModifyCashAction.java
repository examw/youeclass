package com.changh.sccms.action.order;

import com.changh.sccms.service.StudentService;
import com.changh.sccms.until.Constant;

public class ModifyCashAction {
	private double money;
	private int stuId;
	private int orderId;
	private int payType;
	private String content;
	private StudentService studentService;
	
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public String execute()throws Exception
	{
		String tradePattern = null;
		switch(payType)
		{
		case Constant.CARD:tradePattern = "ѧϰ��֧��";
    	case Constant.ZFB:tradePattern = "֧����֧��";
    	case Constant.CASH_BALANCE:tradePattern = "��֧�����ͻ�";
    	case Constant.REMIT_BC:tradePattern = "�й����л��";
    	case Constant.REMIT_ICBC:tradePattern = "�������л��";
    	case Constant.REMIT_CBC:tradePattern = "�������л��";
    	case Constant.REMIT_PSBC:tradePattern = "�������л��";
    	case Constant.WY:tradePattern = "����֧��";
    	case Constant.CARDANDCASH:tradePattern = "���֧��";
    	default : tradePattern = "������ʽ֧��";
		}
		if(money<0) tradePattern="�ֶ��۷�";
		if(studentService.updateAccount(stuId, money, tradePattern, content))
		{
			if(orderId!=0)
			{
				return "success";
			}else
				return "success1";
		}else
		{
			return "error";
		}
		
	}
}
