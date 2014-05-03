package com.lotusmedia.mongo;

import com.lotusmedia.mongo.models.Book;
import com.lotusmedia.mongo.models.Person;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class Test {
	
	private static final String LOTUS_COLLECTION="lotusCollection";

	public static void main(String[] args) {
		DBCollection lotusCollection=MongoUtils.getCollection(LOTUS_COLLECTION);
		Book book=new Book("Introduction Of Spring Framework",500);
		Person p = new Person("Saurav");
		lotusCollection.save((DBObject)p);
		System.out.println(lotusCollection.findOne());
		//lotusCollection.save(book);
		//System.out.println(lotusCollection.findOne());
	}

}
