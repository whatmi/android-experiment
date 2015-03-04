package com.ap;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;

import com.ap.SchemaHelper.StudentTable;

public class AdvancedQueryActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SchemaHelper sch = new SchemaHelper(this);
		SQLiteDatabase db = sch.getWritableDatabase();
		
		/*
		 * ORDER BY例
		 */
		System.out.println("METHOD 1");
		
		// 规过 #1 - SQLITEDATABASE RAWQUERY()
		Cursor c = db.rawQuery("SELECT * from " + StudentTable.TABLE_NAME + " ORDER BY " + StudentTable.STATE + " ASC", null);
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.NAME);
			int colid2 = c.getColumnIndex(StudentTable.STATE);
			String name = c.getString(colid);
			String state = c.getString(colid2);
			System.out.println("GOT STUDENT " + name + " FROM " + state);
		}
		
		System.out.println("METHOD 2");
		
		// 规过 #2 - SQLITEDATABASE QUERY()
		c = db.query(StudentTable.TABLE_NAME, null, null, null, null, null, StudentTable.STATE + " ASC");
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.NAME);
			int colid2 = c.getColumnIndex(StudentTable.STATE);
			String name = c.getString(colid);
			String state = c.getString(colid2);
			System.out.println("GOT STUDENT " + name + " FROM " + state);
		}
		
		System.out.println("METHOD 3");
		
		String query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, null, null, null, null, StudentTable.STATE + " ASC", null);
		System.out.println(query);
		c = db.rawQuery(query, null);
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.NAME);
			int colid2 = c.getColumnIndex(StudentTable.STATE);
			String name = c.getString(colid);
			String state = c.getString(colid2);
			System.out.println("GOT STUDENT " + name + " FROM " + state);
		}
				
	}
	
}
