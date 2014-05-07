/**
 * 
 */
package com.techidiocy.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

/**
 * @author yoMongo
 *
 */
public class MultipleDocumentUpdate {
	
	private static void updateDocuments(){
		DBCollection lotusCollection=MongoUtils.getCollection("lotusCollection");
		BasicDBObject searchObject = new BasicDBObject();
		searchObject.put("founder", "mongo");
		
		DBObject modifiedObject =new BasicDBObject();
		modifiedObject.put("$set", new BasicDBObject().append("founder", "mongodb"));
		
		
		WriteResult result=lotusCollection.update(searchObject, modifiedObject,false,true);
		MongoUtils.printAllDocument("founder", "mongodb");		
	}
	
	
	public static void main(String[] args) {
		MultipleDocumentUpdate.updateDocuments();
	}

}
