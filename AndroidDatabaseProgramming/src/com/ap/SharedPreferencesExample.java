package com.ap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;

public class SharedPreferencesExample extends Activity {
	
	static final String TAG = "SharedPreferencesExample";
	static final String MY_DB = "my_db";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Shared preference 클래스를 초기화한다.
		SharedPreferences sp = getSharedPreferences(MY_DB, Context.MODE_PRIVATE);
		
		// 에디터를 부른다 - 변경을 적용해야 한다는 점을 기억하자.
		Editor e = sp.edit();
		e.putString("testStringKey", "Hello World");
		e.putBoolean("testBooleanKey", true);
		e.commit();
		
		String stringValue = sp.getString("testStringKey", "error");
		boolean booleanValue = sp.getBoolean("testBooleanKey", false);
		
		Log.i(TAG, "Retrieved string value : " + stringValue);
		Log.i(TAG, "Retrieved boolean value : " + booleanValue);
	}
	
}
