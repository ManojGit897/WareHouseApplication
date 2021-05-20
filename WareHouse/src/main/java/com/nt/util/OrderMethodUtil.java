package com.nt.util;

import java.io.File;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class OrderMethodUtil {
	
	public void generatePieChart(String path,List<Object[]> data) {

	// 1. prepare data source
	
	DefaultPieDataset dataset=new DefaultPieDataset();
	for(Object[] ob:data) {
		// key(String)-- val(Double)
		dataset.setValue(ob[0].toString(),
				Double.valueOf(ob[1].toString()));
	}
	 
	// 2. create Jfree Chart Object
	JFreeChart chart=ChartFactory.createPieChart(" ORDER METHOD TYPE", dataset);
	
	//3. save as Image
	
	try {
		ChartUtils.saveChartAsJPEG(
				new File(path+"/orderMethodTypeA.jpg"),
				chart, // Jfree object
				300,// width
				300); // object
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public void generateBarChart(String path,List<Object[]> data) {

		// 1. prepare data source
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] ob:data) {
			// key(String)-- val(Double)
			dataset.setValue(
					Double.valueOf(ob[1].toString()),
					ob[0].toString(),
					"" // display lable
					);
		}
		 
		// 2. create Jfree Chart Object
		//Input => title, x-axis label, y-axis-label, dataset
		JFreeChart chart=ChartFactory.createBarChart(" ORDER METHOD TYPE", "ORDER TYPE", "COUNTS", dataset);
		//3. save as Image
		
		try {
			ChartUtils.saveChartAsJPEG(
					new File(path+"/orderMethodTypeB.jpg"),
					chart, // Jfree object
					450,// width
					450); // object
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}