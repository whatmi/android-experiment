package kr.whatmi.camera;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * 사진 및 동영상 촬영 샘플 구현
 * : 디바이스의 기본 카메라를 이용
 *
 * Created by hmj on 2015. 3. 5. 오전 11:27:21
 *
 */
public class SaveAfterTakePictureActivity extends Activity {
	
	static final int REQUEST_TAKE_PHOTO = 2;
	
	ImageView mImageView;
	
	String mCurrentPhotoPath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		System.out.println("onCreate()");
		
		mImageView = (ImageView)findViewById(R.id.imageView);
//		mImageView.setVisibility(View.GONE);
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
		File f = null;
		
		try {
			f = setupPhotoFile();
			mCurrentPhotoPath = f.getAbsolutePath();
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		} catch(IOException e) {
			e.printStackTrace();
			f = null;
			mCurrentPhotoPath = null;
		}
		
		startActivityForResult(intent, REQUEST_TAKE_PHOTO);
		
//		if(intent.resolveActivity(getPackageManager()) != null) {
//			File photoFile = null;
//			try {
//				photoFile = createImageFile();
//			} catch(IOException e) {
//				e.printStackTrace();
//			}
//			System.out.println("photoFile != null : " + photoFile != null);
//			if(photoFile != null) {
//				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//				startActivityForResult(intent, REQUEST_TAKE_PHOTO);
//			}
//		}
	}
	
	private File createImageFile() throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "APP_" + timeStamp + "_";
		File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(
			imageFileName,	/* prefix */ 
			".jpg", 		/* suffix */
			storageDir		/* directory */
		);
		
		mCurrentPhotoPath = "file:" + image.getAbsolutePath();
		return image;
	}
	
	private File setupPhotoFile() throws IOException {
		File f = createImageFile();
		mCurrentPhotoPath = f.getAbsolutePath();
		
		return f;
	}
	
	private void galleryAddPic() {
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(mCurrentPhotoPath);
		Uri contentUri = Uri.fromFile(f);
		intent.setData(contentUri);
		this.sendBroadcast(intent);
	}
	
	private void setPic() {
		int targetW = mImageView.getWidth();
		int targetH = mImageView.getHeight();
		
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;
		
		int scaleFactor = 1;
		if((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW/targetW, photoH/targetH);
		}
		
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;
		
		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		mImageView.setImageBitmap(bitmap);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
			Toast.makeText(getApplicationContext(), "이미지를 저장하였습니다. \n" +  mCurrentPhotoPath, Toast.LENGTH_LONG).show();
			
			if(mCurrentPhotoPath != null) {
				setPic();
				galleryAddPic();
				mCurrentPhotoPath = null;
			}
		}
	}
}
