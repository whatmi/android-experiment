package kr.mog.android.lib;

import java.lang.reflect.Method;

import kr.mog.android.lib.utils.DisplayUtil;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

public class DisplayTest extends Activity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DisplayUtil displayUtil = new DisplayUtil(this);
		
		String debug = 	"width : " + displayUtil.getDisplayWidth() + "\n" + 
						"height : " + displayUtil.getDisplayHeight() + "\n" + 
						"desity : " + displayUtil.getDesityDpi() + "\n" +  
						"desityName : " + displayUtil.getDesityDpiName() + "\n" +
						"getWidthPixels : " + displayUtil.getWidthPixels() + "\n" +
						"getHeightPixels : " + displayUtil.getHeightPixels() + "\n" +
						"getXdpi : " + displayUtil.getXdpi() + "\n" +
						"getYdpi : " + displayUtil.getYdpi();
		Toast.makeText(this, debug, Toast.LENGTH_LONG).show();
	}
	
	public String getTag() {
		return this.getClass().getSimpleName();
	}
}
