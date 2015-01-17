package com.bran.android;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MyoFitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set Layout
		// Layouts are in res/layout
		//setContentView(R.layout.thelayoutname);
		setContentView(R.layout.activity_myo_fit);
		
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
		
		Button startApp = (Button)this.findViewById(R.id.startButton);
		startApp.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
			  //workoutSelectActivity	
			}
		});
		
		ListView exerciseSeq = (ListView) findViewById(R.id.exerciseList);
		String[] values = new String[] {
			"Bicep Curl", "Tricep experience", "Forearm scrim"	};
		ArrayList<String> list = new ArrayList<String>();
		for (int i=0; i<values.length; i++)
			list.add(values[i]);
		
		ArrayAdapter<TextView> mAdapter = new ArrayAdapter<TextView>(this, android.R.layout.simple_list_item_1, R.layout.list_element);
		exerciseSeq.setAdapter(mAdapter);	
		
		exerciseSeq.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.myo_fit, menu);
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
