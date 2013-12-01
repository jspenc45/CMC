package com.example.cubicmetercommunity.app;


import java.util.ArrayList;

import com.example.cubicmetercommunity.classes.ReviewData;
import com.example.cubicmetercommunity.classes.ReviewDataAdapter;
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

	ListView reviewList;
	ReviewDataAdapter dAdapter;	
	String sortBy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reviewdata);
		
		reviewList = (ListView)findViewById(R.id.rdata_list);
		reviewList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent i = new Intent(getBaseContext(), Review_DetailsActivity.class);
				i.putExtra("DATA", dAdapter.getList().get(position));
				startActivity(i);
				
			}
		});
		
		String[] chartTypes = new String[]{"GROUP","ROLE"};
		
		Spinner rdspinner = (Spinner) findViewById(R.id.rd_spinner);
		ArrayAdapter<String> cadapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, chartTypes);
		cadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		rdspinner.setAdapter(cadapter);
		
		//sortBy = chartTypes[0];  //select the first item to be default
		
		rdspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				sortBy = parent.getItemAtPosition(pos).toString();	
				dAdapter = new ReviewDataAdapter(getBaseContext() ,getReviewDataBy(sortBy));
				reviewList.setAdapter(dAdapter);
				
			}
			public void onNothingSelected(AdapterView<?> arg0) {}
			
		});		
		
	}
	
	private ArrayList<ReviewData> getReviewDataBy(String sortby){
		
		ArrayList<ReviewData> _result = new ArrayList<ReviewData>();
		int sortType = (sortby.equals("GROUP"))?0:1; //set 0 for sortby Group else 1 for by role
		
		switch(sortType){
		
			case 0:
				//get review data from db by group
				_result.add(new ReviewData("sorted by group1", "group_1", "session_1"));
				_result.add(new ReviewData("sorted by group2", "group_2", "session_2"));
				_result.add(new ReviewData("sorted by group3", "group_3", "session_3"));
				_result.add(new ReviewData("sorted by group4", "group_4","session_4"));
				break;
			
			case 1:
				//get review data from db by role
				_result.add(new ReviewData("sorted by role", "group_1", "session_1"));
				_result.add(new ReviewData("sorted by role", "group_2", "session_2"));
				_result.add(new ReviewData("sorted by role", "group_3", "session_3"));
				_result.add(new ReviewData("sorted by role", "group_4","session_4"));
				
				break;
		}
		
		return _result;
	}
	

}
