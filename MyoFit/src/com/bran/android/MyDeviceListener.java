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
	
	private long timestampDiff;
	
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
		
		manager.processData(myo, timestamp, timestampDiff, pose, DataType.POSE);
		timestampDiff = timestamp;
		
		manager.update();
		
	}
	
	@Override
	public void onAccelerometerData(Myo myo, long timestamp, Vector3 accel) {
		manager.processData(myo,timestamp,accel,DataType.ACCELEROMETER);
	}
	
	@Override
	public void onGyroscopeData(Myo myo, long timestamp, Vector3 gyro) {
		manager.processData(myo,timestamp,gyro,DataType.GYROSCOPE);
	}
	
	@Override
	public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection) {
		Log.i("MyoTestActivity", "Myo Synced");
	}
	
}
