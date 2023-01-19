package com.elib.model;

import java.util.Date;

public class PaymentClass {
	private int paymentID;
	private String status;
	private Date paymentDate;
	private BillingClass billId;

	public PaymentClass(int paymentID, String status, Date paymentDate, BillingClass billId) {
		super();
		this.paymentID = paymentID;
		this.status = status;
		this.paymentDate = paymentDate;
		this.billId = billId;
	}

	@Override
	public String toString() {
		return "PaymentClass [paymentID=" + paymentID + ", status=" + status + ", paymentDate=" + paymentDate
				+ ", billId=" + billId + "]";
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BillingClass getBillId() {
		return billId;
	}

	public void setBillId(BillingClass billId) {
		this.billId = billId;
	}
}
