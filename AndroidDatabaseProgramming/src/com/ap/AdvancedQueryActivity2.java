package com.ap;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;

import com.ap.SchemaHelper.StudentTable;

public class AdvancedQueryActivity2 extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SchemaHelper sch = new SchemaHelper(this);
		SQLiteDatabase db = sch.getWritableDatabase();
		
		/*
		 * GROUP BY例
		 */
		System.out.println("METHOD 1");
		
		// 规过 #1 - SQLITEDATABASE RAWQUERY()
		String colName = "COUNT (" + StudentTable.STATE + ")";
		Cursor c = db.rawQuery("SELECT " + StudentTable.STATE + ", " + colName + " from " + StudentTable.TABLE_NAME + " GROUP BY " + StudentTable.STATE, null);
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.STATE);
			int colid2 = c.getColumnIndex(colName);
			String state = c.getString(colid);
			int count = c.getInt(colid2);
			System.out.println("STATE " + state + " HAS COUNT " + count);
		}
		
		System.out.println("METHOD 2");
		
		// 规过 #2 - SQLITEDATABASE QUERY()
		c = db.query(StudentTable.TABLE_NAME, new String[] { StudentTable.STATE, colName }, null, null, StudentTable.STATE, null, null);
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.STATE);
			int colid2 = c.getColumnIndex(colName);
			String state = c.getString(colid);
			int count = c.getInt(colid2);
			System.out.println("STATE " + state + " HAS COUNT " + count);
		}
		
		System.out.println("METHOD 3");
		
		String query = SQLiteQueryBuilder.buildQueryString(false, StudentTable.TABLE_NAME, new String[] { StudentTable.STATE, colName }, null, StudentTable.STATE, null, null, null);
		System.out.println(query);
		c = db.rawQuery(query, null);
		while(c.moveToNext()) {
			int colid = c.getColumnIndex(StudentTable.STATE);
			int colid2 = c.getColumnIndex(colName);
			String state = c.getString(colid);
			int count = c.getInt(colid2);
			System.out.println("STATE " + state + " HAS COUNT " + count);
		}
				
	}
	
}
