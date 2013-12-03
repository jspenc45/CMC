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
	String sortBy;// subType;
	List<Group> groups;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reviewdata);
		
		typespinner = (Spinner) findViewById(R.id.rd_typespinner);
		subtypespinner = (Spinner) findViewById(R.id.rd_subtypespinner);
		reviewList = (ListView)findViewById(R.id.rdata_list);
		reviewList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent i = new Intent(getBaseContext(), Review_DetailsActivity.class);
				
				i.putExtra("DATA", (Meteorologist)madapter.getList().get(position));
				startActivity(i);
				
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
				getData(sortBy, subval);
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});		
		
	}
	
	
	private  void getData(String sortby, String val){			
		
		int sortType = (sortby.equals("GROUP"))?0:1; //set 0 for sortby Group else 1 for by role
		
		switch(sortType){
		
			case 0: //sort  by Group
				
				
				break;
			
			case 1: 
				if(val.equals("METEOROLOGIST")){				
					List<Meteorologist> list = DatabaseManager.getCollectedDataByRole(DatabaseManager.METEOROLOGIST_TABLE);
					list.add(0, new Meteorologist("Canopy_Cover", "Celsius", "fahrenheit", "Humidity", "Cloud"));
					
					madapter = new MeteoAdapter(getBaseContext(),list);
					reviewList.setAdapter(madapter);
				}
				if(val.equals("SOIL SCIENTIST")){
					List<SoilScientist> list = DatabaseManager.getCollectedDataByRole(DatabaseManager.SOIL_SCIENTIST_TABLE);
					
					ssadapter = new SScientistAdapter(getBaseContext(), list);
				}
				if(val.equals("NATURALIST")){
					List<Naturalist> list = DatabaseManager.getCollectedDataByRole(DatabaseManager.NATURALIST_TABLE);
					
				}
				
				break;
		}		
	}

}
