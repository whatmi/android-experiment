package kr.mog.android.lib;

import kr.mog.android.lib.utils.MemoryUtill;
import android.app.Activity;
import android.app.ActivityManager.MemoryInfo;
import android.os.Bundle;
import android.util.Log;

public class MemoryInfoSample extends Activity {
	
	static final String TAG = "MemoryInfoSample";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		MemoryInfo _memoryInfo = MemoryUtill.getMemoryInfo(this);
		
		Log.i(TAG, "memory info totalMem : " + (_memoryInfo.totalMem / 1048576L) + "MB");
		Log.i(TAG, "memory info availMem : " + (_memoryInfo.availMem / 1048576L) + "MB");
		Log.i(TAG, "memory info lowMemory : " + _memoryInfo.lowMemory);
		Log.i(TAG, "memory info threshold : " + (_memoryInfo.threshold / 1048576L) + "MB");
	}
}
