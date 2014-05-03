package com.lotusmedia.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;


public class FindMongo {

	public static void main(String[] args) {
	   //Fetch all the documents present in the collection.	
	   DBCollection lotusCollection=MongoUtils.getCollection("lotusCollection");
	   DBCursor allResults = lotusCollection.find();
	   while(allResults.hasNext()){
			System.out.println(allResults.next());
		}
	   
	   //Fetch all the founders of mongodb.
	   BasicDBObject searchObject = new BasicDBObject();
	   searchObject.put("founder", "mongo");
	   DBCursor resultSubset = lotusCollection.find(searchObject);
	   while(resultSubset.hasNext()){
			System.out.println(resultSubset.next());
		}
	
	   //Fetch only firstname and wiki link
	   searchObject = new BasicDBObject();
	   searchObject.put("founder", "mongo");
	   BasicDBObject fieldObject = new BasicDBObject();
	   fieldObject.put("firstName", 1);
	   fieldObject.put("wiki", 1);
	   resultSubset = lotusCollection.find(searchObject,fieldObject);
	   while(resultSubset.hasNext()){
			System.out.println(resultSubset.next());
		}
	   
	   //Fetch only firstname , wiki link and exclude _id
	   searchObject = new BasicDBObject();
	   searchObject.put("founder", "mongo");
	   fieldObject = new BasicDBObject();
	   fieldObject.put("firstName", 1);
	   fieldObject.put("wiki", 1);
	   fieldObject.put("_id", 0);
	   resultSubset = lotusCollection.find(searchObject,fieldObject);
	   while(resultSubset.hasNext()){
			System.out.println(resultSubset.next());
		}
	   
	   
	}

}
