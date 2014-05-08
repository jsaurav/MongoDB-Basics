/**
 * 
 */
package com.techidiocy.mongo.indexing;

import java.util.logging.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.techidiocy.mongo.MongoUtils;

/**
 * @author yoMongo
 *
 */
public class SimpleIndexComparison {
	
	private static final Logger log = Logger.getAnonymousLogger();
	private static final String DEFAULT_COLLECTION_NAME="yoMongo";

	/**
	 * searching without having an index on collection
	 */
	private static void findWithoutIndex(){
		DBObject searchObject = new BasicDBObject();
		searchObject.put("userName", "userName87469");
		DBObject explainObject = MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).find(searchObject).explain();
		log.info("explainObject without Index--->"+explainObject);
	}
	
	/**
	 * searching with having an index on collection
	 */
	private static void findWithIndex(){
		DBObject searchObject = new BasicDBObject();
		searchObject.put("userName", "userName87469");
		MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).ensureIndex("userName");
		DBObject explainObject = MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).find(searchObject).explain();
		log.info("explainObject with Index--->"+explainObject);
	}

	public static void main(String[] args) {
		MongoUtils.insertMillionRecords();
		SimpleIndexComparison.findWithoutIndex();
		SimpleIndexComparison.findWithIndex();
		MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).drop();
	}

}
