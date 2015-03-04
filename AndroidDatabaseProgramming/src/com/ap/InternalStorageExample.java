package com.ap;

import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class InternalStorageExample extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 파일 이름
		String fileName = "my_file.txt";
		
		// 파일에 쓸 문자열
		String msg = "Hello World";
		
		try {
			// 파일 생성 후 쓰기
			FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(msg.getBytes());
			fos.close();
		} catch(IOException e) { 
			e.printStackTrace();
		}
	}
}
