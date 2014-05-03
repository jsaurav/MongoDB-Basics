package com.lotusmedia.mongo;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class HelloMongo {
	

	public static void main(String[] args) throws UnknownHostException {

		//Connect to MongoDB server
		MongoClient serverConnection = new MongoClient("localhost",27017);
		
		//Create a connection to lotus database
		DB db = serverConnection.getDB("lotus");
		
		//Display all the databases 
		List<String> dbNames = serverConnection.getDatabaseNames();
		for(String databaseName:dbNames){
			System.out.println(databaseName);
		}
		
		//Creating a non capped collection 
		DBObject options = BasicDBObjectBuilder.start().add("capped", false).add("size", 4194304).get();
		//db.createCollection("lotusCollection", options );
		
		//Inserting a document into the collection
		DBObject firstObject = BasicDBObjectBuilder.start().add("firstName", "saurav").
				append("lastName", "jain").get();
		db.getCollection("lotusCollection").insert(firstObject);
		
		//Search a document
		BasicDBObject searchObject = new BasicDBObject();
		searchObject.put("firstName", "saurav");		
		DBCursor searchResults = db.getCollection("lotusCollection").find(searchObject);
		while(searchResults.hasNext()){
			System.out.println("Before Update--->"+searchResults.next());
		}
		
		//Update a document
		BasicDBObject searchObjectNew = new BasicDBObject();
		searchObjectNew.put("firstName", "saurav");		
		
		DBObject newDocument = BasicDBObjectBuilder.start().add("firstName", "saurav").append("middleName", "R").append("lastName", "sharma").get();
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", newDocument);
		db.getCollection("lotusCollection").updateMulti(searchObjectNew, updateObject);
		
		
		searchResults = db.getCollection("lotusCollection").find(searchObjectNew);
		while(searchResults.hasNext()){
			System.out.println("After Update "+searchResults.next());
		}
		
		//db.getCollection("lotusCollection").remove(searchObjectNew);
	}

}
