package com.lotusmedia.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class UpdateModifiers {
	
	/**
	 * Sets the authorOf key with the document associated with Rod Johnson.
	 */
	private static void updateUsingSetModifier(){
		DBCollection lotusCollection=MongoUtils.getCollection("lotusCollection");
		//search object
		DBObject searchObject =new BasicDBObject();
		searchObject.put("firstName", "rod");		
		
		DBObject modifiedObject =new BasicDBObject();
		modifiedObject.put("$set", new BasicDBObject().append("authorOf", new String[]{"Introduction Of Spring Framework","Expert One-on-One J2EE Development without EJB"}));
	    lotusCollection.update(searchObject, modifiedObject);
	    printDocument();
	}

	/**
	 * Unsets the authorOf key with the document associated with Rod Johnson.
	 */
	private static void updateUsingUnsetModifier(){
		DBCollection lotusCollection=MongoUtils.getCollection("lotusCollection");
		//search object
		DBObject searchObject =new BasicDBObject();
		searchObject.put("firstName", "rod");
		
		DBObject modifiedObject =new BasicDBObject();		
		modifiedObject.put("$unset", new BasicDBObject().append("authorOf", 1));
		lotusCollection.update(searchObject, modifiedObject);
		printDocument();
	}
	
	/**
	 * Increments the hits key of the document by 1.
	 */
	private static void updateUsingIncModifier(){
		DBCollection lotusCollection=MongoUtils.getCollection("lotusCollection");
		//search object
		DBObject searchObject =new BasicDBObject();
		searchObject.put("firstName", "rod");		
		
		DBObject modifiedObject =new BasicDBObject();
		modifiedObject.put("$inc", new BasicDBObject().append("hits", 1));
	    lotusCollection.update(searchObject, modifiedObject);
	    printDocument();
	}
	
	private static void printDocument(){
		System.out.println(MongoUtils.getCollection("lotusCollection").findOne(new BasicDBObject().append("firstName", "rod")));
	}
	
	public static void main(String[] args) {
		UpdateModifiers.updateUsingSetModifier();
		UpdateModifiers.updateUsingUnsetModifier();
		UpdateModifiers.updateUsingIncModifier();
	}

}
