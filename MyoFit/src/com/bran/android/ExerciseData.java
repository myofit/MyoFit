package com.bran.android;

import java.util.ArrayList;
import java.util.HashMap;

public class ExerciseData {

	public String name;
	public long timestamp;
	public ArrayList<ArrayList<Integer>> table;
	public int id;
	
	public ExerciseData(String name, long timestamp, ArrayList<ArrayList<Integer>> table, int id) {
		this.name = name;
		this.timestamp = timestamp;
		this.table = table;
		this.id = id;
	}
	
}
