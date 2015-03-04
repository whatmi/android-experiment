package kr.mog.android.lib.activities;

import kr.mog.android.lib.interfaces.IBaseActivity;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class BaseActivity extends Activity implements IBaseActivity {
	
	
	protected Handler handler;
	
	
	
	//-----------------------------------------------
	//
	// Custom Methods
	//
	//-----------------------------------------------
	
	
	
	
	//-----------------------------------------------
	//
	// Activity Lifecycle
	//
	//-----------------------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		destroy();
		super.onDestroy();
	}
	
	
	//-----------------------------------------------
	//
	// implements
	//
	//-----------------------------------------------
	
	@Override
	public void initialize() {
		handler = new Handler();
	}
	
	@Override
	public void destroy() {
		if(handler != null) 
			handler.removeCallbacksAndMessages(null);
	}
	
	@Override
	public String getTag() {
		return getClass().getSimpleName().toString();
	}
	
	
	//-----------------------------------------------
	//
	// Basic Methods
	//
	//-----------------------------------------------
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
	}


}
