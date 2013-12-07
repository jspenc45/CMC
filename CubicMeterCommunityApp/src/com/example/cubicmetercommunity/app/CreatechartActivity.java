package com.example.cubicmetercommunity.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.cubicmetercommunity.classes.ChartInfo;
import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.classes.Meteorologist;
import com.example.cubicmetercommunity.classes.Naturalist;
import com.example.cubicmetercommunity.classes.SoilScientist;
import com.example.cubicmetercommunity.dbutil.DatabaseManager;
import com.example.cubicmetercommunityapp.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

//This activity shows data parameters for creating a new chart.

public class CreatechartActivity extends Activity implements OnClickListener{
	List<Group> groups;
	
	String selectedGroup, selectedGroupID, selectedChartType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createchart);		
			
		groups = DatabaseManager.getGroups();
		
		String[] chartTypes = new String[]{"Bar Chart","Pie Chart"};	
		
		Spinner gspinner = (Spinner) findViewById(R.id.groupSpinner);
		ArrayAdapter<Group> gadapter = new ArrayAdapter<Group>(getBaseContext(), android.R.layout.simple_spinner_item, groups);
		gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gspinner.setAdapter(gadapter);
		
		selectedGroup = groups.get(0).toString(); //select the first item as the default
		selectedGroupID = groups.get(0).getId();
		
		gspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				selectedGroup = parent.getItemAtPosition(pos).toString();
				selectedGroupID = groups.get(pos).getId();
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
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});
		
		Button b = (Button) findViewById(R.id.ccf_back);
		b.setOnClickListener(this);
		Button g = (Button) findViewById(R.id.ccf_generate);
		g.setOnClickListener(this);
	}
	
	@SuppressLint("UseValueOf")
	@Override
	public void onClick(View v) {
		int chartType = (selectedChartType.equals("Bar Chart"))?0:1; //set 0 if bar chart else 1 for pie chart
		Intent i;
		switch(v.getId()){
		case R.id.ccf_generate:			
			switch(chartType){
				case 0: //generate bar chart
					i = new Intent(getBaseContext(), BarChartActivity.class);
					i.putExtra("VAL", new ChartInfo(selectedGroup,getBarData() ));
					startActivity(i);
					break;
				case 1://generate pie chart
					i = new Intent(getBaseContext(), PieChartActivity.class);					
					
					i.putExtra("VAL", new ChartInfo(selectedGroup,getPieData() ));
					startActivity(i);
					break;
			}			
			break;
			
		case R.id.ccf_back:
			finish();
		}		
	}
	public HashMap<String, Double> getPieData(){
		List<Meteorologist> mlist = DatabaseManager.getCollectedDataByRole(DatabaseManager.NATURALIST_TABLE, selectedGroupID);
		List<Naturalist> nlist = DatabaseManager.getCollectedDataByRole(DatabaseManager.SOIL_SCIENTIST_TABLE, selectedGroupID);
		List<SoilScientist> sslist = DatabaseManager.getCollectedDataByRole(DatabaseManager.METEOROLOGIST_TABLE, selectedGroupID);
		HashMap<String, Double> data = new HashMap<String, Double>();
		Double total = (double) (mlist.size() + nlist.size() + sslist.size());
		
		Double met = (mlist.size()/total) * 100.00;
		Double nat = (nlist.size()/total) * 100.00;
		Double ss = (sslist.size()/total) * 100.00;
		
		data.put("Meteorologist", met );
        data.put("Naturalist",  nat);
        data.put("Soil Scientist",ss);
			
		return data;
	}
	
	public HashMap<String, Double> getBarData(){
		List<Meteorologist> mlist = DatabaseManager.getCollectedDataByRole(DatabaseManager.NATURALIST_TABLE, selectedGroupID);
		List<Naturalist> nlist = DatabaseManager.getCollectedDataByRole(DatabaseManager.SOIL_SCIENTIST_TABLE, selectedGroupID);
		List<SoilScientist> sslist = DatabaseManager.getCollectedDataByRole(DatabaseManager.METEOROLOGIST_TABLE, selectedGroupID);
		HashMap<String, Double> data = new HashMap<String, Double>();
		
		Double met = (double) mlist.size();
		Double nat = (double) nlist.size();
		Double ss = (double) sslist.size();
		
		data.put("Meteorologist", met );
        data.put("Naturalist",  nat);
        data.put("Soil Scientist",ss);
			
		return data;
	}

}
