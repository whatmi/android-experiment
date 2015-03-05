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
 * ���� �� ������ �Կ� ���� ����
 * : ����̽��� �⺻ ī�޶� �̿�
 *
 * Created by hmj on 2015. 3. 5. ���� 11:27:21
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
	
	// ���� �Կ� ��ư Ŭ����
	public void onTakePictureClick(View view) {
		dispatchTakePaictureIntent();
	}
	
	// ������ �Կ� ��ư Ŭ����
	public void onTakeMovieClick(View view) {
		
	}
	
	// ���� �Կ� ����Ʈ �߻�
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
