package com.bran.android;

import android.util.Log;

public class Exercise {
	
	private int set;
	private int rep;
	private boolean started;
	
	private String name;
	
	private ExerciseType type;
	
	public Exercise(String name, ExerciseType type) {
		
		this.name = name;
		this.type = type;
		
		this.set = 0;
		this.rep = 0;
		
	}
	
	public String getName() {
		return name;
	}
	
	public ExerciseType getType() {
		return type;
	}
	
	public void nextSet() {
		Log.i("Exercises", "Exercises - nextSet()");
		if(!started) {
			set++;
			started = true;
			Log.i("Exercises", "Exercises - nextSet() - setTV: "+set);
		}
	}
	
	public void endSet() {
		Log.i("Exercises", "Exercises - endSet()");
		if(started) {
			started = false;
			Log.i("Exercises", "Exercises - endSet(): started = false");
		}
	}
	
	public int getRep() {
		return this.rep;
	}
	
	public int getSet() {
		return this.set;
	}
	
	public boolean isStarted() {
		return started;
	}
	
}
