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

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

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
	View mheader, nheader, ssheader, addedView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_2);
		groups = DatabaseManager.getGroups();

		typespinner = (Spinner) findViewById(R.id.rd_typespinner);
		subtypespinner = (Spinner) findViewById(R.id.rd_subtypespinner);
		xView = (ExpandableListView) findViewById(R.id.expandableview);

		roles = new ArrayList<String>();
		roles.add(Role.METEOROLOGIST);
		roles.add(Role.NATURALIST);
		roles.add(Role.SOIL_SCIENTIST);

		// setup SortType selector (spinner)
		String[] sTypes = new String[] { "GROUP", "ROLE" };

		ArrayAdapter<String> sadapter = new ArrayAdapter<String>(
				getBaseContext(), android.R.layout.simple_spinner_item, sTypes);
		sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typespinner.setAdapter(sadapter);
		typespinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				sortBy = parent.getItemAtPosition(pos).toString();
				int intsortBy = (sortBy.equals("GROUP")) ? 0 : 1;

				switch (intsortBy) {
				case 0:
					// if sorting by group, populate groups
					gadapter = new ArrayAdapter<Group>(getBaseContext(),
							android.R.layout.simple_spinner_item, groups);
					gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					subtypespinner.setAdapter(gadapter);
					break;

				case 1:
					// if sorting by role populate roles
					ArrayAdapter<String> radapter = new ArrayAdapter<String>(
							getBaseContext(),
							android.R.layout.simple_spinner_item, roles);
					radapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					subtypespinner.setAdapter(radapter);
					break;
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		subtypespinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				String subval = parent.getItemAtPosition(pos).toString();

				int sortType = (sortBy.equals("GROUP")) ? 0 : 1; // set 0 for
																	// sortby
																	// Group
																	// else 1
																	// for by
																	// role

				switch (sortType) {

				case 0: // populated data based on the selected group

					xAdapter = new ExpandableViewAdapter(getBaseContext(),
							roles, gadapter.getItem(pos).getId());
					xView.setAdapter(xAdapter);
					break;

				case 1: // populated data based on the selected role

					List<String> _roles = new ArrayList<String>();
					_roles.add(subval);
					xAdapter = new ExpandableViewAdapter(getBaseContext(),
							_roles, null);
					xView.setAdapter(xAdapter);

					break;
				}

			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		xView.setOnChildClickListener(new OnChildClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				Intent i = new Intent(getBaseContext(),
						Review_DetailsActivity.class);
				String role = (String) xAdapter.getGroup(groupPosition);

				if (role.equals(Role.METEOROLOGIST)) {
					i.putExtra("DATA", ((List<Meteorologist>) xAdapter
							.getChild(groupPosition, childPosition))
							.get(childPosition));
				}
				if (role.equals(Role.NATURALIST)) {
					i.putExtra("DATA", ((List<Naturalist>) xAdapter.getChild(
							groupPosition, childPosition)).get(childPosition));
				}
				if (role.equals(Role.SOIL_SCIENTIST)) {
					i.putExtra("DATA", ((List<SoilScientist>) xAdapter
							.getChild(groupPosition, childPosition))
							.get(childPosition));
				}
				startActivity(i);

				return false;
			}
		});
	}

}
