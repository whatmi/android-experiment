package kr.mog.android.lib;

import kr.mog.android.lib.manager.PreferencesManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DashboardActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		PreferencesManager.initialize(getBaseContext());
		
		if(!isUserSignedIn()) {
			finish();
			startLoginActivity();
			return;
		}
		
		setContentView(R.layout.dash_board);
		
		// do initialization
	}
	
	
	// retrieve access token from preferences
	boolean isUserSignedIn() {
		return PreferencesManager.getInstance().getAccessToken() != null;
	}
	
	void startLoginActivity() {
		Intent intent = new Intent(this, LoginScreen.class);
		startActivity(intent);
	}
}
