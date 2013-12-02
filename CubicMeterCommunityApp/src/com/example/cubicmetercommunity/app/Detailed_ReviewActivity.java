package com.example.cubicmetercommunity.app;

import com.example.cubicmetercommunity.classes.ReviewData;
import com.example.cubicmetercommunityapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class Detailed_ReviewActivity extends Activity {

	ReviewData data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detailed__review);
		
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey("VAL")) {				
					try {
						
						data = (ReviewData) getIntent().getExtras().getSerializable(
								"VAL");			

						TextView id = (TextView) findViewById(R.id.rd_ID);  
						TextView gid = (TextView) findViewById(R.id.rd_groupID);   
						TextView sid = (TextView)findViewById(R.id.rd_sessionID);   
						
						id.setText(data.getID());
						gid.setText(data.getGroupID());
						sid.setText(data.getSessionID());	
						
					} 
					catch (NullPointerException e) {									
					
					}		
			}
		}
	}

}
