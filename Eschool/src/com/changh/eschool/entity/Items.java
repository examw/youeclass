package com.changh.eschool.entity;

import java.util.Date;



/**
 * Items entity. @author MyEclipse Persistence Tools
 */

public class Items  implements java.io.Serializable {


    // Fields    

     private Integer itemId;
     private Integer orderId; 
     private Integer id;
     private Integer productId;
     private double itemOPrice; //ԭ��
     private double itemRPrice;	//�Żݼ�
     private double itemSPrice; //��ѧԱ��
     private String itemName;	//��Ʒ��Ŀ������
     private Integer itemPType; //��Ŀ����ײ�or����
     private String itemPresent;//��Ŀ����
     private Date itemOverTime;
     /**
      * �ײͻ��߰༶�����Ŀ���
      */
     private Integer examId;
     /**
      * ͬ�ֲ�Ʒ�Ĺ�������������ͳ�����
      */
     private int count;

     //
     private Integer dealId;  //Э��id�������ײ��п����д�����
     private Date orderTime;
     
     
    // Constructors

    /** default constructor */
    public Items() {
    }

    
    /** full constructor */
    public Items(Integer itemId, Integer orderId, Integer id, Integer productId, double itemOPrice, double itemRPrice, double itemSPrice,String itemName, Integer itemPType) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.id = id;
        this.productId = productId;
        this.itemOPrice = itemOPrice;
        this.itemRPrice = itemRPrice;
        this.itemSPrice = itemSPrice;
        this.itemName = itemName;
        this.itemPType = itemPType;
    }

   
    // Property accessors

    public Integer getItemId() {
        return this.itemId;
    }
    
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }


    public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return this.productId;
    }
    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public double getItemOPrice() {
        return this.itemOPrice;
    }
    
    public void setItemOPrice(double itemOPrice) {
        this.itemOPrice = itemOPrice;
    }

    public double getItemRPrice() {
        return this.itemRPrice;
    }
    
    public void setItemRPrice(double itemRPrice) {
        this.itemRPrice = itemRPrice;
    }
    
    public double getItemSPrice() {
		return itemSPrice;
	}


	public void setItemSPrice(double itemSPrice) {
		this.itemSPrice = itemSPrice;
	}


	public String getItemName() {
        return this.itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemPType() {
        return this.itemPType;
    }
    
    public void setItemPType(Integer itemPType) {
        this.itemPType = itemPType;
    }


	public Date getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}


	public Integer getExamId() {
		return examId;
	}


	public void setExamId(Integer examId) {
		this.examId = examId;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getItemPresent() {
		return itemPresent;
	}


	public void setItemPresent(String itemPresent) {
		this.itemPresent = itemPresent;
	}


	public Integer getDealId() {
		return dealId;
	}


	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}


	public Date getItemOverTime() {
		return itemOverTime;
	}


	public void setItemOverTime(Date itemOverTime) {
		this.itemOverTime = itemOverTime;
	}
   
	







}