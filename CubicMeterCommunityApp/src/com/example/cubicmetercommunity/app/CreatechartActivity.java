package com.example.cubicmetercommunity.app;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

import com.example.cubicmetercommunity.classes.ChartInfo;
import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.classes.Meteorologist;
import com.example.cubicmetercommunity.classes.Naturalist;
import com.example.cubicmetercommunity.classes.Session;
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
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

//This activity shows data parameters for creating a new chart.

public class CreatechartActivity extends Activity implements OnClickListener {
	List<Group> groups;
	List<Session> sessions;

	String selectedGroup, selectedGroupID, selectedChartType, selectedDataType,
			selectedSession;
	String[] dataTypes;
	String[] dataTypeB = { "Biodiversity" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createchart);

		groups = DatabaseManager.getGroups();
		sessions = DatabaseManager.getAllSessions();
		sessions.add(new Session("All", "null", "All Sessions"));

		String[] chartTypes = new String[] { "Bar Chart", "Pie Chart" };

		Spinner gspinner = (Spinner) findViewById(R.id.groupSpinner);
		ArrayAdapter<Group> gadapter = new ArrayAdapter<Group>(
				getBaseContext(), android.R.layout.simple_spinner_item, groups);
		gadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gspinner.setAdapter(gadapter);

		selectedGroup = groups.get(0).toString(); // select the first item as
													// the default
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
		ArrayAdapter<String> cadapter = new ArrayAdapter<String>(
				getBaseContext(), android.R.layout.simple_spinner_item,
				chartTypes);
		cadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cspinner.setAdapter(cadapter);

		selectedChartType = chartTypes[0]; // select the first item to be
											// default

		cspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				selectedChartType = parent.getItemAtPosition(pos).toString();
				TextView t = (TextView) findViewById(R.id.TextView02);
				Spinner sessions = (Spinner) findViewById(R.id.sessions);
				Spinner dataTypeSpinner = (Spinner) findViewById(R.id.dataTypes);
				if (selectedChartType.equals("Pie Chart")) {
					t.setVisibility(View.VISIBLE);
					sessions.setVisibility(View.VISIBLE);
					dataTypeSpinner.setAdapter(new ArrayAdapter<String>(
							getBaseContext(),
							android.R.layout.simple_spinner_item, dataTypeB));
				} else {
					t.setVisibility(View.GONE);
					sessions.setVisibility(View.GONE);
					dataTypeSpinner.setAdapter(new ArrayAdapter<String>(
							getBaseContext(),
							android.R.layout.simple_spinner_item, dataTypes));
				}

			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		Spinner sessionSpinner = (Spinner) findViewById(R.id.sessions);
		ArrayAdapter<Session> sAdapter = new ArrayAdapter<Session>(
				getBaseContext(), android.R.layout.simple_spinner_item,
				sessions);
		sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sessionSpinner.setAdapter(sAdapter);

		selectedSession = sessions.get(0).getId(); // select the first item as
													// the default

		sessionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				selectedSession = sessions.get(pos).getId();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		dataTypes = getResources().getStringArray(R.array.dataTypes);
		Spinner dTypes = (Spinner) findViewById(R.id.dataTypes);
		ArrayAdapter<String> dTypeAdapter = new ArrayAdapter<String>(
				getBaseContext(), android.R.layout.simple_spinner_item,
				dataTypes);
		dTypeAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dTypes.setAdapter(dTypeAdapter);

		selectedDataType = dataTypes[0]; // select the first item to be default

		dTypes.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				selectedDataType = parent.getItemAtPosition(pos).toString();

			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		Button b = (Button) findViewById(R.id.ccf_back);
		b.setOnClickListener(this);
		Button g = (Button) findViewById(R.id.ccf_generate);
		g.setOnClickListener(this);
	}

	@SuppressLint("UseValueOf")
	@Override
	public void onClick(View v) {
		int chartType = (selectedChartType.equals("Bar Chart")) ? 0 : 1;
		// set 0 if bar chart, else 1 for pie chart
		Intent i;

		switch (v.getId()) {
		case R.id.ccf_generate:
			SortedMap<String, Double> data;
			switch (chartType) {
			case 0: // generate bar chart
				i = new Intent(getBaseContext(), BarChartActivity.class);
				if (selectedDataType.equals("Soil pH Levels")
						|| selectedDataType.equals("Soil Moisture"))
					data = generateSSBarData();
				else
					data = generateBarData();
				if (data == null || data.size() == 0)
					Toast.makeText(this, "No data for selected item!",
							Toast.LENGTH_LONG).show();
				else {
					i.putExtra("VAL", new ChartInfo(selectedGroup + " "
							+ selectedDataType, data));
					startActivity(i);
				}
				break;
			case 1:// generate pie chart
				i = new Intent(getBaseContext(), PieChartActivity.class);
				data = getPieData();
				Log.d("debug", data.size() + "");

				if (data == null || data.size() == 0)
					Toast.makeText(this, "No data for selected item!",
							Toast.LENGTH_LONG).show();
				else {
					i.putExtra("VAL", new ChartInfo("Biodiversity", data));
					startActivity(i);
				}
				break;
			}
			break;

		case R.id.ccf_back:
			finish();
		}
	}

	public int getIndex(String val) {
		int x = 0;
		for (String str : dataTypes) {
			if (str.equals(dataTypes[x]))
				return x;
			x++;
		}
		return -1;
	}

	public SortedMap<String, Double> generateBarData() {
		List<Meteorologist> mlist = DatabaseManager.getCollectedDataByRole(
				DatabaseManager.METEOROLOGIST_TABLE, selectedGroupID);
		if (mlist.size() == 0)
			return null;
		HashMap<String, String> sessionTimes = new HashMap<String, String>();
		for (Session session : sessions) {
			sessionTimes.put(session.getId(), session.getSessionTime());
		}
		SortedMap<String, Double> data = new TreeMap<String, Double>();

		for (Meteorologist m : mlist) {
			Date d = null;
			String date = sessionTimes.get(m.getSession_id());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			try {
				d = simpleDateFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();

			}
			m.setDate(d);
		}
		Collections.sort(mlist);
		int end = mlist.size() - 11;
		for (int i = mlist.size() - 1; i > end; i--) {
			Meteorologist m = mlist.get(i);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"MM/dd HH:mm", Locale.US);
			String d = simpleDateFormat.format(m.getDate());
			try {
				data.put(d, Double.parseDouble(getData(m, selectedDataType)));
			} catch (NumberFormatException e) {
				if (end >= 0)
					end--;
			}
		}

		return data;
	}

	public SortedMap<String, Double> generateSSBarData() {
		List<SoilScientist> sslist = DatabaseManager.getCollectedDataByRole(
				DatabaseManager.SOIL_SCIENTIST_TABLE, selectedGroupID);
		if (sslist.size() == 0)
			return null;
		HashMap<String, String> sessionTimes = new HashMap<String, String>();
		for (Session session : sessions) {
			sessionTimes.put(session.getId(), session.getSessionTime());
		}
		SortedMap<String, Double> data = new TreeMap<String, Double>();

		for (SoilScientist s : sslist) {
			Date d = null;
			String date = sessionTimes.get(s.getSession_id());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			try {
				d = simpleDateFormat.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();

			}
			s.setDate(d);
		}
		Collections.sort(sslist);
		int end = sslist.size() - 11;
		for (int i = sslist.size() - 1; i > end; i--) {
			SoilScientist s = sslist.get(i);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"MM/dd HH:mm", Locale.US);
			String d = simpleDateFormat.format(s.getDate());
			try {
				data.put(d, Double.parseDouble(getSSData(s, selectedDataType)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				if (end >= 0)
					end--;
			}
		}

		return data;
	}

	private String getData(Meteorologist m, String selectedDataType2) {
		if (selectedDataType2.equals("Temperature"))
			return m.getFahrenheit();
		else if (selectedDataType2.equals("Air Pressure"))
			return m.getPressure();
		else if (selectedDataType2.equals("Humidity"))
			return m.getHumidity();
		else if (selectedDataType2.equals("Wind"))
			return m.getWind();
		else if (selectedDataType2.equals("Average Rainfall"))
			return m.getRainfall();

		return null;
	}

	private String getSSData(SoilScientist m, String selectedDataType2) {
		if (selectedDataType2.equals("Soil pH Levels"))
			return m.getSoil_ph();
		else if (selectedDataType2.equals("Soil Moisture"))
			return m.getSoil_moisture();

		return null;
	}

	public SortedMap<String, Double> getPieData() {
		List<Naturalist> nlist = DatabaseManager.getCollectedDataByRole(
				DatabaseManager.NATURALIST_TABLE, selectedGroupID);
		SortedMap<String, Double> data = new TreeMap<String, Double>();

		double snail = 0, bristletail = 0, lacewing = 0, mayfly = 0, thrip = 0, pillbug = 0, beetle = 0, spider = 0, butterfly = 0, grasshopper = 0, worm = 0, springtail = 0, larvae = 0, woodroach = 0, cadisfly = 0, scorpion = 0, tick = 0, earwig = 0, stonefly = 0, caterpillar = 0, centipede = 0, aphid = 0, booklice = 0, fly = 0, bee = 0, ant = 0;

		// get fields
		if (selectedSession != "All") {
			Naturalist nat = null;
			for (Naturalist n : nlist) {
				if (n.getSession_id().equals(selectedSession))
					nat = n;
			}
			nlist.removeAll(nlist);
			nlist.add(nat);
		}

		for (Naturalist nat : nlist) {
			try {
				snail += Double.parseDouble(nat.getSnail());
			} catch (NumberFormatException e) {
			}
			try {
				bristletail += Double.parseDouble(nat.getBristletail());
			} catch (NumberFormatException e) {
			}
			try {
				lacewing += Double.parseDouble(nat.getLacewing());
			} catch (NumberFormatException e) {
			}
			try {
				mayfly += Double.parseDouble(nat.getMayfly());
			} catch (NumberFormatException e) {
			}
			try {
				thrip += Double.parseDouble(nat.getThrip());
			} catch (NumberFormatException e) {
			}
			try {
				pillbug += Double.parseDouble(nat.getPillbug());
			} catch (NumberFormatException e) {
			}
			try {
				beetle += Double.parseDouble(nat.getBeetle());
			} catch (NumberFormatException e) {
			}
			try {
				spider += Double.parseDouble(nat.getSpider());
			} catch (NumberFormatException e) {
			}
			try {
				butterfly += Double.parseDouble(nat.getButterfly());
			} catch (NumberFormatException e) {
			}
			try {
				grasshopper += Double.parseDouble(nat.getGrasshopper());
			} catch (NumberFormatException e) {
			}
			try {
				worm += Double.parseDouble(nat.getWorm());
			} catch (NumberFormatException e) {
			}
			try {
				springtail += Double.parseDouble(nat.getSpringtail());
			} catch (NumberFormatException e) {
			}
			try {
				larvae += Double.parseDouble(nat.getLarvae());
			} catch (NumberFormatException e) {
			}
			try {
				woodroach += Double.parseDouble(nat.getWoodroach());
			} catch (NumberFormatException e) {
			}
			try {
				cadisfly += Double.parseDouble(nat.getCadisfly());
			} catch (NumberFormatException e) {
			}
			try {
				scorpion += Double.parseDouble(nat.getScorpion());
			} catch (NumberFormatException e) {
			}
			try {
				tick += Double.parseDouble(nat.getTick());
			} catch (NumberFormatException e) {
			}
			try {
				earwig += Double.parseDouble(nat.getEarwig());
			} catch (NumberFormatException e) {
			}
			try {
				stonefly += Double.parseDouble(nat.getStonefly());
			} catch (NumberFormatException e) {
			}
			try {
				caterpillar += Double.parseDouble(nat.getCaterpillar());
			} catch (NumberFormatException e) {
			}
			try {
				centipede += Double.parseDouble(nat.getCentipede());
			} catch (NumberFormatException e) {
			}
			try {
				aphid += Double.parseDouble(nat.getAphid());
			} catch (NumberFormatException e) {
			}
			try {
				booklice += Double.parseDouble(nat.getBooklice());
			} catch (NumberFormatException e) {
			}
			try {
				fly += Double.parseDouble(nat.getFly());
			} catch (NumberFormatException e) {
			}
			try {
				bee += Double.parseDouble(nat.getBee());
			} catch (NumberFormatException e) {
			}
			try {
				ant += Double.parseDouble(nat.getAnt());
			} catch (NumberFormatException e) {
			}
		}

		double total = snail + bristletail + lacewing + mayfly + thrip
				+ pillbug + beetle + spider + butterfly + grasshopper + worm
				+ springtail + larvae + woodroach + cadisfly + scorpion + tick
				+ earwig + stonefly + caterpillar + centipede + aphid
				+ booklice + fly + bee + ant;

		snail = snail / total * 100;
		bristletail = bristletail / total * 100;
		lacewing = lacewing / total * 100;
		mayfly = mayfly / total * 100;
		thrip = thrip / total * 100;
		pillbug = pillbug / total * 100;
		beetle = beetle / total * 100;
		spider = spider / total * 100;
		butterfly = butterfly / total * 100;
		grasshopper = grasshopper / total * 100;
		worm = worm / total * 100;
		springtail = springtail / total * 100;
		larvae = larvae / total * 100;
		woodroach = woodroach / total * 100;
		cadisfly = cadisfly / total * 100;
		scorpion = scorpion / total * 100;
		tick = tick / total * 100;
		earwig = earwig / total * 100;
		stonefly = stonefly / total * 100;
		caterpillar = caterpillar / total * 100;
		centipede = centipede / total * 100;
		aphid = aphid / total * 100;
		booklice = booklice / total * 100;
		fly = fly / total * 100;
		bee = bee / total * 100;
		ant = ant / total * 100;

		DecimalFormat df = new DecimalFormat("#.#");
		if (snail != 0 && !Double.isNaN(snail))
			data.put("Snail - " + df.format(snail) + "%", snail);
		if (bristletail != 0 && !Double.isNaN(bristletail))
			data.put("Bristletail - " + df.format(bristletail) + "%",
					bristletail);
		if (lacewing != 0 && !Double.isNaN(lacewing))
			data.put("Lacewing - " + df.format(lacewing) + "%", lacewing);
		if (mayfly != 0 && !Double.isNaN(mayfly))
			data.put("Mayfly - " + df.format(mayfly) + "%", mayfly);
		if (thrip != 0 && !Double.isNaN(thrip))
			data.put("Thrip - " + df.format(thrip) + "%", thrip);
		if (pillbug != 0 && !Double.isNaN(pillbug))
			data.put("Pillbug - " + df.format(pillbug) + "%", pillbug);
		if (beetle != 0 && !Double.isNaN(beetle))
			data.put("Beetle - " + df.format(beetle) + "%", beetle);
		if (spider != 0 && !Double.isNaN(spider))
			data.put("Spider - " + df.format(spider) + "%", spider);
		if (butterfly != 0 && !Double.isNaN(butterfly))
			data.put("Butterfly - " + df.format(butterfly) + "%", butterfly);
		if (grasshopper != 0 && !Double.isNaN(grasshopper))
			data.put("Grasshopper - " + df.format(grasshopper) + "%",
					grasshopper);
		if (worm != 0 && !Double.isNaN(worm))
			data.put("Worm - " + df.format(worm) + "%", worm);
		if (springtail != 0 && !Double.isNaN(springtail))
			data.put("Springtail - " + df.format(springtail) + "%", springtail);
		if (larvae != 0 && !Double.isNaN(larvae))
			data.put("Larvae - " + df.format(larvae) + "%", larvae);
		if (woodroach != 0 && !Double.isNaN(woodroach))
			data.put("Woodroach - " + df.format(woodroach) + "%", woodroach);
		if (cadisfly != 0 && !Double.isNaN(cadisfly))
			data.put("Cadisfly - " + df.format(cadisfly) + "%", cadisfly);
		if (scorpion != 0 && !Double.isNaN(scorpion))
			data.put("Scorpion - " + df.format(scorpion) + "%", scorpion);
		if (tick != 0 && !Double.isNaN(tick))
			data.put("Tick - " + df.format(tick) + "%", tick);
		if (earwig != 0 && !Double.isNaN(earwig))
			data.put("Earwig - " + df.format(earwig) + "%", earwig);
		if (stonefly != 0 && !Double.isNaN(stonefly))
			data.put("Stonefly - " + df.format(stonefly) + "%", stonefly);
		if (caterpillar != 0 && !Double.isNaN(caterpillar))
			data.put("Caterpillar - " + df.format(caterpillar) + "%",
					caterpillar);
		if (centipede != 0 && !Double.isNaN(centipede))
			data.put("Centipede - " + df.format(centipede) + "%", centipede);
		if (aphid != 0 && !Double.isNaN(aphid))
			data.put("Aphid - " + df.format(aphid) + "%", aphid);
		if (booklice != 0 && !Double.isNaN(booklice))
			data.put("Booklice - " + df.format(booklice) + "%", booklice);
		if (fly != 0 && !Double.isNaN(fly))
			data.put("Fly - " + df.format(fly) + "%", fly);
		if (bee != 0 && !Double.isNaN(bee))
			data.put("Bee - " + df.format(bee) + "%", bee);
		if (ant != 0 && !Double.isNaN(ant))
			data.put("Ant - " + df.format(ant) + "%", ant);

		return data;
	}
}
