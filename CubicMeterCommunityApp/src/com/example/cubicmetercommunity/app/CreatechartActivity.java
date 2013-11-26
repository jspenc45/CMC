package com.example.cubicmetercommunity.app;

import com.example.cubicmetercommunityapp.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class CreatechartActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createchart);
		Button b = (Button) findViewById(R.id.ccf_generate);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ccf_generate:
			Intent i = new Intent(getBaseContext(), BarChartActivity.class);
			startActivity(i);
			break;
		}
		
	}

}
