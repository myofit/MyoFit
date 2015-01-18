package com.ram.android;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager {

	public String DB_NAME;
	public int DB_VERSION;
	public String TABLE_NAME;
	
	public ArrayList<String> cols;
	
	public DBManager(String dbname, String tname, String[] cols) {
		
		this.DB_NAME = dbname;
		this.DB_VERSION = 1;
		this.TABLE_NAME = tname;
		
		this.cols = new ArrayList<String>();
		for (int i = 0; i < cols.length; i++) {
			this.cols.add(cols[i]);
		}
		
	}
	
	public String getColString() {
		
		String colString = "";
		
		for (String s : cols) {
			colString+=s+",";
		}
		
		colString = colString.substring(0,colString.length()-1);
		
		return colString;
		
	}
	
	public String createColString(String types[]) {
		
		String colString = "";
		
		int i = 0;
		
		for (String s : cols) {
			colString+=s+" "+types[i]+",";
			i++;
		}
		
		colString = colString.substring(0,colString.length()-1);
		
		return colString;
		
	}

}
