package com.elib.model;

import java.util.Date;
import java.util.List;

public class BillingClass {
	private int billId;
	private double totalAmount;
	private Date billDate;
	private User userId;
	private PaymentClass paymentId;
	private List<BillingItem> billItem;
	
	public BillingClass(int billId, double totalAmount, Date billDate, User userId, PaymentClass paymentId,
			List<BillingItem> billItem) {
		super();
		this.billId = billId;
		this.totalAmount = totalAmount;
		this.billDate = billDate;
		this.userId = userId;
		this.paymentId = paymentId;
		this.billItem = billItem;
	}

	@Override
	public String toString() {
		return "BillingClass [billId=" + billId + ", totalAmount=" + totalAmount + ", billDate=" + billDate
				+ ", userId=" + userId + ", paymentId=" + paymentId + ", billItem=" + billItem + "]";
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public List<BillingItem> getBillItem() {
		return billItem;
	}

	public void setBillItem(List<BillingItem> billItem) {
		this.billItem = billItem;
	}

	public PaymentClass getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(PaymentClass paymentId) {
		this.paymentId = paymentId;
	}

}
