package com.bran.android;

import android.util.Log;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Myo.VibrationType;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.XDirection;

public class BicepCurl extends Exercise {

	private boolean down = true;
	private double minAngle = .25; //70 degrees
	private double downAngle = -0.2; //-10 degrees
	
	private int direction;

	public BicepCurl() {
		super("Bicep Curl",ExerciseType.BICEP_CURL);
		
		direction = 1;
		
	}

	@Override
	public void processData(Myo myo, long timestamp, Quaternion quaternion, DataType type) {

		if (myo.getXDirection().equals(XDirection.TOWARD_ELBOW))
			direction = -1;

		// Process Data
		if(type.equals(DataType.ORIENTATION)) {
			Log.i("BicepCurl Angles","x: "+direction*quaternion.x());
			Log.i("BicepCurl Angles","y: "+direction*quaternion.y());
			Log.i("BicepCurl Angles","z: "+direction*quaternion.z());
			if(started) {
				if(down && direction*quaternion.z() > minAngle) {
					down = false;
					rep++;
					myo.vibrate(VibrationType.SHORT);
				} else if (!down && direction*quaternion.z() < downAngle) {
					down = true;
				}
			}
		}

	}

	@Override
	public void processData(Myo myo, long timestamp, Vector3 vector, DataType type) {


		// Process Data
		/*if(type.equals(DataType.GYROSCOPE)) {
		Log.i("BicepCurl Gyroscope","z: "+vector.z());
		if(started) {
		if(down && vector.z() > minAngle) {
		down = false;
		rep++;
	} else if (!down && vector.z() < downAngle) {
	down = true;
}]
}
}*/

}

}
