package com.bran.android;

import java.util.ArrayList;

import android.util.Log;

public class ExerciseManager {
	
	private ArrayList<Exercise> exercises;
	
	private int position;
	
	private UIManager uimanager;
	
	public ExerciseType exercise_type;
	public String exercise_name;
	public String set;
	public String rep;
	
	public ExerciseManager(UIManager uimanager, ExerciseType[] types) {
		
		this.uimanager = uimanager;
		
		exercises = new ArrayList<Exercise>();
		position = 0;
		
		for (int i = 0; i < types.length; i++) {
			switch(types[i]) {
				case BICEP_CURL: exercises.add(new BicepCurl()); 
				case BENCH_PRESS: exercises.add(new BenchPress());
				case TRICEP_PUSHDOWN: exercises.add(new TricepPushdown());
			}
		}
		
	}
	
	public void next() {

		if (!exercises.get(position).isStarted()) {

			if (position+1 >= exercises.size()) {

				// WORKOUT COMPLETE

			} else {
				position++;
				this.update();
				uimanager.update();
				Log.i("ExerciseManager", "Exercise Manager - next()");
			}

		}
		
	}
	
	public void nextSet() {
		exercises.get(position).nextSet();
		
	}
	
	public void endSet() {
		exercises.get(position).endSet();
		
	}
	
	public void update() {
		
		exercise_type = exercises.get(position).getType();
		exercise_name = exercises.get(position).getName();
		rep = ""+exercises.get(position).getRep();
		set = ""+exercises.get(position).getSet();
		
	}
	
	public static String getName(ExerciseType type) {
		
		switch(type) {
			case BICEP_CURL: return "Bicep Curl";
			case BENCH_PRESS: return "Bench Press";
			case TRICEP_PUSHDOWN: return "Tricep Pushdown";
		}
		
		return null;
		
	}

}
