package com.example.cubicmetercommunity.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.dbutil.DBUtil;
import com.example.cubicmetercommunityapp.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.Activity;
import android.content.Intent;

public class CreatechartActivity extends Activity implements OnClickListener{
	List<Group> groups;
	DBUtil db;
	String selectedGroup, selectedChartType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createchart);		
		
		db = new DBUtil();		
		groups = getGroups();
		
		String[] chartTypes = new String[]{"Bar Chart","Pie Chart"};	
		
		Spinner gspinner = (Spinner) findViewById(R.id.groupSpinner);
		ArrayAdapter<Group> gadapter = new ArrayAdapter<Group>(getBaseContext(), android.R.layout.simple_spinner_item, groups);
		gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gspinner.setAdapter(gadapter);
		
		selectedGroup = groups.get(0).toString(); //select the first item to be default
		
		gspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				selectedGroup = parent.getItemAtPosition(pos).toString();
				//do something with group
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {
			}
			
		});
		
		Spinner cspinner = (Spinner) findViewById(R.id.chartSpinner);
		ArrayAdapter<String> cadapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, chartTypes);
		cadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cspinner.setAdapter(cadapter);
		
		selectedChartType = chartTypes[0];  //select the first item to be default
		
		cspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				selectedChartType = parent.getItemAtPosition(pos).toString();
				//do something with chartType
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});
		
		Button b = (Button) findViewById(R.id.ccf_generate);
		b.setOnClickListener(this);
	}

	private List<Group> getGroups() {
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put(Group.sqlNAME, null);
		fields.put(Group.sqlID, null);
		JSONObject response = db.select("Group__c", fields, null);

		return Group.getGroups(response);
	}
	
	@Override
	public void onClick(View v) {
		int chartType = (selectedChartType.equals("Bar Chart"))?0:1; //set 0 if bar chart else 1 for pie chart
		Intent i;
		switch(v.getId()){
		case R.id.ccf_generate:
			
			switch(chartType){
			case 0: //generate bar chart
				i = new Intent(getBaseContext(), BarChartActivity.class);
				startActivity(i);
				break;
			case 1://generate pie chart
				
				break;
			}
			
			break;
		}
		
	}

}
