package com.example.cubicmetercommunityapp;

import android.support.v4.app.Fragment;

public interface OnButtonClick {
	public void LoadNextFragment(Fragment nextFragment);
	public void LoadNextFragmentWithBackstack(Fragment nextFragment);
}
