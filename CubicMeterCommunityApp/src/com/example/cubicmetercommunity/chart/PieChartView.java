package com.example.cubicmetercommunity.chart;

import java.util.SortedMap;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.data.general.DefaultPieDataset;
import org.afree.data.general.PieDataset;


import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("ViewConstructor")
public class PieChartView extends DemoView{
		
		
	  public PieChartView(Context context, String title, SortedMap<String, Double> sortedMap ) {
	        super(context);
	        PieDataset dataset = createDataset(sortedMap);
	        AFreeChart chart = createChart(dataset, title);

	        setChart(chart);
	    }
	  
	  @SuppressLint("UseValueOf")
	private static PieDataset createDataset(SortedMap<String, Double> sortedMap) {
		          DefaultPieDataset dataset = new DefaultPieDataset();
		          for (String k : sortedMap.keySet()){
		        	 dataset.setValue(k, sortedMap.get(k));		        	  
		          }		       
		      
		          
		          return dataset;
		      }
	  
	  private static AFreeChart createChart(PieDataset dataset, String title) {
		  
		  AFreeChart chart = ChartFactory.createPieChart(
		              title + " Chart",  // chart title
		             dataset,            // data
		              true,              // no legend
		              true,               // tooltips
		             false               // no URL generation
		          );

		          return chart;
		  
		      }
}
