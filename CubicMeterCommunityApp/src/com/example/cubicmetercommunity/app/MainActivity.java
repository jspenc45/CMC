package com.example.cubicmetercommunity.app;

import com.example.cubicmetercommunityapp.R;
import com.salesforce.androidsdk.auth.HttpAccess;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


//This class handles the main menu activity for the project

public class MainActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		HttpAccess.init(this, null);
		
		((Button) findViewById(R.id.soilcolorbackbutton)).setOnClickListener(this);
		((Button) findViewById(R.id.btn_reviewData)).setOnClickListener(this);
		((Button) findViewById(R.id.btn_createCharts)).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
		case R.id.soilcolorbackbutton:
			i = new Intent(getBaseContext(), RoleActivity.class);
			startActivity(i);
			break;
			
		case R.id.btn_reviewData:
			//i = new Intent(getBaseContext(), ReviewdataActivity.class);
			i = new Intent(getBaseContext(), Review_2Activity.class);			
			startActivity(i);
			break;
			
		case R.id.btn_createCharts:
			i = new Intent(getBaseContext(), CreatechartActivity.class);
			startActivity(i);
			break;
		}
	}
}
