package com.example.cubicmetercommunity.app;

import java.util.Arrays;
import java.util.Iterator;

import com.example.cubicmetercommunityapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

//This fragment allows a user to select a role

public class BiodiversityFragment extends Fragment implements OnClickListener {

	String[] bugs = { "Snails", "Bristletails", "Lacewings", "Mayflies",
			"Thrips", "Pill Bugs", "Adult Beatles", "Spiders",
			"Butterflies/Moths", "Grasshoppers", "Earthworms", "Springtails",
			"Beetle Larva", "Daddy-Long-legs", "Woodroaches", "Caddislifes",
			"Pseudoscorpiones", "Adult Flies & Gnats", "Mites & Ticks",
			"Earwigs", "Stoneflies", "Caterpillars", "Centipedes",
			"Booklice/Barklice", "Millipedes", "Fly&Gnat Larvae", "True Bugs",
			"Bees/Ants/Wasps" };

	// "Leafhoppers/Aphids/Cicadas"
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		TableLayout table = (TableLayout) getActivity().findViewById(
				R.id.TableLayout1);

		Iterator<String> b = Arrays.asList(bugs).iterator();

		while (b.hasNext()) {
			TableRow tr = new TableRow(getActivity());
			for (int i = 0; i < 3; i++) {
				View v = LayoutInflater.from(getActivity()).inflate(
						R.layout.bugs_layout, tr, false);
				((TextView) v.findViewById(R.id.title)).setText(b.next());
				final TextView num = (TextView) v.findViewById(R.id.textView1);
				final Button minus = (Button) v.findViewById(R.id.button1);
				minus.setEnabled(false);
				minus.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View arg) {
								int count = Integer.parseInt(num.getText()
										.toString());
								
								num.setText(Integer.toString(--count));
								if (count==0)
									minus.setEnabled(false);
							}
						});
				((Button) v.findViewById(R.id.button2))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View arg) {
								int count = Integer.parseInt(num.getText()
										.toString());
								num.setText(Integer.toString(++count));
								minus.setEnabled(true);
							}
						});
				tr.addView(v);
				if (!b.hasNext()) {
					break;
				}
			}

			table.addView(tr);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.biodiversity_activity_2, container,
				false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:

		}

	}
}
