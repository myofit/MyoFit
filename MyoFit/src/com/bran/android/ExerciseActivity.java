package com.bran.android;

import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Hub.LockingPolicy;
import com.thalmic.myo.scanner.ScanActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ExerciseActivity extends Activity implements UIManager {
	
	private TextView exercise_nameTV;
	private TextView setTV;
	private TextView repTV;
	
	private ExerciseManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_exercise);
		
		// MOVE TO MIDDLE CONNECTION ACTIVITY
		
		// Create Hub for Connection
		Hub hub = Hub.getInstance();

		// Unlock Myo Permanently
		hub.setLockingPolicy(LockingPolicy.NONE);

		// Terminate Usage Data Sending
		hub.setSendUsageData(false);
		
		Log.i("MyoTestActivity", "not connected");
		
		// Initialize Hub by Context
		if (!hub.init(this,getPackageName())) {
			// Hub Issue
			Log.e("MyoTestActivity","Could not initialize hub");
			// Terminate Activity
			finish();
		} else
			Log.i("MyoTestActivity", "connected");
		
		Log.i("MyoTestActivity", "Myo Not Synced");
		
		// Myo SDK Activity for Connecting (called on Begin Workout Click) 
		Intent intent = new Intent(this,ScanActivity.class);
		startActivity(intent);
		
		hub.setLockingPolicy(LockingPolicy.NONE);
		Log.i("MyoTestActivity", "MyoTestActivity - "+hub.getLockingPolicy());
		
		manager = new ExerciseManager(this, new ExerciseType[]{ExerciseType.BICEP_CURL,ExerciseType.BENCH_PRESS,ExerciseType.TRICEP_PUSHDOWN});
		
		// Create Device Listener
		DeviceListener listener = new MyDeviceListener(manager);

		// Add Listener to Hub
		hub.addListener(listener);
		
		exercise_nameTV = (TextView) this.findViewById(R.id.exercise_name);
		
		setTV = (TextView) this.findViewById(R.id.set);
		
		repTV = (TextView) this.findViewById(R.id.rep);
		
		update();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exercise, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void update() {
		
		exercise_nameTV.setText(manager.exercise_name);
		setTV.setText(manager.set);
		repTV.setText(manager.rep);
		
	}
}
