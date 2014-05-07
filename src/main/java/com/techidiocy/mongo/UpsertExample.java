/**
 * 
 */
package com.techidiocy.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @author yoMongo
 *
 */
public class UpsertExample {

	public static void updateOrInsert(){
		DBCollection lotusCollection=MongoUtils.getCollection("yoMongo");
		BasicDBObject searchObject = new BasicDBObject();
		searchObject.put("title", "Sir");
		searchObject.put("firstName", "Dennis");
		searchObject.put("lastName", "Ritchie");
		searchObject.put("founder", "C");
		searchObject.put("wiki", "http://en.wikipedia.org/wiki/Dennis_Ritchie");
		
		DBObject modifiedObject =new BasicDBObject();
		modifiedObject.put("$inc", new BasicDBObject().append("hits", 1));
		
		lotusCollection.update(searchObject, modifiedObject,true,false);
		MongoUtils.printDocument("firstName", "Dennis");
		
	}
	
	
	public static void main(String[] args) {
		UpsertExample.updateOrInsert();
	}

}
