package kr.mog.android.lib;

import kr.mog.android.lib.R;
import kr.mog.android.lib.MogApplication.TrackerName;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends Activity {
	
	AppTracker _tracker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TextView txt = (TextView)findViewById(R.id.txt);
		txt.setText(getSimpleName());
		
		_tracker = new AppTracker(this, getSimpleName());
	}
	
	public void onClick(View v) {
		_tracker.addEvent(getSimpleName(), "Press Button", "Page Button");
		
		Intent intent = new Intent(this, MainActivity2.class);
		startActivity(intent);
		finish();
	}
	
	private String getSimpleName() {
		return this.getClass().getSimpleName();
	}
	
	private void initialize() {
		
	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
		
		initialize();
		
		_tracker.onStart();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		
		_tracker.onStop();
	}
}

