package com.example.cubicmetercommunityapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class RoleActivity extends FragmentActivity implements
		OnButtonClick {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_role);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		Fragment fragment = new SelectGroupFragment();
		if (savedInstanceState != null) {
			fragment = getSupportFragmentManager().getFragment(
					savedInstanceState, null);
		}
		fragmentTransaction.add(R.id.role_layout, fragment);
		fragmentTransaction.commit();
	}

	@Override
	public void LoadNextFragment(Fragment nextFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.role_layout, nextFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void LoadNextFragmentWithBackstack(Fragment nextFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.role_layout, nextFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

}
