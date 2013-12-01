package com.example.cubicmetercommunity.classes;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cubicmetercommunityapp.R;

public class ReviewDataAdapter  extends ArrayAdapter<ReviewData>{
	
		Context context;
		ArrayList<ReviewData> list;		

		public ReviewDataAdapter(Context context, ArrayList<ReviewData> list) {
			super(context, R.layout.review_data_row, list);
			this.context = context;
			this.list = list;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.review_data_row,
						parent, false);
			}		

			TextView id = (TextView) convertView.findViewById(R.id.rd_ID);  
			TextView gid = (TextView) convertView.findViewById(R.id.rd_groupID);   
			TextView sid = (TextView) convertView.findViewById(R.id.rd_sessionID);   
			
			id.setText(list.get(position).ID);
			gid.setText(list.get(position).groupID);
			sid.setText(list.get(position).sessionID);
			
			return convertView;
		}

		public ArrayList<ReviewData> getList() {
			return list;
		}

		
		
}
