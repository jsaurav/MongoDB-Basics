package com.lotusmedia.mongo;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class MongoUtils {

	private static final String DATABASE_NAME="lotusDB";
	private static final String DEFAULT_COLLECTION_NAME="lotusCollection";
	private static MongoClient serverConnection = null;
	
	//TODO Thread Safety
	public static DBCollection getCollection(String collectionName) {
		if(serverConnection==null){
			try {
				serverConnection = new MongoClient("localhost",27017);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				throw new RuntimeException("Not able to connect to localhost");
			}
		}		
		DB db = serverConnection.getDB(DATABASE_NAME);
		return db.getCollection(collectionName==null?DEFAULT_COLLECTION_NAME:collectionName);
	}
	
	
	public static void printDocument(String key,String value){
		System.out.println(getCollection("lotusCollection").findOne(new BasicDBObject().append(key, value)));
	}
	
	public static void printAllDocument(String key,String value){
		DBCursor cursor = getCollection("lotusCollection").find(new BasicDBObject().append(key, value));
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}		
	}
	
	
	public static void dropAndRecreateCollection(String collectionName){
		DBCollection lotusCollection=MongoUtils.getCollection(collectionName);
		lotusCollection.drop();
		DBObject firstObject = BasicDBObjectBuilder.start().add("firstName", "rod").append("lastName", "johnson").
				append("founder", "Spring").append("wiki","http://en.wikipedia.org/wiki/Rod_Johnson_%28programmer%29").append("hits",45000).get();
		MongoUtils.getCollection("lotusCollection").insert(firstObject);
		firstObject = BasicDBObjectBuilder.start().add("firstName", "kevin").append("lastName", "ryan").
				append("founder", "mongo").append("wiki","http://en.wikipedia.org/wiki/Kevin_P._Ryan").append("hits",32000).get();
		MongoUtils.getCollection("lotusCollection").insert(firstObject);
		firstObject = BasicDBObjectBuilder.start().add("firstName", "james").
				append("lastName", "gosling").append("founder", "java").append("wiki","http://en.wikipedia.org/wiki/James_Gosling").append("hits",98000).get();
		MongoUtils.getCollection("lotusCollection").insert(firstObject);
		firstObject = BasicDBObjectBuilder.start().add("firstName", "doug").
				append("lastName", "cutting").append("founder", "Spring").append("wiki","http://en.wikipedia.org/wiki/Doug_Cutting").append("hits",28000).get();
		MongoUtils.getCollection("lotusCollection").insert(firstObject);
		firstObject = BasicDBObjectBuilder.start().add("firstName", "eliot").
				append("lastName", "Horowitz").append("founder", "mongo").append("wiki","http://en.wikipedia.org/wiki/Eliot_Horowitz").append("hits",38000).get();
		MongoUtils.getCollection("lotusCollection").insert(firstObject);
	}
}
