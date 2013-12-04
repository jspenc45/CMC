package com.example.cubicmetercommunity.app;

import com.example.cubicmetercommunity.classes.TableIDs;
import com.example.cubicmetercommunityapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

//This activity holds new DynamicFragments, which allow users to complete activities

public class IndividualActivity extends FragmentActivity implements
		OnButtonClick {

	TableIDs ids;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getIntent().getExtras().containsKey("tables"))
			ids = (TableIDs) getIntent().getExtras().get("tables");
		
		String str = getIntent().getExtras().getString("fragmentList");
		int resource = getResources().getIdentifier(str, "layout", getBaseContext().getPackageName());
		
		setContentView(R.layout.activity_individual);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		Fragment fragment;
		if (str.equals("biodiversity_activity"))
			fragment = new BiodiversityFragment();
		else
			fragment = new DynamicFragment(resource);
		
		if (savedInstanceState != null) {
			fragment = getSupportFragmentManager().getFragment(
					savedInstanceState, null);
		}
		fragmentTransaction.add(R.id.individual_layout, fragment);
		fragmentTransaction.commit();
	}

	@Override
	public void LoadNextFragment(Fragment nextFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.individual_layout, nextFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void LoadNextFragmentWithBackstack(Fragment nextFragment) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.individual_layout, nextFragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	@Override
	public void popStack(Fragment fragment) {
		FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack (fragment.getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
		
	}

	
	public TableIDs getTables() {
		return ids;
	}

	@Override
	public void sendIntentWithExtra(Intent i) {
		// Nothing to do here
	}
}
