package com.changh.sccms.entity;
// default package

import java.util.Date;

import com.changh.sccms.until.Constant;


/**
 * Send entity. @author MyEclipse Persistence Tools
 */

public class Send  implements java.io.Serializable {


    // Fields    

     private Integer sendId;
     private Order order;
     private Integer id;
     
     private Integer epcId;
    // private ExpressCompany company;
     //private Address address;
     private Integer sendStatus; //����״̬ 0��δ�ͣ�1�����ͣ�2�����ͣ�3���ѻؼġ��˵�ʱ�á�
     private Date sendTime;
     private Date sendAddTime;
     private String sendPerson;
     private Date sendConfirmTime;
     private String sendDetail;//������ϸ
     private String sendContent;//��������
     private double sendCost;	//���ͻ���
     private String epcName;
     private Integer sendType;//�������� 0:�̲ģ�1����Ʊ��2������
     private String sendExpressNo;//���͵Ŀ�ݵ���
     //
     private String orderNo;
     private String status;
     private String type;
     private String sendReceiveName;
     private String sendFullAddress;
     private String sendMobile;
     private String sendPostalCode;
    // Constructors

    /** default constructor */
    public Send() {
    }

	/** minimal constructor */
    public Send(Integer sendId, Order order, Integer id, Integer sendStatus) {
        this.sendId = sendId;
        this.order = order;
        this.id = id;
        this.sendStatus = sendStatus;
    }
    
    /** full constructor 
    public Send(Integer sendId, Order order, Integer id, ExpressCompany company, String sendOrderId, Integer sendStatus, Date sendTime, Date sendAddTime,String sendPerson, Date sendConfirmTime,String sendDetail,double sendCost) {
        this.sendId = sendId;
        this.order = order;
        this.id = id;
        this.company= company;
        this.sendOrderId = sendOrderId;
        this.sendStatus = sendStatus;
        this.sendTime = sendTime;
        this.sendAddTime = sendAddTime;
        this.sendPerson = sendPerson;
        this.sendConfirmTime = sendConfirmTime;
        this.sendDetail=sendDetail;
        this.sendCost=sendCost;
    }
	*/
   
    // Property accessors

    public Integer getSendId() {
        return this.sendId;
    }
    
    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSendStatus() {
        return this.sendStatus;
    }
    
    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSendTime() {
        return this.sendTime;
    }
    
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendPerson() {
        return this.sendPerson;
    }
    
    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
    }

    public Date getSendConfirmTime() {
        return this.sendConfirmTime;
    }
    
    public void setSendConfirmTime(Date sendConfirmTime) {
        this.sendConfirmTime = sendConfirmTime;
    }
   
    
    public String getSendDetail() {
		return sendDetail;
	}

	public void setSendDetail(String sendDetail) {
		this.sendDetail = sendDetail;
	}
///////////////////
	public String getStatus()
    {
    	switch(sendStatus)
    	{
    	case Constant.PRESEND:return "δ��";
    	case Constant.SENDING:return "�ͻ���";
    	case Constant.SENT:return "����";
    	default : return "unknown";
    		
    	}
    }
	public String getType()
	{
		return sendType==0?"�̲�":sendType==1?"��Ʊ":"����";
	}
	  public Integer getEpcId() {
	        return this.epcId;
	 }
	  
	    public void setEpcId(Integer epcId) {
		this.epcId = epcId;
	}

		public String getEpcName()
	    {
	    	return this.epcName;
	    }
/////////////////////////

	public String getOrderNo() {
		return order.getOrderNo();
	}


	public Date getSendAddTime() {
		return sendAddTime;
	}

	public void setSendAddTime(Date sendAddTime) {
		this.sendAddTime = sendAddTime;
	}

	public double getSendCost() {
		return sendCost;
	}

	public void setSendCost(double sendCost) {
		this.sendCost = sendCost;
	}

	public void setEpcName(String epcName) {
		this.epcName = epcName;
	}

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public String getSendReceiveName() {
		return sendReceiveName;
	}

	public void setSendReceiveName(String sendReceiveName) {
		this.sendReceiveName = sendReceiveName;
	}

	public String getSendFullAddress() {
		return sendFullAddress;
	}

	public void setSendFullAddress(String sendFullAddress) {
		this.sendFullAddress = sendFullAddress;
	}

	public String getSendMobile() {
		return sendMobile;
	}

	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}

	public String getSendPostalCode() {
		return sendPostalCode;
	}

	public void setSendPostalCode(String sendPostalCode) {
		this.sendPostalCode = sendPostalCode;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public String getSendExpressNo() {
		return sendExpressNo;
	}

	public void setSendExpressNo(String sendExpressNo) {
		this.sendExpressNo = sendExpressNo;
	}
	
	
	
	
	
    







}