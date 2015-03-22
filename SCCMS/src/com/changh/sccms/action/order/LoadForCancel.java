package com.changh.sccms.action.order;

import com.changh.sccms.entity.Order;
import com.changh.sccms.service.OrderService;
import com.changh.sccms.until.Constant;

public class LoadForCancel {
	private int orderId;
	private String statusForShow;
	private String paymentForShow;
	private String card;
	private String cash;
	private Order order;
	
	public Order getOrder() {
		return order;
	}
	public String getStatusForShow() {
		return statusForShow;
	}
	public String getPaymentForShow() {
		return paymentForShow;
	}
	public String getCard() {
		return card;
	}
	public String getCash() {
		return cash;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public String execute()throws Exception
	{
		order  = orderService.findById(orderId);
		statusForShow = order.getOrderStatus()==Constant.FINISH?"��֧��":"��֧��";
		//��Ʊ�Ļ�ȷ��
		//
		String pay = order.getOrderPayType();
		//�����ַ���
		String[] money = pay.split("[|]");
		card = money[0];
		cash = money[1];
		if("0".equals(card)&&!"0".equals(cash)) paymentForShow="�ֽ�֧��";
		else if(!"0".equals(card)&&"0".equals(cash)) paymentForShow ="ѧϰ��֧��";
		else paymentForShow ="���֧��";
		return "success";
	}
}
