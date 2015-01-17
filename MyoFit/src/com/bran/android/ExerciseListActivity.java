package com.bran.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise_list);
		
		ListView exerciseSeq = (ListView) this.findViewById(R.id.exerciselistview);
		
		//TODO: this is a bullshit ass excuse of an arraylist fix it or you will be cursed by the bbeanies
		ArrayList<String> exerciseListNames = new ArrayList<String>();
		exerciseListNames.add("workout scrim exercise");
		exerciseListNames.add("tablet mode experience exercise");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,exerciseListNames);
        exerciseSeq.setAdapter(adapter);
        
        for (int i = 0; i < exerciseSeq.getAdapter().getCount(); i++)
			exerciseSeq.setItemChecked(i, true);
		
		exerciseSeq.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				ListView lv = (ListView) parent;
				
				if (lv.isItemChecked(position))
				{
					  Intent intent = new Intent(ExerciseListActivity.this, ExerciseDataActivity.class);
					  startActivity(intent);
				}
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
