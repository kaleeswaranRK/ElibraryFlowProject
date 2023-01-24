package com.elib.model;

public class BookCart {
	private int bookCartId;
	private BookProduct book;
	private int quantity;
	private double price;
	private User user;

	@Override
	public String toString() {
		return "BookCart [bookCartId=" + bookCartId + ", book=" + book + ", quantity=" + quantity + ", price=" + price
				+ ", userId=" + user + "]";
	}

	public BookCart(int bookCartId, BookProduct book, int quantity, double price, User user) {
		this.bookCartId = bookCartId;
		this.book = book;
		this.quantity = quantity;
		this.price = price;
		this.user = user;
	}

	public int getBookCartId() {
		return bookCartId;
	}

	public void setBookCartId(int bookCartId) {
		this.bookCartId = bookCartId;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BookProduct getBook() {
		return book;
	}

	public void setBook(BookProduct book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
