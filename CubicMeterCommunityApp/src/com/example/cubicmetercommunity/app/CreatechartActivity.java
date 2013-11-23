package com.example.cubicmetercommunity.app;

import com.example.cubicmetercommunityapp.R;
import com.example.cubicmetercommunityapp.R.layout;
import com.example.cubicmetercommunityapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreatechartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createchart);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.createchart, menu);
		return true;
	}

}
