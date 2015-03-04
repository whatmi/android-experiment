package com.ap;

import com.ap.SchemaHelper.StudentTable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;

public class BasicQueryActivity3 extends android.app.Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SchemaHelper sch = new SchemaHelper(this);
		SQLiteDatabase db = sch.getWritableDatabase();
		
		/*
		 *  WHERE 필터 - 주(state)로 필터링한다.
		 */
		
		System.out.println("METHOD 1");
		
		// 방법 #1 - SQLITEDATABASE RAWQUERY()
		Cursor c = db.rawQuery("SELECT * from " + StudentTable.TABLE_NAME + " WHERE " + StudentTable.STATE + "= ? ", new String[]{ "IL" });
		
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.NAME);
			int colid2 = c.getColumnIndex(StudentTable.STATE);
			String name = c.getString(colid);
			String state = c.getString(colid2);
			System.out.println("GOT STUDENT " + name + " FROM " + state);
		}
		
		System.out.println("METHOD 2");
		
		// 방법 #2 - SQLITEDATABASE QUERY()
		c = db.query(StudentTable.TABLE_NAME, null, StudentTable.STATE + "= ?", new String[] { "IL" }, null, null, null);
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.NAME);
			int colid2 = c.getColumnIndex(StudentTable.STATE);
			String name = c.getString(colid);
			String state = c.getString(colid2);
			System.out.println("GOT STUDENT " + name + " FROM " + state);
		}
		
		System.out.println("METHOD 3");
		
		// 방법 #3 - SQLITEQUERYBUILDER
		String query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, null, StudentTable.STATE + "='IL'", null, null, null, null);
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
