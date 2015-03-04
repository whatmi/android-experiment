package kr.mog.android.lib;

import android.app.Activity;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class AppTracker {
	
	Tracker t;
	
	Activity _activity;
	
	public AppTracker(Activity activity, String screenName) {
		this._activity = activity;
		
		t= ((MogApplication)activity.getApplication()).getTracker(MogApplication.TrackerName.APP_TRACKER);
		
		t.setScreenName(screenName);
		t.send(new HitBuilders.AppViewBuilder().build());
	}
	
	public void addEvent(String category, String action, String label) {
		t.send(new HitBuilders.EventBuilder()
				.setCategory(category)
				.setAction(action)
				.setLabel(label)
				.build());
	}
	
	public void onStart() {
		GoogleAnalytics.getInstance(_activity.getApplicationContext()).reportActivityStart(_activity);
	}
	
	public void onStop() {
		GoogleAnalytics.getInstance(_activity.getApplicationContext()).reportActivityStop(_activity);
	}
	
}
