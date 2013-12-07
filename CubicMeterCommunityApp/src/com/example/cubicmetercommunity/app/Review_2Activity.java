package com.example.cubicmetercommunity.app;

import java.util.ArrayList;
import java.util.List;

import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.classes.Meteorologist;
import com.example.cubicmetercommunity.classes.Naturalist;
import com.example.cubicmetercommunity.classes.Role;
import com.example.cubicmetercommunity.classes.CmcAdapters.ExpandableViewAdapter;
import com.example.cubicmetercommunity.classes.CmcAdapters.MeteoAdapter;
import com.example.cubicmetercommunity.classes.CmcAdapters.NaturalistAdapter;
import com.example.cubicmetercommunity.classes.CmcAdapters.SScientistAdapter;
import com.example.cubicmetercommunity.classes.SoilScientist;
import com.example.cubicmetercommunity.dbutil.DatabaseManager;
import com.example.cubicmetercommunityapp.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class Review_2Activity extends Activity {

	ProgressDialog pDialog;
	Spinner typespinner;
	Spinner subtypespinner;
	ExpandableListView xView;
	ListView reviewList;
	ExpandableViewAdapter xAdapter;
	MeteoAdapter madapter;
	NaturalistAdapter nadapter;
	SScientistAdapter ssadapter;
	ArrayAdapter<Group> gadapter;
	List<String> roles;
	String sortBy;
	List<Group> groups;
	View mheader, nheader,ssheader, addedView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_2);
		
		pDialog = new ProgressDialog(this);
		pDialog.setCancelable(false);
		pDialog.setMessage("Loading ...");
		
		typespinner = (Spinner) findViewById(R.id.rd_typespinner);
		subtypespinner = (Spinner) findViewById(R.id.rd_subtypespinner);
		
		roles = new ArrayList<String>();
		roles.add(Role.METEOROLOGIST);
		roles.add(Role.NATURALIST);
		roles.add(Role.SOIL_SCIENTIST);
		
		//xAdapter = new ExpandableViewAdapter(getBaseContext(), roles);
		
		xView = (ExpandableListView) findViewById(R.id.expandableview);
		//xView.setAdapter(xAdapter);
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
			
			@SuppressWarnings("unchecked")
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
//				Toast.makeText(getBaseContext(), "position: " + childPosition,
//	            Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getBaseContext(), Review_DetailsActivity.class);	
				String role = (String) xAdapter.getGroup(groupPosition);
				
				if(role.equals(Role.METEOROLOGIST)){
					Meteorologist mt = ((List<Meteorologist>)xAdapter.getChild(groupPosition, childPosition)).get(childPosition);
					i.putExtra("DATA", mt);
				}
				if(role.equals(Role.NATURALIST)){				
					Naturalist nt =	((List<Naturalist>)xAdapter.getChild(groupPosition, childPosition)).get(childPosition);
					i.putExtra("DATA", nt);
				}
				if(role.equals(Role.SOIL_SCIENTIST)){
				SoilScientist ss =	((List<SoilScientist>)xAdapter.getChild(groupPosition, childPosition)).get(childPosition);
				i.putExtra("DATA", ss);
				}
				startActivity(i);
				
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
					groups = DatabaseManager.getGroups();					
					gadapter = new ArrayAdapter<Group>(getBaseContext(), android.R.layout.simple_spinner_item, groups);
					gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					subtypespinner.setAdapter(gadapter);
					break;
					
				case 1:
					//populate roles
					//String[] roles = new String[]{"METEOROLOGIST","SOIL SCIENTIST", "NATURALIST"}; 	
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
					
					xAdapter = new ExpandableViewAdapter(getBaseContext(),
					        roles, gadapter.getItem(pos).getId());
					xView.setAdapter(xAdapter);
					break;
				
				case 1:  // sort by role
					
					List<String> _roles = new ArrayList<String>();
					_roles.add(subval);
					xAdapter = new ExpandableViewAdapter(getBaseContext(),
					        _roles, null);
					xView.setAdapter(xAdapter);
					
					break;
			}
				
				
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});			

	}
	
	private class AsyncGetGroups extends AsyncTask<String, Void, List<Group>> {

		Context context;
		AsyncGetGroups(Context context){
			this.context = context;
			
		}
		
		@Override
		protected List<Group> doInBackground(String... params) {	
			Log.d("dd", "before group");
			return DatabaseManager.getGroups();
		}

		@Override
		protected void onPostExecute(List<Group> result) {
			if(result != null){
				Log.d("dd", "in result");
				ArrayAdapter<Group> gadapter = new ArrayAdapter<Group>(context, android.R.layout.simple_spinner_item, result);
				gadapter.setNotifyOnChange(true);
				gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				subtypespinner.setAdapter(gadapter);
				pDialog.dismiss();
			}else Log.d("dd", "result is null");
		}
		
	}
}

