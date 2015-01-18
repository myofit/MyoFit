package com.bran.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class WorkoutListActivity extends Activity {

	private ArrayList<String> workoutListNames;
	private ArrayList<String> workoutIDs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout_list);
		
		ListView workoutSeq = (ListView) this.findViewById(R.id.workoutlistview);
		
		// Read From DD
		DBManager dbmanager_workouts = new DBManager("MyoFit","Workouts",new String[]{"time","id"},new String[]{"VARCHAR(255)","INT(6)"});
		
		String sql = "";
		Cursor c = null;
		
		workoutListNames = new ArrayList<String>();
		workoutIDs = new ArrayList<String>();
		
		// Workout Table
		SQLiteDatabase db = openOrCreateDatabase(dbmanager_workouts.DB_NAME, MODE_PRIVATE, null);
		sql = "SELECT * FROM "+dbmanager_workouts.TABLE_NAME+";";
		c = db.rawQuery(sql, null);
		c.moveToFirst();
		workoutListNames.add(c.getString(0));
		workoutIDs.add(c.getString(1));
		while(c.moveToNext()) {
			workoutListNames.add(c.getString(0));
			workoutIDs.add(c.getString(1));
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,workoutListNames);
        workoutSeq.setAdapter(adapter);
        
		workoutSeq.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(WorkoutListActivity.this, ExerciseListActivity.class);
				intent.putExtra("id", ""+workoutIDs.get(position));
				startActivity(intent);
			}	
		});
		
		Button returnButton = (Button) this.findViewById(R.id.homebutton);
		returnButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
			  finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.workout_select, menu);
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
