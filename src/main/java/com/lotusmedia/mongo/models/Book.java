package com.lotusmedia.mongo.models;

import com.mongodb.BasicDBObject;

public class Book extends BasicDBObject{
	
	public Book(String bookName,Integer numOfPages){
		super.put("bookName", bookName);
		super.put("numOfPages", numOfPages);
	}
	
	public String getBookName() {
		return (String) super.get("bookName");
	}

	public void setBookName(String bookName) {
		super.put("bookName", bookName);
	}

	public Integer getNumOfPages() {
		return  (Integer) super.get("numOfPages");
	}
	
	public void setNumOfPages(Integer numOfPages) {
		super.put("numOfPages", numOfPages);
	}

	public static Book[] createBookArray(){
		Book[] books = new Book[3];
		books[0]=new Book("Introduction Of Spring Framework",500);
		books[1]=new Book("Expert One-on-One J2EE Development without EJB",900);
		return books;
		
	}

}
