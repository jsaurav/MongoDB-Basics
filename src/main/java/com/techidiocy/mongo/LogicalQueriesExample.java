/**
 * 
 */
package com.techidiocy.mongo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

/**
 * @author j.saurab
 *
 */
public class LogicalQueriesExample {

   private static void logicalQueryOperators(){
	   DBCollection lotusCollection=MongoUtils.getCollection("lotusCollection");
	   BasicDBObject searchObject = new BasicDBObject();
	   
	   //or operator - hits > 90000 || hits < 40000
	   List<BasicDBObject> searchArguments = new ArrayList<BasicDBObject>();
	   searchArguments.add(new BasicDBObject("hits",new BasicDBObject("$lt",40000)));
	   searchArguments.add(new BasicDBObject("hits",new BasicDBObject("$gt",90000)));
	   searchObject.put("$or", searchArguments);
	   DBCursor logicalQueryResults = lotusCollection.find(searchObject);		
		while(logicalQueryResults.hasNext()){
			System.out.println("OR operator Result --->"+logicalQueryResults.next());
		}
		
	   //and operator -	hits > 40000 && founder = 'Spring'
	   searchObject = new BasicDBObject();
	   searchArguments.clear();
	   searchArguments.add(new BasicDBObject("hits",new BasicDBObject("$gt",40000)));
	   searchArguments.add(new BasicDBObject("founder","Spring"));
	   searchObject.put("$and", searchArguments);
	   logicalQueryResults = lotusCollection.find(searchObject);		
		while(logicalQueryResults.hasNext()){
			System.out.println("AND Operator Result --->"+logicalQueryResults.next());
		}
		
	   //not operator -returns ( hits > 40000 && founder !='Spring') + All the documents where 'founder' key is missing
	   searchObject = new BasicDBObject();
	   searchArguments.clear();
	   searchArguments.add(new BasicDBObject("hits",new BasicDBObject("$gt",40000)));
	   searchArguments.add(new BasicDBObject("founder",new BasicDBObject("$not",new BasicDBObject("$eq","Spring"))));
	   searchObject.put("$and", searchArguments);
	   logicalQueryResults = lotusCollection.find(searchObject);		
		while(logicalQueryResults.hasNext()){
			System.out.println("NOT Operator Result --->"+logicalQueryResults.next());
		}
		
		//nor operator - !(hits > 40000 || founder ='Spring')
	   searchObject = new BasicDBObject();
	   searchArguments.clear();
	   searchArguments.add(new BasicDBObject("hits",new BasicDBObject("$gt",40000)));
	   searchArguments.add(new BasicDBObject("founder",new BasicDBObject("$eq","Spring")));
	   searchObject.put("$nor", searchArguments);
	   logicalQueryResults = lotusCollection.find(searchObject);		
	   while(logicalQueryResults.hasNext()){
			System.out.println("NOR Operator Result --->"+logicalQueryResults.next());
		}
   }
	
	
	public static void main(String[] args) {
		LogicalQueriesExample.logicalQueryOperators();
	}

}
