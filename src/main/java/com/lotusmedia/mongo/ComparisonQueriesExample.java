package com.lotusmedia.mongo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ComparisonQueriesExample {
	
	/**
	 * Different types of comparison operators
	 */
	private static void comparisonQueryOperators(){
		DBCollection lotusCollection=MongoUtils.getCollection("lotusCollection");
		BasicDBObject searchObject = new BasicDBObject();
		
		//$lt operator example - prints all the records having counts less than 50000
		searchObject.put("hits", new BasicDBObject("$lt",50000));
		DBCursor compareQueryResults = lotusCollection.find(searchObject);		
		while(compareQueryResults.hasNext()){
			System.out.println(compareQueryResults.next());
		}
		
		//$gt operator example - prints all the records having counts greatar than 40000
		searchObject.put("hits", new BasicDBObject("$gt",40000));
		compareQueryResults = lotusCollection.find(searchObject);		
		while(compareQueryResults.hasNext()){
			System.out.println(compareQueryResults.next());
		}
		
		//$lt and $gt combination - print all the records that are less than 50K and more than 40K
		searchObject.put("hits", new BasicDBObject("$gt",40000).append("$lt", 50000));
		compareQueryResults = lotusCollection.find(searchObject);		
		while(compareQueryResults.hasNext()){
			System.out.println(compareQueryResults.next());
		}
		
		//$in operator example - print all the records that match the hits present in list
		List<Integer> hitList = new ArrayList<Integer>();
		hitList.add(28000);
		hitList.add(20000);
		hitList.add(98000);
		searchObject.put("hits", new BasicDBObject("$in",hitList));
		compareQueryResults = lotusCollection.find(searchObject);		
		while(compareQueryResults.hasNext()){
			System.out.println(compareQueryResults.next());
		}
		
		//$ne operator example - print all the records except the value passes as an argument
		searchObject.put("hits", new BasicDBObject("$ne",98000));
		compareQueryResults = lotusCollection.find(searchObject);		
		while(compareQueryResults.hasNext()){
			System.out.println(compareQueryResults.next());
		}
		
		//$nin operator example - print all the records that doesn't match the hits present in list
		searchObject.put("hits", new BasicDBObject("$nin",hitList));
		compareQueryResults = lotusCollection.find(searchObject);		
		while(compareQueryResults.hasNext()){
			System.out.println(compareQueryResults.next());
		}
	}
	
	public static void main(String[] args) {
		ComparisonQueriesExample.comparisonQueryOperators();
	}

}
