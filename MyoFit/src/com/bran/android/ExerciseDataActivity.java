package com.bran.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ExerciseDataActivity extends Activity {

	ArrayList<String> exerciseSetsAndReps;
	
	String workoutID;
	String exerciseID;
	String exerciseName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_exercise_data);
		
		exerciseSetsAndReps = new ArrayList<String>();
		
		Bundle b = getIntent().getExtras();
		workoutID = b.getString("w_id");
		exerciseName = b.getString("name");
		exerciseID = b.getString("e_id");

		TextView tv = (TextView) this.findViewById(R.id.exercise_name);
		tv.setText(exerciseName);
		
		DBManager dbmanager_sets = new DBManager("MyoFit","Sets",new String[]{"set_col","rep","w_id","e_id"},new String[]{"INT(6)","INT(6)","INT(6)","INT(6)"});
		
		SQLiteDatabase db;
		String sql = "";
		Cursor c = null;
		
		// Sets Table
		db = openOrCreateDatabase(dbmanager_sets.DB_NAME, MODE_PRIVATE, null);
		sql = "SELECT * FROM Sets WHERE w_id="+workoutID+" and e_id="+exerciseID+";";
		c = db.rawQuery(sql, null);
		c.moveToFirst();
		exerciseSetsAndReps.add("Set "+c.getString(0)+":"+"            "+c.getString(1)+" Reps");
		while(c.moveToNext()) {
			exerciseSetsAndReps.add("Set "+c.getString(0)+":"+"            "+c.getString(1)+" Reps");
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,exerciseSetsAndReps);
		
		ListView lv = (ListView) this.findViewById(R.id.exerciseDataView);
		lv.setAdapter(adapter);
		
		Button homeButton = (Button) this.findViewById(R.id.homebutton);
		homeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ExerciseDataActivity.this,MyoFitActivity.class);
				startActivity(intent);
			}
			
		});
		
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
}
