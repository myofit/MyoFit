package com.bran.android;

import android.util.Log;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Vector3;

public class BicepCurl extends Exercise {
	
	public BicepCurl() {
		super("Bicep Curl",ExerciseType.BICEP_CURL);
		
	}
	
	@Override
	public void processData(Myo myo, long timestamp, Vector3 vector, DataType type) {
		
		// Process Data
		
		if (type.equals(DataType.ACCELEROMETER)) {
			
			Log.i("BicepCurl", "BicepCurl - Accelerometer x: "+vector.x());
			
			if (vector.x() > 1) {
				rep++;
			}
			
		} else if (type.equals(DataType.GYROSCOPE)) {
			
			
			
		}
		
	}
	
}
