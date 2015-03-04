package kr.mog.android.lib;

import kr.mog.android.lib.dialog.LoadingDialogFragment;
import kr.mog.android.lib.manager.PreferencesManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;


/**
 * 
 * Logout
 * intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
 * 
 * @author hmj
 *
 */
public class LoginScreen extends FragmentActivity implements LoadingDialogFragment.OnDialogClosedListener {
	
	EditText mEditEmail;
	EditText mEditPassword;
	Button mBtnLogin;
	boolean isEmailValid;
	boolean isPasswordValid;
	boolean isDialogVisible;
	LoadingDialogFragment mDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		if(PreferencesManager.hasInitialized() == false) {
			PreferencesManager.initialize(getApplicationContext());
		}
		
		initView();
	}
	
	private void initView() {
		mEditEmail = (EditText)findViewById(R.id.editEmail);
		mEditPassword = (EditText)findViewById(R.id.editPassword);
		mBtnLogin = (Button)findViewById(R.id.btnLogin);
		
		mEditEmail.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.e(getTag(), "mEditEmail > onTextChanged");
				validateEmail(s.toString());
				updateLoginButtonState();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
			
		});
		
		mEditPassword.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.e(getTag(), "mEditPassword > onTextChanged");
				validatePassword(s.toString());
				updateLoginButtonState();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		mEditPassword.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				boolean isValidKey = event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER;
				boolean isValidAction = actionId == EditorInfo.IME_ACTION_DONE;
				
				if(isValidKey || isValidAction) {
					// do login request
					requestLogin();
				}
				
				return false;
			}
		});
		
		
		mBtnLogin.setEnabled(false);
		
		mDialog = new LoadingDialogFragment();
	}
	
	void requestLogin() {
		Log.e(getTag(), "requestLogin()");
		
		PreferencesManager.getInstance().setAccessToken("111");
		
		showLoadingDialog();
	}
	
	void showLoadingDialog() {
		mDialog.show(getSupportFragmentManager(), "LoginScreen");
		isDialogVisible = true;
	}
	
	void closeLoadingDialog() {
		mDialog.dismiss();
		isDialogVisible = false;
	}
	
	void validatePassword(String text) {
		isPasswordValid = !text.isEmpty();
	}
	
	void validateEmail(String text) {
		isEmailValid = Patterns.EMAIL_ADDRESS.matcher(text).matches();
	}
	
	void updateLoginButtonState() {
		if(isEmailValid && isPasswordValid) {
			mBtnLogin.setEnabled(true);
		} else {
			mBtnLogin.setEnabled(false);
		}
		
		Log.e(getTag(), "updateLoginButtonState() " + mBtnLogin.isEnabled());
	}
	
	public boolean isNetworkOn(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		
		return (networkInfo != null && networkInfo.isConnected());
	}
	
	boolean isDataValid() {
		boolean isEmailValid = Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
		boolean isPasswordValid = !getPassword().isEmpty();
		
		Log.e(getTag(), "isDataValid() > isEmailValid : " + isEmailValid);
		Log.e(getTag(), "isDataValid() > isPasswordValid : " + isPasswordValid);
		return isEmailValid && isPasswordValid;
	}
	
	String getEmail() {
		return mEditEmail.getText().toString().trim();
	}
	
	String getPassword() {
		return mEditPassword.getText().toString().trim();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		if(isDialogVisible) {
			closeLoadingDialog();
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		                                                                                                                    
		if(isDialogVisible) {
			showLoadingDialog();
		}
	}

	@Override
	public void onDialogClosed() {
		// cancel request
	}

	public void onLoginClicked(View v) {
		Log.d(getTag(), "do login request");
		if(!isNetworkOn(getBaseContext())) {
			Log.d(getTag(), "do login request > No network connection");
			Toast.makeText(getBaseContext(), "No network connection", Toast.LENGTH_SHORT).show();
		} else {
			Log.d(getTag(), "do login request > request");
			requestLogin();
		}
	}
	
	String getTag() {
		return this.getClass().getSimpleName();
	}
}
