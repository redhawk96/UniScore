/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.utils;

import java.awt.BasicStroke;
import java.awt.Paint;

import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;

public class BarChartPanel {

	// Declaring chart, used to create a image once the chart has been plotted and styled
	JFreeChart chart;

	/*
	 * Method BarChartPanel : used to initialize JFrame, required properties and add UI elements to the JFrame
	 * @params chartTitle		The title used to name the graph, heading
	 * @params xAxis		 	Title at the bottom of the graph
	 * @params dataset		 	Title at the left grid line of the graph
	 */
	public BarChartPanel(String chartTitle, String xAxis, String yAxis, CategoryDataset dataset) {
		// Plotting a new chart object with, graph title, x-axis title, y-axis title, and dataset which is provided
		chart = ChartFactory.createBarChart(chartTitle, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true, true, false);
				
		// Styling the chart	
		StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
		theme.setTitlePaint(UI.APPLICATION_THEME_SECONDARY_COLOR);
		theme.setExtraLargeFont(UI.APPLICATION_THEME_FONT_18_PLAIN); // title
		theme.setLargeFont(UI.APPLICATION_THEME_FONT_17_PLAIN); // axis-title
		theme.setRegularFont(UI.APPLICATION_THEME_FONT_15_PLAIN);
		theme.setRangeGridlinePaint(UI.APPLICATION_THEME_TERTIARY_COLOR);
		theme.setPlotOutlinePaint((Paint)UI.APPLICATION_THEME_SECONDARY_COLOR);
		theme.setPlotBackgroundPaint(UI.APPLICATION_THEME_TERTIARY_COLOR);
		theme.setChartBackgroundPaint(UI.APPLICATION_THEME_TERTIARY_COLOR);
		theme.setDomainGridlinePaint(UI.APPLICATION_THEME_SECONDARY_COLOR);
		theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
		theme.setBarPainter(new StandardBarPainter());
		theme.setAxisLabelPaint(UI.APPLICATION_THEME_SECONDARY_COLOR);
		theme.apply(chart);
		chart.getCategoryPlot().setOutlineVisible(false);
		chart.getCategoryPlot().getRangeAxis().setAxisLineVisible(false);
		chart.getCategoryPlot().getRangeAxis().setTickMarksVisible(false);
		chart.getCategoryPlot().setRangeGridlineStroke(new BasicStroke());
		chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(UI.APPLICATION_THEME_SECONDARY_COLOR);
		chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(UI.APPLICATION_THEME_SECONDARY_COLOR);
		ValueAxis yAxisRange = chart.getCategoryPlot().getRangeAxis();
		yAxisRange.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		chart.setTextAntiAlias(true);
		chart.setAntiAlias(true);
		chart.getCategoryPlot().getRenderer().setSeriesPaint(0, UI.APPLICATION_THEME_PRIMARY_COLOR);
		BarRenderer rend = (BarRenderer) chart.getCategoryPlot().getRenderer();
		rend.setShadowVisible(true);
		rend.setShadowXOffset(2);
		rend.setShadowYOffset(0);
		rend.setShadowPaint(UI.APPLICATION_THEME_QUATERNARY_COLOR);
		rend.setMaximumBarWidth(0.1);
		chart.removeLegend();
	}

	/*
	 * Method getChart : used to return a image of the plotted and style graph with the provided dataset
	 * @returns ImageIcon	Image contaning the finalized graph
	 */
	public ImageIcon getChart() {
		// Creating a new ImageIcon with a with of 1197 and height of 657
		 return new ImageIcon(chart.createBufferedImage(1197,657));
	}
}
