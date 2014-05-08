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
public class CompoundIndexes {
	private static final Logger log = Logger.getAnonymousLogger();
	private static final String DEFAULT_COLLECTION_NAME="yoMongo";
	
	/**
	 * indexed only on the age
	 */
	private static void sortingWithSingleIndex(){
		DBObject searchObject = new BasicDBObject();
		searchObject.put("age", new BasicDBObject().append("$gte", 40).append("$lte", 50));
		DBObject sortCriteria = new BasicDBObject();
		sortCriteria.put("age", 1);
		sortCriteria.put("userName", -1);
		MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).ensureIndex("age");
		DBObject explainObject = MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).find(searchObject).sort(sortCriteria).explain();
		log.info("sorting without Index--->"+explainObject);
	}
	
	/**
	 * indexed on age and username
	 */
	private static void sortingWithCompoundIndex(){
		DBObject searchObject = new BasicDBObject();
		searchObject.put("age", new BasicDBObject().append("$gte", 40).append("$lte", 50));
		DBObject sortCriteria = new BasicDBObject();
		sortCriteria.put("age", 1);
		sortCriteria.put("userName", -1);
		DBObject indexKeys = new BasicDBObject();
		indexKeys.put("age", 1);
		indexKeys.put("userName", -1);
		MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).ensureIndex(indexKeys);
		DBObject explainObject = MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).find(searchObject).sort(sortCriteria).explain();
		log.info("sorting with Index--->"+explainObject);
	}

	
	public static void main(String[] args) {
		MongoUtils.insertMillionRecords();
		MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).dropIndexes();
		CompoundIndexes.sortingWithSingleIndex();
		MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).dropIndexes();
		CompoundIndexes.sortingWithCompoundIndex();
		MongoUtils.getCollection(DEFAULT_COLLECTION_NAME).drop();
	}

}
