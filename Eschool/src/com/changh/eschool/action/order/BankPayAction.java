package com.changh.eschool.action.order;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.Student;
import com.changh.eschool.service.OrderService;

public class BankPayAction extends BaseAction{
	  private String v_oid;		// ������
	  private String v_pmode;		// ֧����ʽ����˵������"���г������ÿ�"
	  private String v_pstatus;	// ֧�������20֧����ɣ�30֧��ʧ�ܣ�
	  private String v_pstring;	// ��֧�������˵�����ɹ�ʱ��v_pstatus=20��Ϊ"֧���ɹ�"��֧��ʧ��ʱ��v_pstatus=30��Ϊ"֧��ʧ��"
	  private String v_amount;		// ����ʵ��֧�����
	  private String v_moneytype;	// ����
	  private String v_md5str;		// MD5У����
	  private String remark1;		// ��ע1
	  private String remark2;		// ��ע2
	  private String trade_type;
	  
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getV_oid() {
		return v_oid;
	}
	public void setV_oid(String v_oid) {
		this.v_oid = v_oid;
	}
	public String getV_pmode() {
		return v_pmode;
	}
	public void setV_pmode(String v_pmode) {
		this.v_pmode = v_pmode;
	}
	public String getV_pstatus() {
		return v_pstatus;
	}
	public void setV_pstatus(String v_pstatus) {
		this.v_pstatus = v_pstatus;
	}
	public String getV_pstring() {
		return v_pstring;
	}
	public void setV_pstring(String v_pstring) {
		this.v_pstring = v_pstring;
	}
	public String getV_amount() {
		return v_amount;
	}
	public void setV_amount(String v_amount) {
		this.v_amount = v_amount;
	}
	public String getV_moneytype() {
		return v_moneytype;
	}
	public void setV_moneytype(String v_moneytype) {
		this.v_moneytype = v_moneytype;
	}
	public String getV_md5str() {
		return v_md5str;
	}
	public void setV_md5str(String v_md5str) {
		this.v_md5str = v_md5str;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public String execute()throws Exception
	{
		trade_type = (String) httpRequest.getAttribute("trade_type");
		Student stu= (Student) session.get("student");
		if(orderService.updateAfterPay(v_oid,"WY",v_amount,trade_type,remark1,stu))
			return "success";
		else
			return "fail";
	}
}
