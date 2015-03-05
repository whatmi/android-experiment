package kr.whatmi.camera;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;


/**
 * 사진 및 동영상 촬영 샘플 구현
 * : 디바이스의 기본 카메라를 이용
 *
 * Created by hmj on 2015. 3. 5. 오전 11:27:21
 *
 */
public class TakePictureActivity extends Activity {
	
	static final int REQUEST_IMAGE_CAPTURE = 1;
	
	ImageView mImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		mImageView = (ImageView)findViewById(R.id.imageView);
		mImageView.setVisibility(View.GONE);
	}
	
	// 사진 촬영 버튼 클릭시
	public void onTakePictureClick(View view) {
		dispatchTakePaictureIntent();
	}
	
	// 동영상 촬영 버튼 클릭시
	public void onTakeMovieClick(View view) {
		
	}
	
	// 사진 촬영 인텐트 발생
	private void dispatchTakePaictureIntent() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if(intent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap)extras.get("data");
			mImageView.setImageBitmap(imageBitmap);
			mImageView.setVisibility(View.VISIBLE);
		}
	}
}
