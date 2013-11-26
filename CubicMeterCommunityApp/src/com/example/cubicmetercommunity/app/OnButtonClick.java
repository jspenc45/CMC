package com.example.cubicmetercommunity.app;

import android.content.Intent;
import android.support.v4.app.Fragment;

public interface OnButtonClick {
	public void LoadNextFragment(Fragment nextFragment);
	public void LoadNextFragmentWithBackstack(Fragment nextFragment);
	public void popStack(Fragment fragment);
	public void sendIntentWithExtra(Intent i);
}