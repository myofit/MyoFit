package com.bran.android;

import android.util.Log;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Arm;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.XDirection;

public class MyDeviceListener extends AbstractDeviceListener {

	private ExerciseManager manager;
	
	private Pose curPose;
	private long timestampDiff;
	
	private static final int TIME_DIFF = 2000;
	
	public MyDeviceListener(ExerciseManager manager) {
		this.manager = manager;
		this.timestampDiff = 0;
	}
	
	@Override
	public void onConnect(Myo myo, long timestamp) {
		
	}

	@Override
	public void onDisconnect(Myo myo, long timestamp) {
		
	}

	@Override
	public void onPose(Myo myo, long timestamp, Pose pose) {
		
		Log.i("MyDeviceListener","MyDeviceListener - "+pose.toString());
		
		if (pose.equals(Pose.DOUBLE_TAP)) {

			if (timestamp - timestampDiff > TIME_DIFF) {
				curPose = pose;
				timestampDiff = timestamp;
				manager.next();
			}
			
		} else if (pose.equals(Pose.FIST)) {
			manager.nextSet();
		} else if (pose.equals(Pose.FINGERS_SPREAD)) {
			manager.endSet();
		}
		
	}
	
	@Override
	public void onAccelerometerData(Myo myo, long timestamp, Vector3 accel) {
		
	}
	
	@Override
	public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection) {
		Log.i("MyoTestActivity", "Myo Synced");
		//change image to myo synced
		//add button for Continue
	}
	
}
