package com.example.cubicmetercommunity.app;

import com.example.cubicmetercommunity.classes.Meteorologist;
import com.example.cubicmetercommunity.classes.Naturalist;
import com.example.cubicmetercommunity.classes.SoilScientist;
import com.example.cubicmetercommunityapp.R;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class Review_DetailsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review__details);
		
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey("DATA")) {				
					try {
						
						Object obj = (Object) getIntent().getExtras().getSerializable(
								"DATA");		
						
						if(obj instanceof Meteorologist){
							((TextView) findViewById(R.id.rd_ID)).setText(((Meteorologist) obj).getCanopy_cover());
							((TextView) findViewById(R.id.rd_groupID)).setText(((Meteorologist) obj).getCelsius()); 
							((TextView) findViewById(R.id.rd_sessionID)).setText(((Meteorologist) obj).getCloud());
						}
						if(obj instanceof Naturalist){
							((TextView) findViewById(R.id.rd_ID)).setText(((Naturalist) obj).getCentipede());
							((TextView) findViewById(R.id.rd_groupID)).setText(((Naturalist) obj).getBee()); 
							((TextView) findViewById(R.id.rd_sessionID)).setText(((Naturalist) obj).getComments());
						}
						if(obj instanceof SoilScientist){
							((TextView) findViewById(R.id.rd_ID)).setText(((SoilScientist) obj).getSoil_ph());
							((TextView) findViewById(R.id.rd_groupID)).setText(((SoilScientist) obj).getSoil_consistency()); 
							((TextView) findViewById(R.id.rd_sessionID)).setText(((SoilScientist) obj).getSoil_texture());
						}
				
						
					} 
					catch (NullPointerException e) {									
					
					}		
			}
		}
	}



}
