package com.bran.android;

import java.util.HashMap;

public class ExerciseData {

	public String name;
	public long timestamp;
	public HashMap<Integer,Integer> table;
	public int id;
	
	public ExerciseData(String name, long timestamp, HashMap<Integer,Integer> table, int id) {
		this.name = name;
		this.timestamp = timestamp;
		this.table = table;
		this.id = id;
	}
	
}
