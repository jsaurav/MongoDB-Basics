package com.lotusmedia.mongo;

import com.lotusmedia.mongo.models.Book;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class ArrayModifiers {
	
	private static final String LOTUS_COLLECTION="lotusCollection";

	/**
	 *  Use of push array modifier
	 */
	private static void usingPushModifier(){
		DBCollection lotusCollection=MongoUtils.getCollection(LOTUS_COLLECTION);
		
		//search object
		DBObject searchObject =new BasicDBObject();
		searchObject.put("firstName", "rod");
		
		//Adding Books
		DBObject modifiedObject =new BasicDBObject();
		modifiedObject.put("$push", new BasicDBObject().append("authorOf", "Introduction Of Spring Framework"));
	    lotusCollection.update(searchObject, modifiedObject);
		
	    MongoUtils.printDocument("firstName", "rod");
	    
	    modifiedObject.put("$push", new BasicDBObject().append("authorOf", "Expert One-on-One J2EE Development without EJB"));
	    lotusCollection.update(searchObject, modifiedObject);
	    MongoUtils.printDocument("firstName", "rod");
		
	    //Recreating the collection and records
	    MongoUtils.dropAndRecreateCollection(LOTUS_COLLECTION);
	}
	
	/**
	 *  Use of each with push array modifier
	 */
	private static void usingEachWithPushModifier(){
		DBCollection lotusCollection=MongoUtils.getCollection(LOTUS_COLLECTION);
		
		//search object
		DBObject searchObject =new BasicDBObject();
		searchObject.put("firstName", "rod");
		
		//Adding Books
		DBObject modifiedObject =new BasicDBObject();
		DBObject eachObject =new BasicDBObject();
		eachObject.put("$each", new String[]{"Introduction Of Spring Framework","Expert One-on-One J2EE Development without EJB"});
		modifiedObject.put("$push", new BasicDBObject().append("authorOf", eachObject));
	    lotusCollection.update(searchObject, modifiedObject);
		
	    MongoUtils.printDocument("firstName", "rod");
	    
	  //Recreating the collection and records
	    MongoUtils.dropAndRecreateCollection(LOTUS_COLLECTION);
		
	}
	
	/**
	 *  Use of slice,each under push array modifier
	 */
	private static void usingSliceEachUnderPushModifier(){
		DBCollection lotusCollection=MongoUtils.getCollection(LOTUS_COLLECTION);
		
		//search object
		DBObject searchObject =new BasicDBObject();
		searchObject.put("firstName", "rod");
		
		//Adding Books
		DBObject modifiedObject =new BasicDBObject();
		DBObject eachObject =new BasicDBObject();
		eachObject.put("$each", new String[]{"Old Book 1","Old Book 2"});
		modifiedObject.put("$push", new BasicDBObject().append("authorOf", eachObject));
	    lotusCollection.update(searchObject, modifiedObject);
		
	    MongoUtils.printDocument("firstName", "rod");
	    
	    eachObject.put("$each", new String[]{"Introduction Of Spring Framework","Expert One-on-One J2EE Development without EJB"});
	    eachObject.put("$slice", -3);
	//    eachObject.put("$sort", new BasicDBObject().append("", -1));
	    
	    modifiedObject.put("$push", new BasicDBObject().append("authorOf", eachObject));
	    lotusCollection.update(searchObject, modifiedObject);		
	    MongoUtils.printDocument("firstName", "rod");
	    
	  //Recreating the collection and records
	    MongoUtils.dropAndRecreateCollection(LOTUS_COLLECTION);
		
	}	
	
	/**
	 *  Use of slice,each under push array modifier
	 */
	private static void usingSortSliceEachUnderPushModifier(){
		DBCollection lotusCollection=MongoUtils.getCollection(LOTUS_COLLECTION);
		
		//search object
		DBObject searchObject =new BasicDBObject();
		searchObject.put("firstName", "rod");
		
		//Adding Books
		DBObject modifiedObject =new BasicDBObject();
		DBObject eachObject =new BasicDBObject();
		eachObject.put("$each", Book.createBookArray());
	    eachObject.put("$slice", -3);
	    eachObject.put("$sort", new BasicDBObject().append("numOfPages", 1));
	    
	    modifiedObject.put("$push", new BasicDBObject().append("authorOf", eachObject));
	    lotusCollection.update(searchObject, modifiedObject);		
	    MongoUtils.printDocument("firstName", "rod");
	    
	  //Recreating the collection and records
	    MongoUtils.dropAndRecreateCollection(LOTUS_COLLECTION);
		
	}	

	
	public static void main(String[] args) {
		//ArrayModifiers.usingPushModifier();
		//ArrayModifiers.usingEachWithPushModifier();
		//ArrayModifiers.usingSliceEachUnderPushModifier();
		ArrayModifiers.usingSortSliceEachUnderPushModifier();
	}

}
