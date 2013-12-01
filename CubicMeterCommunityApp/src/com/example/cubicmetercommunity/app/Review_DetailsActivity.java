package com.example.cubicmetercommunity.app;

import com.example.cubicmetercommunity.classes.ReviewData;
import com.example.cubicmetercommunityapp.R;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class Review_DetailsActivity extends Activity {
	ReviewData data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review__details);
		
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey("DATA")) {				
					try {
						
						data = (ReviewData) getIntent().getExtras().getSerializable(
								"DATA");			

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
