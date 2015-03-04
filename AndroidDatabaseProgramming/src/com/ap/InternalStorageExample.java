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
		
		// ���� �̸�
		String fileName = "my_file.txt";
		
		// ���Ͽ� �� ���ڿ�
		String msg = "Hello World";
		
		try {
			// ���� ���� �� ����
			FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(msg.getBytes());
			fos.close();
		} catch(IOException e) { 
			e.printStackTrace();
		}
	}
}
