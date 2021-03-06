package com.techidiocy.performance.tests;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class WriteConcernPerformanceTest {
	
	private static final Logger log = Logger.getAnonymousLogger();
	private static final String DATABASE_NAME="performanceDB";
	
	private static final int HUNDRED_THOUSAND=1000;

	@Test
	public void test() throws UnknownHostException {		
		errorIgnored();
		unacknowledged();
		acknowledged();
		journaled();
		fsynced();
	}	
	
	private void errorIgnored() throws UnknownHostException{
		DBCollection collection = getCollection(DATABASE_NAME, WriteConcern.ERRORS_IGNORED);		
		log.info("ERRORS_IGNORED Insertion Time --"+insertRecords(collection));
	}
	
	private void unacknowledged() throws UnknownHostException{
		DBCollection collection = getCollection(DATABASE_NAME, WriteConcern.UNACKNOWLEDGED );
		log.info("UNACKNOWLEDGED Insertion Time --"+insertRecords(collection));
	}
	
	private void acknowledged() throws UnknownHostException{
		DBCollection collection = getCollection(DATABASE_NAME, WriteConcern.ACKNOWLEDGED );
		log.info("ACKNOWLEDGED Insertion Time --"+insertRecords(collection));
	}
	
	private void journaled() throws UnknownHostException{
		DBCollection collection = getCollection(DATABASE_NAME, WriteConcern.JOURNALED );
		log.info("JOURNALED Insertion Time --"+insertRecords(collection));
	}
	
	private void fsynced() throws UnknownHostException{
		DBCollection collection = getCollection(DATABASE_NAME, WriteConcern.FSYNCED );
		log.info("FSYNCED Insertion Time --"+insertRecords(collection));
	}
	
	private long insertRecords(DBCollection collection) {
		long startTime=System.currentTimeMillis();
		for(int i=0;i<HUNDRED_THOUSAND;i++){
			DBObject myObject = new BasicDBObject();
			myObject.put("someKey", "someValue");
			collection.insert(myObject);
		}
		long endTime=System.currentTimeMillis();
		collection.drop();
		return (endTime-startTime);
	}


	private DBCollection getCollection(String databaseName,WriteConcern writeConcern) throws UnknownHostException{
		MongoClient serverConnection = new MongoClient("localhost",27017);
		serverConnection.setWriteConcern(writeConcern);
		return serverConnection.getDB(databaseName).getCollection("yoMongo");
	}	

}
