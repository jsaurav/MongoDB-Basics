package com.techidiocy.mongo;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class DropAndCreate {
	
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
				append("lastName", "cutting").append("founder", "Hadoop").append("wiki","http://en.wikipedia.org/wiki/Doug_Cutting").append("hits",28000).get();
		MongoUtils.getCollection("lotusCollection").insert(firstObject);
		firstObject = BasicDBObjectBuilder.start().add("firstName", "eliot").
				append("lastName", "Horowitz").append("founder", "mongo").append("wiki","http://en.wikipedia.org/wiki/Eliot_Horowitz").append("hits",38000).get();
		MongoUtils.getCollection("lotusCollection").insert(firstObject);
	}

    public static void main(String[] args) {	
    	DropAndCreate.dropAndRecreateCollection("lotusCollection");
    }
}
