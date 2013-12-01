package com.example.cubicmetercommunity.app;


import java.util.ArrayList;
import java.util.List;

import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.classes.ReviewData;
import com.example.cubicmetercommunity.classes.ReviewDataAdapter;
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
	ReviewDataAdapter rdAdapter;	
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
				i.putExtra("DATA", rdAdapter.getList().get(position));
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
					String[] roles = new String[]{"Meterologis","Soil Scientist", "Naturalist"}; 	
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
				rdAdapter = new ReviewDataAdapter(getBaseContext() ,getReviewDataBy(sortBy, subval));
				reviewList.setAdapter(rdAdapter);
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});		
		
	}
	
	private ArrayList<ReviewData> getReviewDataBy(String sortby, String val){
		
		ArrayList<ReviewData> _result = new ArrayList<ReviewData>();
		int sortType = (sortby.equals("GROUP"))?0:1; //set 0 for sortby Group else 1 for by role
		
		switch(sortType){
		
			case 0:
				//get review data from db by group where group is val
				_result.add(new ReviewData("sorted by group1", "group_1", "session_1"));
				_result.add(new ReviewData("sorted by group2", "group_2", "session_2"));
				_result.add(new ReviewData("sorted by group3", "group_3", "session_3"));
				_result.add(new ReviewData("sorted by group4", "group_4","session_4"));
				break;
			
			case 1:
				//get review data from db by role where role is val
				_result.add(new ReviewData("sorted by role", "role_1", "session_1"));
				_result.add(new ReviewData("sorted by role", "role_2", "session_2"));
				_result.add(new ReviewData("sorted by role", "role_3", "session_3"));
				_result.add(new ReviewData("sorted by role", "role_4","session_4"));
				
				break;
		}
		
		return _result;
	}
	

}
