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
						}
						if(obj instanceof Naturalist){
							nlist = new ArrayList<Naturalist>();
							nlist.add((Naturalist) obj);
							nAdapter = new NaturalistAdapter(getBaseContext(), nlist);
							listView.setAdapter(nAdapter);
						}
						if(obj instanceof SoilScientist){
							slist = new ArrayList<SoilScientist>();
							slist.add((SoilScientist) obj);
							sAdapter = new SScientistAdapter(getBaseContext(), slist);
							listView.setAdapter(sAdapter);
						}			
						
					} 
					catch (NullPointerException e) {									
					
					}		
			}
		}
	}



}
