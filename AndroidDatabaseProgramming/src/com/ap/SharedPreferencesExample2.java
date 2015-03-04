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
		 * 사용자의 첫 방문인지 체크한다.
		 */
		boolean hasVisited = sp.getBoolean("hasVisited", false);
		
		if(!hasVisited) {
			
			Toast.makeText(getApplicationContext(), "처음 방문했어", Toast.LENGTH_SHORT).show();
			// ....
			// 스플래시 액티비티와 로그인 액티비티 등을 보여준다
			// ....
			// 변경을 적용하는 것을 잊지 말자!
			Editor e = sp.edit();
			e.putBoolean("hasVisited", true);
			e.commit();
		} else {
			Toast.makeText(getApplicationContext(), "처음 방문이 아니네 ?", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	
}
