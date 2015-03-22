package com.changh.eschool.action.order;

import com.changh.eschool.service.OrderService;

public class AlipayAction {
	private String trade_no ;				//֧�������׺�
	private String out_trade_no;	        //��ȡ������
	private String total_fee ;	        //��ȡ�ܽ��
	private String subject ;//��Ʒ���ơ���������
	private String body ;//��Ʒ������������ע������
	private String buyer_email;		//���֧�����˺�
	private String trade_status;		//����״̬
	private String trade_type;		//��������
	private String extra_common_param;//�Զ������
	private OrderService orderService;
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
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
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public String getExtra_common_param() {
		return extra_common_param;
	}
	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}
	public String execute()throws Exception
	{	
		if(orderService.updateFromAliPost(out_trade_no,trade_no,total_fee,"ZFB",extra_common_param))
		return "success";
		return "fail";
	}
}
