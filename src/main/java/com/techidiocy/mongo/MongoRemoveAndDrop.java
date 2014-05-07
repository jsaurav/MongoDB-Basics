package com.techidiocy.mongo;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoRemoveAndDrop {
	private static final int MAX_DOCS=1000000;	
	
	public static void insertAndRemove(){
		DBCollection collection = MongoUtils.getCollection("lotusCollection");
		//Inserting 1000,000 documents into collection
		for(int i=0;i<MAX_DOCS;i++){
			BasicDBObject object = new BasicDBObject();
			object.put("identification", i);
			object.put("role", "Role "+i);
			collection.insert(object);
		}
		//Remove all the documents 94257 48481
		BasicDBObject removeObject = new BasicDBObject();
		long currentTime = System.currentTimeMillis();
		collection.remove(removeObject);
		System.out.println("Removal Time : "+(System.currentTimeMillis()-currentTime));
	}
	
	public static void insertAndDrop(){
		DBCollection collection = MongoUtils.getCollection("lotusCollection");
		//Inserting 1000,000 documents into collection
		for(int i=0;i<MAX_DOCS;i++){
			BasicDBObject object = new BasicDBObject();
			object.put("identification", i);
			object.put("role", "Role "+i);
			collection.insert(object);
		}
		//Drop the collection
		long currentTime = System.currentTimeMillis();
		collection.drop();
		System.out.println("Drop Time : "+(System.currentTimeMillis()-currentTime));	
	}

	public static void main(String[] args) {
		MongoRemoveAndDrop.insertAndRemove();
		MongoRemoveAndDrop.insertAndDrop();
	}

}
