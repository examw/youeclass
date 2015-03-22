package com.changh.eschool.action.member;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.service.OrderService;

public class DeleteOrderAction extends BaseAction{
	private int orderId;
	private OrderService orderService;
	private boolean ok=false;
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public String execute()throws Exception
	{
		//��û������ɾ��,ֻ��ȡ����,��״̬��Ϊ�� ȡ�� ״̬
		ok = orderService.cancelOrder(orderId);
		return "success";
	}
}
