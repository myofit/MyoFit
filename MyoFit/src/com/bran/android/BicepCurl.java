package com.bran.android;

import android.util.Log;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Vector3;

public class BicepCurl extends Exercise {

	private boolean down = true;
	private double minAngle = 1.2217; //70 degrees
	private double downAngle = 0.1745; //10 degrees

	public BicepCurl() {
		super("Bicep Curl",ExerciseType.BICEP_CURL);

	}

	@Override
	public void processData(Myo myo, long timestamp, Vector3 vector, DataType type) {

		// Process Data
		if(type.equals(DataType.GYROSCOPE)) {

			Log.i("BicepCurl Gyroscope","z: "+vector.z());
			if(started) {
				if(down && vector.z() > minAngle) {
					down = false;
					reps++;
				} else if (!down && vector.z() < downAngle)
					down = true;
				}
		  }
		}

	}

}

if(down && myo.getYaw() > minAngle) {
	down = false;
	reps++;
}
if(down = false && myo.getYaw() < downAngle) {
	down = true;
}
