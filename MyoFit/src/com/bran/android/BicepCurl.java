package com.bran.android;

import android.util.Log;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Myo.VibrationType;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.XDirection;

public class BicepCurl extends Exercise {

	private boolean down = true;
	private double minAngle = 25;
	private double downAngle = -10;

	private int direction;

	public BicepCurl() {
		super("Bicep Curl",ExerciseType.BICEP_CURL);

		direction = 1;

	}

	@Override
	public void processData(Myo myo, long timestamp, Quaternion quaternion, DataType type) {

		if (myo.getXDirection().equals(XDirection.TOWARD_ELBOW))
			direction = -1;

		if(type.equals(DataType.ORIENTATION)) {

			float pitch = (float) Math.toDegrees(Quaternion.pitch(quaternion));
			float roll = (float) Math.toDegrees(Quaternion.roll(quaternion));
			float yaw = (float) Math.toDegrees(Quaternion.yaw(quaternion));

			Log.i("BicepCurl", "pitch: "+pitch);
			Log.i("BicepCurl", "roll: "+roll);
			Log.i("BicepCurl", "yaw: "+yaw);

			if(started) {
				if(down && pitch > minAngle) {
					down = false;
					rep++;
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
			Log.i("BicepCurl", "x: "+vector.x());
			Log.i("BicepCurl", "y: "+vector.y());
			Log.i("BicepCurl","z: "+vector.z());
			/*if(started) {
				if(down && vector.z() > minAngle) {
					down = false;
					rep++;
				} else if (!down && vector.z() < downAngle) {
					down = true;
				}
			}*/
		}

	}

}
