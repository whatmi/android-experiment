package kr.mog.android.lib.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesManager {
	
	static PreferencesManager mInstance;
	static Context mContext;
	static SharedPreferences mPreferences;
	static SharedPreferences.Editor mPreferencesEditer;
	
	public static void initialize(Context context) {
		mContext = context;
		mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		mPreferencesEditer = mPreferences.edit();
	}
	
	public static synchronized PreferencesManager getInstance() {
		if(mContext == null) {
			throw new IllegalArgumentException("Impossible to get the instance. This class must be initialized before");
		}
		
		if(mInstance == null) {
			mInstance = new PreferencesManager();
		}
		
		return mInstance;
	}
	
	public static boolean hasInitialized() {
		return mContext != null;
	}
	
	public static String getAccessToken() {
		return mPreferences.getString("ACCESS_TOKEN", null);
	}
	
	public static void setAccessToken(String value) {
		mPreferencesEditer.putString("ACCESS_TOKEN", value);
		mPreferencesEditer.apply();
		mPreferencesEditer.commit();
	}
	
	
}
