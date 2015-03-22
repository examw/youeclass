package com.changh.eschool.action.order;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.Student;
import com.changh.eschool.service.OrderService;
//ѧϰ��֧�� ��֧�����ֻ�ȫ��
public class CardPayAction extends BaseAction{
		//�̻�������
		private int orderId ;
		//֧����ʽ
		private OrderService orderService;
		
		public int getOrderId() {
			return orderId;
		}
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
		public void setOrderService(OrderService orderService) {
			this.orderService = orderService;
		}
		public String execute()throws Exception
		{
			Student stu= (Student) session.get("student");
			if(orderService.updatePayByCard(orderId, stu))
			{
				return "success";
			}else
			{
				return "success1";
			}
			
		}
}
