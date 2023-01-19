package com.elib.model;

public class BookProduct {
	private int bookID;
	private String bookName;
	private String authorName;
	private int bookQuantity;
	private double bookPrice;
	private Category category;

	public BookProduct(int bookID, String bookName, String authorName, int bookQuantity, double bookPrice) {
		this.bookID = bookID;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookQuantity = bookQuantity;
		this.bookPrice = bookPrice;
	}

	public BookProduct() {
		
	}

	@Override
	public String toString() {
		return "BookProduct [bookID=" + bookID + ", bookName=" + bookName + ", authorName=" + authorName
				+ ", bookQuantity=" + bookQuantity + ", bookPrice=" + bookPrice + ", category=" + category + "]";
	}

	public int getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Category getcategory() {
		return category;
	}

	public void setcategory(Category category) {
		this.category = category;
	}
}