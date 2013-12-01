package com.example.cubicmetercommunity.app;


import java.util.ArrayList;

import com.example.cubicmetercommunity.classes.ReviewData;
import com.example.cubicmetercommunity.classes.ReviewDataAdapter;
import com.example.cubicmetercommunityapp.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

public class ReviewdataActivity extends Activity {

	ListView reviewList;
	ReviewDataAdapter dAdapter;	
	ArrayList<ReviewData> result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reviewdata);
		
		reviewList = (ListView)findViewById(R.id.rdata_list);
		
		result  = new ArrayList<ReviewData>();
		result.add(new ReviewData("id_1", "group_1", "session_1"));
		result.add(new ReviewData("id_2", "group_2", "session_2"));
		result.add(new ReviewData("id_3", "group_3", "session_3"));
		result.add(new ReviewData("id_4", "group_4", "session_4"));
		
		dAdapter = new ReviewDataAdapter(this,result);
		
		reviewList.setAdapter(dAdapter);
		reviewList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent i = new Intent(getBaseContext(), Review_DetailsActivity.class);
				i.putExtra("DATA", dAdapter.getList().get(position));
				startActivity(i);
				
			}
		});
	}
	

}
