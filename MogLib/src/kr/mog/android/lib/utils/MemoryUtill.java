package kr.mog.android.lib.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;


/**
 *
 *
 * Created by hmj on 2015. 3. 4. ¿ÀÈÄ 2:02:09
 *
 */
public class MemoryUtill {
	
	public static int getMemoryClass(Context context) {
		ActivityManager _activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		
		return _activityManager.getMemoryClass();
	}
	
	public static MemoryInfo getMemoryInfo(Context context) {
		ActivityManager _activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		
		MemoryInfo outInfo = new ActivityManager.MemoryInfo();
		 _activityManager.getMemoryInfo(outInfo);
		 
		return outInfo;
	}
}
