package com.bran.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class WorkoutSelectActivity extends Activity {

	ArrayList<String> exerciseSelections;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set Layout
		// Layouts are in res/layout
		//setContentView(R.layout.thelayoutname);
		setContentView(R.layout.activity_workout_select);
		
		// Use One Activity for each layout
		// Essentially: HomeActivity, SelectWorkoutActivity, WorkoutActivity etc.
		
		// TextView and Button are subclasses of View
		// Other examples: TextField, ImageView
		
		// Get TextView
		//TextView tv = (TextView) this.findViewById(R.id.theid);
		// Set Text
		//tv.setText("Your Text");
		
		// Get Button
		//Button b = (Button) this.findViewById(R.id.theid);
		// Set Listener
		/*b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Method For Click
				
				// For now just create a comment describing what needs to be done
				 * Ex. Store in DB, Start Another Activity
				
			}
		});*/
		
		String[] values = new String[] {
			"Bicep Curl", "Tricep experience", "Forearm scrim"	};
		ArrayList<String> exerciseList = new ArrayList<String>();
		for (int i=0; i<values.length; i++)
			exerciseList.add(values[i]);
		
		ListView exerciseSeq = (ListView) this.findViewById(R.id.namelistview);
		//exerciseSeq.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
        // specify an adapter (see also next example)
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,exerciseList);
        exerciseSeq.setAdapter(adapter);
		
		// Exercises in Workout
		exerciseSelections = new ArrayList<String>();
		
		exerciseSeq.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (((ListView)parent).isItemChecked(position))
				{
					view.setBackgroundColor(Color.WHITE);
					exerciseSelections.remove(((TextView)view).getText());
				} else {
					view.setBackgroundColor(Color.CYAN);
					exerciseSelections.add((String) ((TextView)view).getText());
				}
				((ListView)parent).setItemChecked(position,!((ListView)parent).isItemChecked(position));
				//Log.i("excercise selections",exerciseSelections.get(exerciseSelections.size()-1));
			}
			
		});
		
		Button beginWorkoutButton = (Button) this.findViewById(R.id.beginbutton);
		beginWorkoutButton.setText("Begin Workout");
		beginWorkoutButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
			  //Intent intent = new Intent(WorkoutSelectActivity.this, ExerciseActivity.class);
			  //startActivity(intent);
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
