package com.changh.eschool.entity;

import java.io.Serializable;

public class CartItem implements Serializable{
	//�������Ʒ
	private Items item;
	//�Ƿ�ɾ���ı�ʶ
	private boolean buy=true;
	public Items getItem() {
		return item;
	}
	public void setItem(Items item) {
		this.item = item;
	}
	public boolean isBuy() {
		return buy;
	}
	public void setBuy(boolean buy) {
		this.buy = buy;
	}
	
}
