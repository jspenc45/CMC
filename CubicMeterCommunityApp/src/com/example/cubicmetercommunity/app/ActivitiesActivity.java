package com.example.cubicmetercommunity.app;

import com.example.cubicmetercommunity.classes.TableIDs;
import com.example.cubicmetercommunityapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

//This activity populates a list of activities given the role passed.

public class ActivitiesActivity extends FragmentActivity implements
		OnButtonClick {
	
	TableIDs ids;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activities);
		
		if (getIntent().getExtras().containsKey("tables"))
			ids = (TableIDs) getIntent().getExtras().get("tables");
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		Fragment fragment = new SelectActivityFragment();
		if (savedInstanceState != null) {
			fragment = getSupportFragmentManager().getFragment(
					savedInstanceState, null);
		}
		fragmentTransaction.add(R.id.activities_layout, fragment);
		fragmentTransaction.commit();
	}

	@Override
	public void LoadNextFragment(Fragment nextFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.activities_layout, nextFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void LoadNextFragmentWithBackstack(Fragment nextFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.activities_layout, nextFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	@Override
	public void popStack(Fragment fragment) {
		FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack (fragment.getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
		
	}

	@Override
	public void sendIntentWithExtra(Intent i) {
		i.putExtra("tables", ids);
		startActivity(i);
	}
}
