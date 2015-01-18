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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class WorkoutSelectActivity extends Activity {

	ArrayList<ExerciseType> exerciseSelections;
	ArrayList<ExerciseType> exerciseList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_workout_select);
		
		exerciseList = new ArrayList<ExerciseType>();
		exerciseList.add(ExerciseType.BICEP_CURL);
		exerciseList.add(ExerciseType.DUMBBELL_FLY);
		exerciseList.add(ExerciseType.SIDE_LATERAL_RAISE);
		exerciseList.add(ExerciseType.TRICEP_KICKBACK);
		ArrayList<String> exerciseListNames = new ArrayList<String>();
		for (ExerciseType exercise : exerciseList)
			exerciseListNames.add(ExerciseManager.getName(exercise));
		
		ListView exerciseSeq = (ListView) this.findViewById(R.id.namelistview);
		
        // specify an adapter (see also next example)
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,exerciseListNames);
        exerciseSeq.setAdapter(adapter);
		
		// Exercises in Workout
		exerciseSelections = new ArrayList<ExerciseType>();
		
		exerciseSeq.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		for (int i = 0; i < exerciseSeq.getAdapter().getCount(); i++)
			exerciseSeq.setItemChecked(i, true);
		
		exerciseSeq.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				ListView lv = (ListView) parent;
				
				if (lv.isItemChecked(position))
				{
					view.setBackgroundColor(Color.TRANSPARENT);
					exerciseSelections.remove(exerciseList.get(position));
					lv.setItemChecked(position, false);
				} else {
					view.setBackgroundColor(Color.parseColor("#64B5F6"));
					exerciseSelections.add(exerciseList.get(position));
					lv.setItemChecked(position, true);
				}
				lv.setItemChecked(position,!lv.isItemChecked(position));
			}
			
		});
		
		Button beginWorkoutButton = (Button) this.findViewById(R.id.beginbutton);
		beginWorkoutButton.setText("Begin Workout");
		beginWorkoutButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

				if (exerciseSelections.size() > 0) {

					Intent intent = new Intent(WorkoutSelectActivity.this, ExerciseActivity.class);

					int size = 0;
					
					for (ExerciseType type : exerciseSelections) {
						intent.putExtra("Exercise "+size, type);
						intent.putExtra("Workout", exerciseSelections);
						size++;
					}

					intent.putExtra("Size",size);

					startActivity(intent);

				} else {
					Toast.makeText(WorkoutSelectActivity.this, "Must Selected At Least One Exercise", Toast.LENGTH_SHORT).show();
				}
				
				
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
