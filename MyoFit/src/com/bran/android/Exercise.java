package com.bran.android;

import java.util.HashMap;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;

import android.util.Log;

public class Exercise {
	
	protected int set;
	protected int rep;
	protected boolean form;
	protected boolean started;
	
	private String name;
	
	private ExerciseType type;
	
	private HashMap<Integer,Integer> set_reps;
	private long time;
	
	public Exercise(String name, ExerciseType type) {
		
		this.name = name;
		this.type = type;
		
		this.set = 0;
		this.rep = 0;
		
		set_reps = new HashMap<Integer,Integer>();
		
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
			rep = 0;
			started = true;
			Log.i("Exercises", "Exercises - nextSet() - setTV: "+set);
		}
	}
	
	public void endSet() {
		Log.i("Exercises", "Exercises - endSet()");
		if(started) {
			started = false;
			set_reps.put(set, rep);
			Log.i("Exercises", "Exercises - endSet(): started = false");
		}
	}
	
	public int getRep() {
		return this.rep;
	}
	
	public int getSet() {
		return this.set;
	}
	
	public boolean getForm() {
		return this.form;
	}
	
	public long getTime() {
		return this.time;
	}
	
	public HashMap<Integer,Integer> getSet_Reps() {
		return set_reps;
	}
	
	public boolean isStarted() {
		return started;
	}
	
	public void processData(Myo myo, long timestamp, Vector3 vector, DataType type) {
		this.time = timestamp;
	}
	
	public void processData(Myo myo, long timestamp, Quaternion quaternion, DataType type) {
		this.time = timestamp;
	}
	
}
