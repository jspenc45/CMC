package com.example.cubicmetercommunity.app;

import com.example.cubicmetercommunityapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		((Button) findViewById(R.id.soilcolorbackbutton)).setOnClickListener(this);
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
			i = new Intent(getBaseContext(), ReviewdataActivity.class);
			startActivity(i);
			break;
			
		case R.id.btn_createCharts:
			i = new Intent(getBaseContext(), CreatechartActivity.class);
			startActivity(i);
			break;
		}
	}
}
