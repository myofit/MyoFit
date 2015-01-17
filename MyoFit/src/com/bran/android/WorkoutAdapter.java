package com.bran.android;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder>{
	private ArrayList<String> mDataset = new ArrayList<String>();
	
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
    
    public WorkoutAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }
	
	public WorkoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// create a new view
		View v = LayoutInflater.from(parent.getContext())
		.inflate(R.layout.namescreen, parent, false);
		// setTV the view's size, margins, paddings and layout parameters
		ViewHolder vh = new ViewHolder((TextView ) v);
		return vh;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onBindViewHolder(ViewHolder arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
