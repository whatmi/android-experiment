package com.ap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Toast;

public class SharedPreferencesExample2 extends Activity {
	
	static final String MY_DB = "my_db";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences sp = getSharedPreferences(MY_DB, Context.MODE_PRIVATE);
		
		/**
		 * ������� ù �湮���� üũ�Ѵ�.
		 */
		boolean hasVisited = sp.getBoolean("hasVisited", false);
		
		if(!hasVisited) {
			
			Toast.makeText(getApplicationContext(), "ó�� �湮�߾�", Toast.LENGTH_SHORT).show();
			// ....
			// ���÷��� ��Ƽ��Ƽ�� �α��� ��Ƽ��Ƽ ���� �����ش�
			// ....
			// ������ �����ϴ� ���� ���� ����!
			Editor e = sp.edit();
			e.putBoolean("hasVisited", true);
			e.commit();
		} else {
			Toast.makeText(getApplicationContext(), "ó�� �湮�� �ƴϳ� ?", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	
}
