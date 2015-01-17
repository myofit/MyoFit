package com.bran.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyoConnectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myo_connect);
		
		// Create Hub for Connection
		Hub hub = Hub.getInstance();

		// Unlock Myo Permanently
		hub.setLockingPolicy(LockingPolicy.NONE);
		
		// Terminate Usage Data Sending
		hub.setSendUsageData(false);
		
		// Initialize Hub by Context
		if (!hub.init(this,getPackageName())) {
			// Hub Issue
			Log.e("MyoTestActivity","Could not initialize hub");
			// Terminate Activity
			finish();
		} else
			Log.i("MyoTestActivity", "connected");
		
		// Myo SDK Activity for Connecting (called on Begin Workout Click) 
		Intent intent = new Intent(this,ScanActivity.class);
		startActivity(intent);
		
		hub.setLockingPolicy(LockingPolicy.NONE);
		Log.i("MyoTestActivity", "MyoTestActivity - "+hub.getLockingPolicy());
		
		TextView mConnectView = (TextView) this.findViewById(R.id.myoTextView);
		Button myoContinue = (Button) this.findViewById(R.id.myoContinue);
		myoContinue.setClickable(false);
		mConnectView.setText("Myo Not Connected");
		myoContinue.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
			  Intent intent = new Intent(MyoConnectActivity.this, ExerciseActivity.class);
			  startActivity(intent);
			}
				
		});
		
		//check if connected
		
		mConnectView.setText("Myo Not Synced");
		
		//check if synced
		
		mConnectView.setText("Myo Synced");
		myoContinue.setClickable(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.myo_connect, menu);
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
}
