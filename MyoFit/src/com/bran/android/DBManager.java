package com.bran.android;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager {

	public String DB_NAME;
	public int DB_VERSION;
	public String TABLE_NAME;
	
	public ArrayList<String> cols;
	public ArrayList<String> types;
	
	public DBManager(String dbname, String tname, String[] cols, String[] types) {
		
		this.DB_NAME = dbname;
		this.DB_VERSION = 1;
		this.TABLE_NAME = tname;
		
		this.cols = new ArrayList<String>();
		for (int i = 0; i < cols.length; i++) {
			this.cols.add(cols[i]);
		}
		
		this.types = new ArrayList<String>();
		for (int i = 0; i < types.length; i++) {
			this.types.add(types[i]);
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
	
	public String createColString() {
		
		String colString = "";
		
		int i = 0;
		
		for (String s : cols) {
			colString+=s+" "+types.get(i)+",";
			i++;
		}
		
		colString = colString.substring(0,colString.length()-1);
		
		return colString;
		
	}
	
	public int createID(SQLiteDatabase db, int checkColumn) {
		
		int rand = (int) (Math.random()*100000);
		
		Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
		c.moveToFirst();
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		while(c.moveToNext()) {
			 ids.add(c.getInt(checkColumn));
		}
		
		while (ids.contains(rand)) {
			rand = (int) (Math.random()*100000);
		}
		
		return rand;
		
	}

}
