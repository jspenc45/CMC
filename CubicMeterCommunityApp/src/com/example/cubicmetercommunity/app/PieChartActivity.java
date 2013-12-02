package com.example.cubicmetercommunity.app;


import com.example.cubicmetercommunity.chart.PieChartView;
import com.example.cubicmetercommunity.classes.ChartInfo;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Window;

public class PieChartActivity extends Activity {

	ChartInfo data;
	@SuppressLint("UseValueOf")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey("VAL")) {				
					try {
						
						data = (ChartInfo) getIntent().getExtras().getSerializable(
								"VAL");		
						PieChartView view = new PieChartView(this, data.getTitle(), data.getData());
				        requestWindowFeature(Window.FEATURE_NO_TITLE);
				        setContentView(view);
					} 
					catch (NullPointerException e) {									
					
					}		
			}
		}
		
		
	}
}
