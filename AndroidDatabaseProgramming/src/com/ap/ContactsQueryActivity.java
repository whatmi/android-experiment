package com.ap;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

public class ContactsQueryActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*
		 * QUERY EXAMPLE
		 */
		// 컨택 검색을 위한 첫번째 쿼리
		Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, 
				new String[] { ContactsContract.Contacts._ID,
					ContactsContract.Contacts.DISPLAY_NAME,
					ContactsContract.Contacts.LOOKUP_KEY }, 
				ContactsContract.Contacts.DISPLAY_NAME + " IS NOT NULL", 
				null, 
				null);
		
		startManagingCursor(c);
		
		int idCol = c.getColumnIndex(Contacts._ID);
		int nameCol = c.getColumnIndex(Contacts.DISPLAY_NAME);
		int lookCol = c.getColumnIndex(Contacts.LOOKUP_KEY);
		
		// 룩업 값을 추적하기 위해 맵을 사용한다.
		Map<String, String> loopups = new HashMap<String, String>();
		while(c.moveToNext()) {
			int id = c.getInt(idCol);
			String name = c.getString(nameCol);
			String lookup = c.getString(lookCol);
			loopups.put(name, lookup);
			System.out.println("GOT " + id + " // " + lookup + " // " + name + " FROM CONTACNTS");
		}
	}
	
}
