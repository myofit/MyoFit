package com.bran.android;

import java.util.ArrayList;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;

import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;

public class ExerciseManager {
	
	private ArrayList<Exercise> exercises;
	
	private ArrayList<ExerciseData> exerciseData;
	
	private int position;
	
	private UIManager uimanager;
	
	public ExerciseType exercise_type;
	public String exercise_name;
	
	private Pose prevPose;
	
	private static final int TIME_DIFF = 2000;
	
	public ExerciseManager(UIManager uimanager, ExerciseType[] types) {
		
		this.uimanager = uimanager;
		
		exercises = new ArrayList<Exercise>();
		position = 0;
		
		for (int i = 0; i < types.length; i++) {
			switch(types[i]) {
				case BICEP_CURL: exercises.add(new BicepCurl()); 
				break;
				case DUMBBELL_FLY: exercises.add(new DumbbellFly());
				break;
				case TRICEP_KICKBACK: exercises.add(new TricepKickback());
				break;
				case SIDE_LATERAL_RAISE: exercises.add(new SideLateralRaise());
			}
		}
		
		exerciseData = new ArrayList<ExerciseData>();
		
		update();
		
	}
	
	public void next() {

		if (!exercises.get(position).isStarted()) {

			if (position+1 >= exercises.size()) {
				
				createExerciseData();
				
				// Workout Completed
				uimanager.end();

			} else {
				
				createExerciseData();
				
				position++;
				
				this.update();
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
	
	public int getSet() {
		return exercises.get(position).getSet();
	}
	
	public int getRep() {
		return exercises.get(position).getRep();
	}
	
	public boolean getForm() {
		return exercises.get(position).getForm();
	}
	
	public ArrayList<ExerciseData> getExerciseData() {
		return exerciseData;
	}
	
	public void createExerciseData() {
		
		Exercise exercise = exercises.get(position);
		
		ExerciseData data = new ExerciseData(exercise.getName(),exercise.getTime(),exercise.getSet_Reps(),position);
		
		exerciseData.add(data);
		
	}
	
	public void processData(Myo myo, long timestamp, Vector3 vector, DataType type) {
		exercises.get(position).processData(myo, timestamp, vector, type);
		this.update();
	}
	
	public void processData(Myo myo, long timestamp, Quaternion quaternion, DataType type) {
		exercises.get(position).processData(myo, timestamp, quaternion, type);
		this.update();
	}
	
	private boolean similar_wave(Pose pose1, Pose pose2) {
		
		if (pose1.equals(pose2))
			return true;
		
		if (pose1.equals(Pose.WAVE_IN) || pose1.equals(Pose.WAVE_OUT))
			if (pose2.equals(Pose.WAVE_IN) || pose2.equals(Pose.WAVE_OUT))
				return true;
		
		return false;
		
	}
	
	public void processData(Myo myo, long timestamp, long timestampDiff, Pose pose, DataType type) {
		
		Log.i("ExerciseManager","ExerciseManager - Pose: "+pose);
		
		if (pose.equals(Pose.WAVE_OUT) || pose.equals(Pose.WAVE_IN)) {
		
			// Next Exercise
			
			if (prevPose != null && !similar_wave(pose,prevPose))
				next();
			else if (timestamp - timestampDiff > TIME_DIFF && prevPose != null && similar_wave(pose,prevPose))
				next();
		
			timestampDiff = timestamp;
		
		} else if (pose.equals(Pose.FIST)) {
			nextSet();
		} else if (pose.equals(Pose.FINGERS_SPREAD)) {
			endSet();
		}
		
		prevPose = pose;
		
		this.update();
		
	}
	
	public void update() {
		
		exercise_type = exercises.get(position).getType();
		exercise_name = exercises.get(position).getName();
		
		if (uimanager != null)
			uimanager.update();
		
	}
	
	public static String getName(ExerciseType type) {
		
		switch(type) {
			case BICEP_CURL: return "Bicep Curl";
			case DUMBBELL_FLY: return "Dumbbell Fly";
			case TRICEP_KICKBACK: return "Tricep Kickback";
			case SIDE_LATERAL_RAISE: return "Side Lateral Raise";
		}
		
		return null;
		
	}
	
}
