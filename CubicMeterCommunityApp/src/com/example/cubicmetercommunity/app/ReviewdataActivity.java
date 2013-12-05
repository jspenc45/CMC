package com.example.cubicmetercommunity.app;


import java.util.List;

import com.example.cubicmetercommunity.classes.CmcAdapters;
import com.example.cubicmetercommunity.classes.CmcAdapters.MeteoAdapter;
import com.example.cubicmetercommunity.classes.CmcAdapters.NaturalistAdapter;
import com.example.cubicmetercommunity.classes.CmcAdapters.SScientistAdapter;
import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.classes.Meteorologist;
import com.example.cubicmetercommunity.classes.Naturalist;
import com.example.cubicmetercommunity.classes.SoilScientist;
import com.example.cubicmetercommunity.dbutil.DatabaseManager;
import com.example.cubicmetercommunityapp.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

public class ReviewdataActivity extends Activity {
	Spinner typespinner, subtypespinner;
	ListView reviewList;
	MeteoAdapter madapter;
	NaturalistAdapter nadapter;
	SScientistAdapter ssadapter;
	String sortBy;
	List<Group> groups;
	View mheader, nheader,ssheader, addedView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reviewdata);
		
		typespinner = (Spinner) findViewById(R.id.rd_typespinner);
		subtypespinner = (Spinner) findViewById(R.id.rd_subtypespinner);
		reviewList = (ListView)findViewById(R.id.rdata_list);
		final View mheader = getLayoutInflater().inflate(R.layout.header_meteorologist, null);
		final View nheader = getLayoutInflater().inflate(R.layout.header_naturalist, null);
		final View ssheader = getLayoutInflater().inflate(R.layout.header_soilscientist, null);
		//reviewList.addHeaderView(mheader);	
		reviewList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
//				Intent i = new Intent(getBaseContext(), Review_DetailsActivity.class);				
//				i.putExtra("DATA", (Meteorologist)madapter.getList().get(position));
//				startActivity(i);
				
			}
		});
		
		String[] sTypes = new String[]{"GROUP","ROLE"};		
		
		ArrayAdapter<String> sadapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, sTypes);
		sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typespinner.setAdapter(sadapter);
		typespinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				sortBy = parent.getItemAtPosition(pos).toString();	
				int intsortBy = (sortBy.equals("GROUP"))?0:1;
				
				switch(intsortBy){
				case 0:
					
					groups = DatabaseManager.getGroups();
					
					ArrayAdapter<Group> gadapter = new ArrayAdapter<Group>(getBaseContext(), android.R.layout.simple_spinner_item, groups);
					gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					subtypespinner.setAdapter(gadapter);
					
					break;
					
				case 1:
					String[] roles = new String[]{"METEOROLOGIST","SOIL SCIENTIST", "NATURALIST"}; 	
					ArrayAdapter<String> radapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, roles);
					radapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					subtypespinner.setAdapter(radapter);
					break;
				
				}			
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});	
		
		subtypespinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				String subval = parent.getItemAtPosition(pos).toString();	
				//getData(subval);
				
				int sortType = (sortBy.equals("GROUP"))?0:1; //set 0 for sortby Group else 1 for by role
				
				switch(sortType){
				
				case 0: //sort  by Group
					
					
					break;
				
				case 1:  // sort by role
					if(subval.equals("METEOROLOGIST")){				
						List<Meteorologist> list = DatabaseManager.getCollectedDataByRole(DatabaseManager.METEOROLOGIST_TABLE);
						
						madapter = new MeteoAdapter(getBaseContext(),list);
						
						if(reviewList.getHeaderViewsCount() == 0){
							reviewList.addHeaderView(mheader);	
							addedView = mheader;
							reviewList.setHeaderDividersEnabled(true);
						}
						else{
							reviewList.removeHeaderView(addedView);
							reviewList.addHeaderView(mheader);	
							addedView = mheader;
							reviewList.setHeaderDividersEnabled(true);
						}
						
						reviewList.setAdapter(madapter);
						
					}
					if(subval.equals("NATURALIST")){
						List<SoilScientist> list = DatabaseManager.getCollectedDataByRole(DatabaseManager.SOIL_SCIENTIST_TABLE);
						
						ssadapter = new SScientistAdapter(getBaseContext(), list);
						
						if(reviewList.getHeaderViewsCount() == 0){
							reviewList.addHeaderView(nheader);	
							addedView = nheader;
							reviewList.setHeaderDividersEnabled(true);
						}
						else{
							reviewList.removeHeaderView(addedView);
							reviewList.addHeaderView(nheader);	
							addedView = nheader;
							reviewList.setHeaderDividersEnabled(true);
						}
						
						reviewList.setAdapter(ssadapter);
					}
					if(subval.equals("SOIL SCIENTIST")){
						List<Naturalist> list = DatabaseManager.getCollectedDataByRole(DatabaseManager.NATURALIST_TABLE);
						nadapter = new NaturalistAdapter(getBaseContext(), list);
						
						if(reviewList.getHeaderViewsCount() == 0){
							reviewList.addHeaderView(ssheader);	
							addedView = ssheader;
							reviewList.setHeaderDividersEnabled(true);
						}
						else{
							reviewList.removeHeaderView(addedView);
							reviewList.addHeaderView(ssheader);	
							addedView = ssheader;
							reviewList.setHeaderDividersEnabled(true);
						}
						
						reviewList.setAdapter(nadapter);
					}
					
					break;
			}
				
				
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});			
	}
	
	
	private  void getData(String subval){			
		
		int sortType = (sortBy.equals("GROUP"))?0:1; //set 0 for sortby Group else 1 for by role
		
		switch(sortType){
		
		case 0: //sort  by Group
			
			
			break;
		
		case 1:  // sort by role
			if(subval.equals("METEOROLOGIST")){				
				List<Meteorologist> list = DatabaseManager.getCollectedDataByRole(DatabaseManager.METEOROLOGIST_TABLE);
				
				madapter = new MeteoAdapter(getBaseContext(),list);
				
				if(reviewList.getHeaderViewsCount() == 0){
					reviewList.addHeaderView(mheader);	
					addedView = mheader;
					reviewList.setHeaderDividersEnabled(true);
				}
				else{
					reviewList.removeHeaderView(addedView);
					reviewList.addHeaderView(mheader);	
					addedView = mheader;
					reviewList.setHeaderDividersEnabled(true);
				}
				
				reviewList.setAdapter(madapter);
				
			}
			if(subval.equals("NATURALIST")){
				List<SoilScientist> list = DatabaseManager.getCollectedDataByRole(DatabaseManager.SOIL_SCIENTIST_TABLE);
				
				ssadapter = new SScientistAdapter(getBaseContext(), list);
				
				if(reviewList.getHeaderViewsCount() == 0){
					reviewList.addHeaderView(nheader);	
					addedView = nheader;
					reviewList.setHeaderDividersEnabled(true);
				}
				else{
					reviewList.removeHeaderView(addedView);
					reviewList.addHeaderView(nheader);	
					addedView = nheader;
					reviewList.setHeaderDividersEnabled(true);
				}
				
				reviewList.setAdapter(ssadapter);
			}
			if(subval.equals("SOIL SCIENTIST")){
				List<Naturalist> list = DatabaseManager.getCollectedDataByRole(DatabaseManager.NATURALIST_TABLE);
				nadapter = new NaturalistAdapter(getBaseContext(), list);
				
				if(reviewList.getHeaderViewsCount() == 0){
					reviewList.addHeaderView(ssheader);	
					addedView = ssheader;
					reviewList.setHeaderDividersEnabled(true);
				}
				else{
					reviewList.removeHeaderView(addedView);
					reviewList.addHeaderView(ssheader);	
					addedView = ssheader;
					reviewList.setHeaderDividersEnabled(true);
				}
				
				reviewList.setAdapter(nadapter);
			}
			
			break;
	}	
	}

}
