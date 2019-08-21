package com.rt.books;

public class Book {
	String id;
	String author;
	String title;
	int price;
	public Book(String id) {
		super();
		this.id = id;
	}
	public Book(String id, String author, String title, int price) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
}
