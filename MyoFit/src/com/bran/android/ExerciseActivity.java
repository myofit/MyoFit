package com.bran.android;

import java.util.ArrayList;

import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Hub.LockingPolicy;
import com.thalmic.myo.scanner.ScanActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExerciseActivity extends Activity implements UIManager {

	private TextView myoTV;

	private TextView exercise_nameTV;
	private TextView setTV;
	private TextView repTV;
	private TextView formTV;

	private TextView setValTV;
	private TextView repValTV;
	private TextView formValTV;

	private ArrayList<ExerciseType> workout;

	private Hub hub;

	private ExerciseManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_exercise);

		initUIElements();

		// Create Hub for Connection
		hub = Hub.getInstance();

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

		// Myo SDK Activity for Connecting 
		Intent intent = new Intent(this,ScanActivity.class);
		startActivity(intent);

		hub.setLockingPolicy(LockingPolicy.NONE);
		Log.i("MyoTestActivity", "MyoTestActivity - "+hub.getLockingPolicy());

		myoTV.setText("Myo Connected!");
		// FADE OUT

		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Sync Myo");

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				continueApp();

			}
		});

		alert.show();

	}

	public void initUIElements() {

		myoTV = (TextView) this.findViewById(R.id.myoTextView);

		exercise_nameTV = (TextView) this.findViewById(R.id.exercise_name);
		setTV = (TextView) this.findViewById(R.id.set);
		repTV = (TextView) this.findViewById(R.id.rep);
		formTV = (TextView) this.findViewById(R.id.form);

		setValTV = (TextView) this.findViewById(R.id.set_title);
		repValTV = (TextView) this.findViewById(R.id.rep_title);
		formValTV = (TextView) this.findViewById(R.id.form_title);

		exercise_nameTV.setVisibility(View.INVISIBLE);
		setTV.setVisibility(View.INVISIBLE);
		repTV.setVisibility(View.INVISIBLE);
		formTV.setVisibility(View.INVISIBLE);
		setValTV.setVisibility(View.INVISIBLE);
		repValTV.setVisibility(View.INVISIBLE);
		formValTV.setVisibility(View.INVISIBLE);

	}

	public void switchUIElements() {

		myoTV.setVisibility(View.INVISIBLE);

		exercise_nameTV.setVisibility(View.VISIBLE);
		setTV.setVisibility(View.VISIBLE);
		repTV.setVisibility(View.VISIBLE);
		formTV.setVisibility(View.VISIBLE);
		setValTV.setVisibility(View.VISIBLE);
		repValTV.setVisibility(View.VISIBLE);
		formValTV.setVisibility(View.VISIBLE);

	}

	public void continueApp() {

		switchUIElements();

		ArrayList<ExerciseType> workout = (ArrayList<ExerciseType>) getIntent().getExtras().get("Workout");

		ExerciseType[] workout_array = new ExerciseType[workout.size()];

		int i = 0;

		for (ExerciseType type : workout) {
			workout_array[i] = type;
			i++;
		}

		manager = new ExerciseManager(this, workout_array);

		// Create Device Listener
		DeviceListener listener = new MyDeviceListener(manager);

		// Add Listener to Hub
		hub.addListener(listener);

		// Update First Time to Fill UI Elements
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

		if (exercise_nameTV != null && manager != null) {

			exercise_nameTV.setText(manager.exercise_name);

			setTV.setText(""+manager.getSet());
			repTV.setText(""+manager.getRep());
			if (manager.getForm()) {
				formTV.setText("Good");
			} else {
				formTV.setText("Bad");
			}

		}

	}

	@Override
	public void end() {

		Intent intent = new Intent(this,WorkoutCompleteActivity.class);
		startActivity(intent);

	}

}
