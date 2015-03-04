package com.ap;

import com.ap.SchemaHelper.StudentTable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;

public class BasicQueryActivity6 extends android.app.Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SchemaHelper sch = new SchemaHelper(this);
		SQLiteDatabase db = sch.getWritableDatabase();
		
		/*
		 * LIMIT 例
		 */
		
		System.out.println("METHOD 1");
		
		// 规过 #1 - SQLITEDATABASE RAWQUERY()
		Cursor c = db.rawQuery("SELECT * from " + StudentTable.TABLE_NAME + " LIMIT 1, 3", null);
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.NAME);
			String name = c.getString(colid);
			System.out.println("GOT STUDENT " + name);
		}
		
		System.out.println("METHOD 2");
		
		// 规过 #2 - SQLITEDATABASE QUERY()
		c = db.query(false, StudentTable.TABLE_NAME, null, null, null, null, null, null, "3");
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.NAME);
			String name = c.getString(colid);
			System.out.println("GOT STUDENT " + name);
		}
		
		System.out.println("METHOD 3");
		
		// 规过 #3 - SQLITEQUERYBUILDER
		String query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, null, null, null, null, null, "3");
		System.out.println(query);
		
		c = db.rawQuery(query, null);
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.NAME);
			String name = c.getString(colid);
			System.out.println("GOT STUDENT " + name);
		}
	}
}
