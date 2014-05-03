package com.lotusmedia.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class DocumentReplacement {
	
	private static void replaceDocument(){
		DBCollection lotusCollection=MongoUtils.getCollection("lotusCollection");
		
		
		
		
		//Search Object
		 DBObject searchObject =new BasicDBObject();
		 searchObject.put("firstName", "eliot");
		
		
		//Modifier Document
		DBObject parentObject=lotusCollection.findOne(searchObject);
		DBObject childObject = new BasicDBObject();
		childObject.put("firstName", parentObject.get("firstName"));
		childObject.put("middleName","R");
		childObject.put("lastName", parentObject.get("lastName"));
		childObject.put("country", "USA");
		parentObject.put("personalDetails", childObject);
		parentObject.removeField("firstName");
		parentObject.removeField("lastName");
		
		//call update
		//searchObject = new BasicDBObject();
		//searchObject.put("founder", "mongo");
		lotusCollection.update(searchObject, parentObject);
		System.out.println("Document Replaced Successfully");
	}
	

	public static void main(String[] args) {
		DocumentReplacement.replaceDocument();
	}

}
