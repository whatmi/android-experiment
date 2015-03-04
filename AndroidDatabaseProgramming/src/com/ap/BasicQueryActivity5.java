package com.ap;

import com.ap.SchemaHelper.StudentTable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;

public class BasicQueryActivity5 extends android.app.Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SchemaHelper sch = new SchemaHelper(this);
		SQLiteDatabase db = sch.getWritableDatabase();
		
		/*
		 * DISTINCT 例
		 */
		
		System.out.println("METHOD 1");
		
		// 规过 #1 - SQLITEDATABASE RAWQUERY()
		Cursor c = db.rawQuery("SELECT DISTINCT " + StudentTable.STATE + " from " + StudentTable.TABLE_NAME, null);
		while(c.moveToNext()) {
			int colid2 = c.getColumnIndex(StudentTable.STATE);
			String state = c.getString(colid2);
			System.out.println("GOT STATE " + state);
		}
		
		System.out.println("METHOD 2");
		
		// 规过 #2 - SQLITEDATABASE QUERY()
		c = db.query(true, StudentTable.TABLE_NAME, new String[] { StudentTable.STATE }, null, null, null, null, null, null);
		while(c.moveToNext()) {
			int colid2 = c.getColumnIndex(StudentTable.STATE);
			String state = c.getString(colid2);
			System.out.println("GOT STATE " + state);
		}
		
		System.out.println("METHOD 3");
		
		// 规过 #3 - SQLITEQUERYBUILDER
		String query = SQLiteQueryBuilder.buildQueryString(true, StudentTable.TABLE_NAME, new String[] { StudentTable.STATE }, null, null, null, null, null);
		System.out.println(query);
		
		c = db.rawQuery(query, null);
		while(c.moveToNext()) {
			int colid2 = c.getColumnIndex(StudentTable.STATE);
			String state = c.getString(colid2);
			System.out.println("GOT STATE " + state);
		}
	}
}
