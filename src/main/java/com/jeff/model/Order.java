package com.jeff.model;

public class Order {
	private Integer orderId;
	private String date;
	private Integer amount;
	private String isShare;
	private String item;
	private Integer userId;
	private Boolean needFood;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getIsShare() {
		return isShare;
	}
	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Boolean getNeedFood() {
		return needFood;
	}
	public void setNeedFood(Boolean needFood) {
		this.needFood = needFood;
	}
}
