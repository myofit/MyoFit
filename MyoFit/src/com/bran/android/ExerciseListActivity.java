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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ExerciseListActivity extends Activity {

	ArrayList<String> exerciseListNames;
	ArrayList<String> exerciseListIDs;

	String workoutID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise_list);

		workoutID = getIntent().getExtras().getString("id");

		ListView exerciseSeq = (ListView) this.findViewById(R.id.exerciselistview);

		exerciseListNames = new ArrayList<String>();
		exerciseListIDs = new ArrayList<String>();

		DBManager dbmanager_exercises = new DBManager("MyoFit","Exercises",new String[]{"name","w_id","id"},new String[]{"VARCHAR(255)","INT(6)","INT(6)"});

		SQLiteDatabase db;
		String sql = "";
		Cursor c = null;

		// Exercises Table
		db = openOrCreateDatabase(dbmanager_exercises.DB_NAME, MODE_PRIVATE, null);
		sql = "SELECT * FROM "+dbmanager_exercises.TABLE_NAME+" WHERE w_id="+workoutID+";";
		c = db.rawQuery(sql, null);
		c.moveToFirst();
		exerciseListNames.add(c.getString(0));
		exerciseListIDs.add(c.getString(2));
		while(c.moveToNext()) {
			exerciseListNames.add(c.getString(0));	
			exerciseListIDs.add(c.getString(2));
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,exerciseListNames);
		exerciseSeq.setAdapter(adapter);

		exerciseSeq.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Intent intent = new Intent(ExerciseListActivity.this, ExerciseDataActivity.class);
				intent.putExtra("e_id", exerciseListIDs.get(position));
				intent.putExtra("name", exerciseListNames.get(position));
				intent.putExtra("w_id", workoutID);
				startActivity(intent);
			
			}	
		});

		Button returnButton = (Button) this.findViewById(R.id.homebutton);
		returnButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(ExerciseListActivity.this,MyoFitActivity.class);
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
