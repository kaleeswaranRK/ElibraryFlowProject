package com.elib.model;

import java.util.List;

public class Category {
	private int categoryId;
	private String categoryName;
	private List<BookProduct> book;
	
	public Category(int categoryId, String categoryName) {
		
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", book=" + book + "]";
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<BookProduct> getBook() {
		return book;
	}

	public void setBook(List<BookProduct> book) {
		this.book = book;
	}
}
