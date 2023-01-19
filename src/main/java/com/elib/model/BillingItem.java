package com.elib.model;

import java.util.List;

public class BillingItem {
	private BillingClass billId;
	private List<BookCart> bookCartId;
	private BookProduct book;
	private int quantity;
	private double price;

	public BillingItem(BillingClass billId, List<BookCart> bookCartId, BookProduct book, int quantity, double price) {
		super();
		this.billId = billId;
		this.bookCartId = bookCartId;
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}
	@Override
	public String toString() {
		return "BillingItem [billId=" + billId + ", bookCartId=" + bookCartId + ", book=" + book + ", quantity="
				+ quantity + ", price=" + price + "]";
	}

	public List<BookCart> getBookCartId() {
		return bookCartId;
	}

	public void setBookCartId(List<BookCart> bookCartId) {
		this.bookCartId = bookCartId;
	}

	public BillingClass getBillId() {
		return billId;
	}

	public void setBillId(BillingClass billId) {
		this.billId = billId;
	}

	public BookProduct getBook() {
		return book;
	}

	public void setBook(BookProduct book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
