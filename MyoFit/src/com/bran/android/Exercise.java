package com.bran.android;

import java.util.ArrayList;
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
	
	protected int badForms;
	
	private String name;
	
	private ExerciseType type;
	
	private ArrayList<ArrayList<Integer>> set_reps_form;
	private long time;
	
	public Exercise(String name, ExerciseType type) {
		
		this.name = name;
		this.type = type;
		
		this.set = 0;
		this.rep = 0;
		
		set_reps_form = new ArrayList<ArrayList<Integer>>();
		
		badForms = 0;
		
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
			badForms = 0;
			started = true;
			Log.i("Exercises", "Exercises - nextSet() - setTV: "+set);
		}
	}
	
	public void endSet() {
		Log.i("Exercises", "Exercises - endSet()");
		if(started) {
			started = false;
			
			ArrayList<Integer> data = new ArrayList<Integer>();
			data.add(set);
			data.add(rep);
			
			int formPerc = 0;
			
			if (rep != 0)
				formPerc = Math.round(100*badForms/rep);
			
			data.add(formPerc);
			
			set_reps_form.add(data);
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
	
	public int getBadForms() {
		return this.badForms;
	}
	
	public ArrayList<ArrayList<Integer>> getSet_Reps_Form() {
		return set_reps_form;
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
