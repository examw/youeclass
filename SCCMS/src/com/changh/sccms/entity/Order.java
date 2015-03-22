package com.changh.sccms.entity;
// default package

import java.util.Date;

import com.changh.sccms.until.Arith;
import com.changh.sccms.until.Constant;


/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order  implements java.io.Serializable {


    // Fields    

     private Integer orderId;
     private Student student;
     private Integer id;
     private String orderNo;
     private double orderPrice; //������ʼ�۸�
     private Integer orderInvoice;		//�Ƿ�Ҫ��Ʊ��
     private Integer orderStatus;		//����״̬��0��δ֧����1����֧����2���ѹ��ڡ�
     private Integer orderPayment;		//֧����ʽ��0���ֽ�1��ѧϰ����2�����֧����
     private Date orderAddTime;
     private Date orderPayTime;
     private String orderPayType;		//֧�����͡�100:200��?
     private Integer admId;
     private Date orderDealTime;
     private double orderMoney;	//�Żݼ�
     private String orderNote;
     private Integer orderIsNeedSend;
     private String orderSendDetail;
     private double cashMoney;
     private String admUsername;
    // private Set tbSends = new HashSet(0);
    // private Set tbItemses = new HashSet(0);

     //������ʾ ,����ligerui��grid�����֧��student.id�ĸ�ʽ���м�������Ҫ��ȡ������ʾ
     private String status;
     private String payment;
     private String stuUsername;
     private String stuName;
     private String stuMobile;
    // Constructors

    /** default constructor */
    public Order() {
    }

	/** minimal constructor */
    public Order(Integer orderId, Student student, Integer id, String orderNo, double orderMoney, Integer orderStatus, Date orderAddTime) {
        this.orderId = orderId;
        this.student = student;
        this.id = id;
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
        this.orderStatus = orderStatus;
        this.orderAddTime = orderAddTime;
    }
    
    /** full constructor */
    public Order(Integer orderId, Student student, Integer id, String orderNo, double orderMoney, Integer orderInvoice, Integer orderStatus, Integer orderPayment, Date orderAddTime, Date orderPayTime, String orderPayType, Integer admId, Date orderDealTime){//, Set tbSends, Set tbItemses) {
        this.orderId = orderId;
        this.student = student;
        this.id = id;
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
        this.orderInvoice = orderInvoice;
        this.orderStatus = orderStatus;
        this.orderPayment = orderPayment;
        this.orderAddTime = orderAddTime;
        this.orderPayTime = orderPayTime;
        this.orderPayType = orderPayType;
        this.admId = admId;
        this.orderDealTime = orderDealTime;
        
        //��ʱ���ù���
        //this.tbSends = tbSends;
        //this.tbItemses = tbItemses;
    }

   
    // Property accessors

    public Integer getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getOrderMoney() {
        return this.orderMoney;
    }
    
    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderInvoice() {
        return this.orderInvoice;
    }
    
    public void setOrderInvoice(Integer orderInvoice) {
        this.orderInvoice = orderInvoice;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }
    
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderPayment() {
        return this.orderPayment;
    }
    
    public void setOrderPayment(Integer orderPayment) {
        this.orderPayment = orderPayment;
    }

    public Date getOrderAddTime() {
        return this.orderAddTime;
    }
    
    public void setOrderAddTime(Date orderAddTime) {
        this.orderAddTime = orderAddTime;
    }

    public Date getOrderPayTime() {
        return this.orderPayTime;
    }
    
    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public String getOrderPayType() {
        return this.orderPayType;
    }
    
    public void setOrderPayType(String orderPayType) {
        this.orderPayType = orderPayType;
    }

    public Integer getAdmId() {
        return this.admId;
    }
    
    public void setAdmId(Integer admId) {
        this.admId = admId;
    }

    public Date getOrderDealTime() {
        return this.orderDealTime;
    }
    
    public void setOrderDealTime(Date orderDealTime) {
        this.orderDealTime = orderDealTime;
    }

//    public Set getTbSends() {
//        return this.tbSends;
//    }
//    
//    public void setTbSends(Set tbSends) {
//        this.tbSends = tbSends;
//    }
//
//    public Set getTbItemses() {
//        return this.tbItemses;
//    }
//    
//    public void setTbItemses(Set tbItemses) {
//        this.tbItemses = tbItemses;
//    }
   
    //��ʾ��getter����
    public String getStatus()
    {
    	switch(orderStatus)
    	{
    	case Constant.WAIT_PAY:return "δ֧��";
    	case Constant.FINISH:return "��֧��";
    	case Constant.OVERTIME:return "�ѹ���";
    	case Constant.CANCELED:return "���˵�";
    	default : return "�û�ȡ��";
    		
    	}
    }
    public String getPayment()
    {
    	if(orderPayment==null) return null;
    	switch(orderPayment)
    	{
    	case Constant.CARD:return "ѧϰ��֧��";
    	case Constant.ZFB:return "֧����֧��";
    	case Constant.CASH_BALANCE:return "���֧��";
    	case Constant.REMIT_ABC:return "ũҵ���л��";
    	case Constant.REMIT_BC:return "�й����л��";
    	case Constant.REMIT_ICBC:return "�������л��";
    	case Constant.REMIT_CBC:return "�������л��";
    	case Constant.REMIT_PSBC:return "�������л��";
    	case Constant.WY:return "����֧��";
    	case Constant.CARDANDCASH:return "���֧��";
    	default : return "������ʽ֧��";
    	}
    }
    public String getStuUsername()
    {
    	return student.getStuUsername();
    }

	public String getStuName() {
		return student.getStuName();
	}

	public String getStuMobile() {
		return student.getStuMobile();
	}
    

	public double getCashMoney()
	{
		/**��ȥѧϰ��֧���Ĳ���
		 * �����ַ��� orderPayType
		 * ��������ȥ ѧϰ��֧���Ĳ���
		 */
		try{
			return Arith.sub(orderMoney , Double.parseDouble(orderPayType.split("[:]")[0]));
		}catch(Exception e)
		{
			return orderMoney;
		}
	
		
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public Integer getOrderIsNeedSend() {
		return orderIsNeedSend;
	}

	public void setOrderIsNeedSend(Integer orderIsNeedSend) {
		this.orderIsNeedSend = orderIsNeedSend;
	}

	public String getOrderSendDetail() {
		return orderSendDetail;
	}

	public void setOrderSendDetail(String orderSendDetail) {
		this.orderSendDetail = orderSendDetail;
	}

	public String getAdmUsername() {
		return admUsername;
	}

	public void setAdmUsername(String admUsername) {
		this.admUsername = admUsername;
	}
}