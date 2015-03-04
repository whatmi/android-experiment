package kr.mog.android.lib.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

public class LoadingDialogFragment extends DialogFragment {
	
	public static LoadingDialogFragment newInstance() {
		return new LoadingDialogFragment();
	}
	
	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		ProgressDialog dialog = new ProgressDialog(getActivity());
		dialog.setMessage("Loading...");
		dialog.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				triggerActivityListener();
			}

		});
		return dialog;
	}
	
	private void triggerActivityListener() {
		if(getActivity() instanceof OnDialogClosedListener) {
			OnDialogClosedListener listener = (OnDialogClosedListener)getActivity();
			listener.onDialogClosed();
		}
	}
	
	public interface OnDialogClosedListener {
		public void onDialogClosed();
	}
	
}
