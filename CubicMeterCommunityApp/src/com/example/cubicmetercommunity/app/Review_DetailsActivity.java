package com.example.cubicmetercommunity.app;

import java.util.ArrayList;
import java.util.List;

import com.example.cubicmetercommunity.classes.CmcAdapters.MeteoAdapter;
import com.example.cubicmetercommunity.classes.CmcAdapters.NaturalistAdapter;
import com.example.cubicmetercommunity.classes.CmcAdapters.SScientistAdapter;
import com.example.cubicmetercommunity.classes.Meteorologist;
import com.example.cubicmetercommunity.classes.Naturalist;
import com.example.cubicmetercommunity.classes.SoilScientist;
import com.example.cubicmetercommunityapp.R;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;

public class Review_DetailsActivity extends Activity {
	ListView listView;
	MeteoAdapter mAdapter;
	NaturalistAdapter nAdapter;
	SScientistAdapter sAdapter;
	List<Meteorologist> mlist;
	List<Naturalist> nlist;
	List<SoilScientist> slist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review__details);
		
		listView = (ListView)findViewById(R.id.details_listview);
		
		
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey("DATA")) {				
					try {
						
						Object obj = (Object) getIntent().getExtras().getSerializable(
								"DATA");		
						
						if(obj instanceof Meteorologist){
							mlist = new ArrayList<Meteorologist>();
							mlist.add((Meteorologist) obj);
							mAdapter = new MeteoAdapter(getBaseContext(), mlist);
							listView.setAdapter(mAdapter);
//							((TextView) findViewById(R.id.rd_ID)).setText(((Meteorologist) obj).getCanopy_cover());
//							((TextView) findViewById(R.id.rd_groupID)).setText(((Meteorologist) obj).getCelsius()); 
//							((TextView) findViewById(R.id.rd_sessionID)).setText(((Meteorologist) obj).getCloud());
						}
						if(obj instanceof Naturalist){
							nlist = new ArrayList<Naturalist>();
							nlist.add((Naturalist) obj);
							nAdapter = new NaturalistAdapter(getBaseContext(), nlist);
							listView.setAdapter(nAdapter);
//							((TextView) findViewById(R.id.rd_ID)).setText(((Naturalist) obj).getCentipede());
//							((TextView) findViewById(R.id.rd_groupID)).setText(((Naturalist) obj).getBee()); 
//							((TextView) findViewById(R.id.rd_sessionID)).setText(((Naturalist) obj).getComments());
						}
						if(obj instanceof SoilScientist){
							slist = new ArrayList<SoilScientist>();
							slist.add((SoilScientist) obj);
							sAdapter = new SScientistAdapter(getBaseContext(), slist);
							listView.setAdapter(sAdapter);
//							((TextView) findViewById(R.id.rd_ID)).setText(((SoilScientist) obj).getSoil_ph());
//							((TextView) findViewById(R.id.rd_groupID)).setText(((SoilScientist) obj).getSoil_consistency()); 
//							((TextView) findViewById(R.id.rd_sessionID)).setText(((SoilScientist) obj).getSoil_texture());
						}
				
						
					} 
					catch (NullPointerException e) {									
					
					}		
			}
		}
	}



}
