package com.example.cubicmetercommunity.app;

import java.util.ArrayList;
import java.util.List;

import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.classes.Meteorologist;
import com.example.cubicmetercommunity.classes.Naturalist;
import com.example.cubicmetercommunity.classes.Role;
import com.example.cubicmetercommunity.classes.SoilScientist;
import com.example.cubicmetercommunity.classes.CmcAdapters.ExpandableViewAdapter;
import com.example.cubicmetercommunity.classes.CmcAdapters.MeteoAdapter;
import com.example.cubicmetercommunity.classes.CmcAdapters.NaturalistAdapter;
import com.example.cubicmetercommunity.classes.CmcAdapters.SScientistAdapter;
import com.example.cubicmetercommunity.dbutil.DatabaseManager;
import com.example.cubicmetercommunityapp.R;
import com.example.cubicmetercommunityapp.R.layout;
import com.example.cubicmetercommunityapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class Review_2Activity extends Activity {

	Spinner typespinner, subtypespinner;
	ExpandableListView xView;
	ListView reviewList;
	ExpandableViewAdapter xAdapter;
	MeteoAdapter madapter;
	NaturalistAdapter nadapter;
	SScientistAdapter ssadapter;
	ArrayAdapter<Group> gadapter;
	String sortBy;
	List<Group> groups;
	View mheader, nheader,ssheader, addedView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_2);
		
		
		typespinner = (Spinner) findViewById(R.id.rd_typespinner);
		subtypespinner = (Spinner) findViewById(R.id.rd_subtypespinner);
		
		List<String> roles = new ArrayList<String>();
		roles.add(Role.METEOROLOGIST);
		roles.add(Role.NATURALIST);
		roles.add(Role.NATURALIST);
		
		final ExpandableViewAdapter xAdapter = new ExpandableViewAdapter(getBaseContext(),
	        roles);
		xView = (ExpandableListView) findViewById(R.id.expandableview);
		xView.setAdapter(xAdapter);
//		xView.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(getBaseContext(), "position: !!!",
//			            Toast.LENGTH_SHORT).show();
//				
//			}
//		});
		xView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(getBaseContext(), "position: " + childPosition,
	            Toast.LENGTH_SHORT).show();
				return false;
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
					//populate groups
					groups = DatabaseManager.getGroups();
					
					gadapter = new ArrayAdapter<Group>(getBaseContext(), android.R.layout.simple_spinner_item, groups);
					gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					subtypespinner.setAdapter(gadapter);
					
					break;
					
				case 1:
					//populate roles
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
				
				int sortType = (sortBy.equals("GROUP"))?0:1; //set 0 for sortby Group else 1 for by role
				
				switch(sortType){
				
				case 0: //sort  by Group					
					
					//String gid = gadapter.getItem(pos).getId(); //get selected groupID
					// xView.setAdapter(xAdapter);
					break;
				
				case 1:  // sort by role
					
					// xView.setAdapter(xAdapter);
					
					break;
			}
				
				
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});			

	}
}
