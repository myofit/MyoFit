package com.bran.android;

import android.util.Log;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Myo.VibrationType;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.XDirection;

public class BicepCurl extends Exercise {

	private boolean down = true;
	private float minAngle = 25;
	private float downAngle = -10;
	
	//TODO: SENSITIVITY SLIDER
	private float formThreshold = 40;

	//TODO: ALLOW MULTIPLE WEARING ORIENTATION
	private int direction;
	
	private long formTimeDiff;
	private static final int TIME_DIFF = 2000;

	public BicepCurl() {
		super("Bicep Curl",ExerciseType.BICEP_CURL);

		direction = 1;
		form = true;

	}

	@Override
	public void processData(Myo myo, long timestamp, Quaternion quaternion, DataType type) {

		if (myo.getXDirection().equals(XDirection.TOWARD_ELBOW))
			direction = -1;

		if(type.equals(DataType.ORIENTATION)) {

			float pitch = (float) Math.toDegrees(Quaternion.pitch(quaternion));
			float roll = (float) Math.toDegrees(Quaternion.roll(quaternion));
			float yaw = (float) Math.toDegrees(Quaternion.yaw(quaternion));

			//Log.i("BicepCurl", "pitch: "+pitch);
			//Log.i("BicepCurl", "roll: "+roll);
			//Log.i("BicepCurl", "yaw: "+yaw);

			if(started) {
				if(down && pitch > minAngle) {
					down = false;
					rep++;
					if (!form)
						form = true;
					// TODO: MAKE OPTION
					// myo.vibrate(VibrationType.SHORT);
				} else if (!down && pitch < downAngle) {
					down = true;
				}
			}
		}

	}

	@Override
	public void processData(Myo myo, long timestamp, Vector3 vector, DataType type) {

		if(type.equals(DataType.GYROSCOPE)) {
			
			if (timestamp - formTimeDiff > TIME_DIFF) {

				//Log.i("BicepCurl", "Gyro x: "+vector.x());
				//Log.i("BicepCurl", "Gyro y: "+vector.y());
				//Log.i("BicepCurl","Gyro z: "+vector.z());
			
				if(started) {
					if (vector.x() > formThreshold) {
						myo.vibrate(VibrationType.SHORT);
						formTimeDiff = timestamp;
						form = false;
					}
				}
			
			}
			
		}

	}

}
