package kr.mog.android.lib.utils;

import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

public class DisplayUtil {
	
	Activity $activity;
	
	public DisplayUtil(Activity activity) {
		$activity = activity;
	}
	
	public int getDisplayWidth() {
		Display display = $activity.getWindowManager().getDefaultDisplay();
		
		int _displayWidth;
		
		if(getSdkVersion() >= 17) {
			DisplayMetrics outMetrics = new DisplayMetrics();
			display.getRealMetrics(outMetrics);
			
			_displayWidth = outMetrics.widthPixels;
			
		} else if(getSdkVersion() >= 14) {
			try {
				Method rawW = Display.class.getMethod("getRawWidth");
				_displayWidth = (Integer) rawW.invoke(display);
			} catch(Exception e) {
				_displayWidth = display.getWidth();
				e.printStackTrace();
			}
		} else {
			_displayWidth = display.getWidth();
		}
		return _displayWidth;
	}
	
	public int getDisplayHeight() {
		Display display = $activity.getWindowManager().getDefaultDisplay();
		
		int _displayHeight;
		
		if(getSdkVersion() >= 17) {
			DisplayMetrics outMetrics = new DisplayMetrics();
			display.getRealMetrics(outMetrics);
			
			_displayHeight = outMetrics.heightPixels;
			
		} else if(getSdkVersion() >= 14) {
			try {
				Method rawH = Display.class.getMethod("getRawHeight");
				_displayHeight = (Integer) rawH.invoke(display);
			} catch(Exception e) {
				_displayHeight = display.getHeight();
				e.printStackTrace();
			}
		} else {
			_displayHeight = display.getHeight();
		}
		return _displayHeight;
	}
	
	public int getDesityDpi() {
		DisplayMetrics metrics = $activity.getResources().getDisplayMetrics();
		return metrics.densityDpi;
	}
	
	public float getDesity() {
		DisplayMetrics metrics = $activity.getResources().getDisplayMetrics();
		return metrics.density;
	}
	
	public int getWidthPixels() {
		DisplayMetrics metrics = $activity.getResources().getDisplayMetrics();
		return metrics.widthPixels;
	}
	
	public int getHeightPixels() {
		DisplayMetrics metrics = $activity.getResources().getDisplayMetrics();
		return metrics.heightPixels;
	}
	
	public float getXdpi() {
		DisplayMetrics metrics = $activity.getResources().getDisplayMetrics();
		return metrics.xdpi;
	}
	
	public float getYdpi() {
		DisplayMetrics metrics = $activity.getResources().getDisplayMetrics();
		return metrics.ydpi;
	}
	
	public String getDesityDpiName() {
		float _density = getDesity();
		
		if(_density >= 4.0) {
			return "xxxhdpi";
		} 
		else if(_density >= 3.0) {
			return "xxhdpi";
		}
		else if(_density >= 2.0) {
			return "xhdpi";
		}
		else if(_density >= 1.5) {
			return "hdpi";
		}
		else if(_density >= 1.0) {
			return "mdpi";
		}
		
		return "ldpi";
	}
	
	public int getSdkVersion() {
		return Build.VERSION.SDK_INT;
	}
}
