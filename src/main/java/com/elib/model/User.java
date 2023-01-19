package com.elib.model;

import java.util.List;

public class User {
	
	private int customerId;
	private String customerPassword;
	private List<BookCart> bookCartId;
	private List<BillingClass> billId;

	public User(int customerId, String customerPassword, List<BookCart> bookCartId,
			List<BillingClass> billId) {
		super();
		this.customerId = customerId;
		this.customerPassword = customerPassword;
		this.bookCartId = bookCartId;
		this.billId = billId;
	}

	@Override
	public String toString() {
		return "User [customerId=" + customerId + ", customerPassword=" + customerPassword +", bookCartId=" + bookCartId + ", billId=" + billId + "]";
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public List<BookCart> getBookCartId() {
		return bookCartId;
	}

	public void setBookCartId(List<BookCart> bookCartId) {
		this.bookCartId = bookCartId;
	}

	public List<BillingClass> getBillId() {
		return billId;
	}

	public void setBillId(List<BillingClass> billId) {
		this.billId = billId;
	}
}
