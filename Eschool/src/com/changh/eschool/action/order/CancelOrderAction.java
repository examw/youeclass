package com.changh.eschool.action.order;

import com.changh.eschool.service.OrderService;

public class CancelOrderAction {
	private int orderId;

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public String execute()throws Exception
	{
		//�û���ȡ����ɾ���� ���ǰ�״̬��Ϊȡ��
		return "success";
	}
}
