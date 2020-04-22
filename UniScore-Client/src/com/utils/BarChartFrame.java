package com.utils;
import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;

public class BarChartFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public BarChartFrame(String chartFrameTitle, String chartTitle, String xAxis, String yAxis, CategoryDataset dataset) {
		super(chartFrameTitle);
		
		// Create chart
		JFreeChart chart = ChartFactory.createBarChart3D(chartTitle, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true, true, false);
		
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
				
	    StandardChartTheme theme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();

	    theme.setTitlePaint( UI.APPLICATION_THEME_SECONDARY_COLOR );
	    theme.setExtraLargeFont(UI.APPLICATION_THEME_FONT_18_BOLD); //title
	    theme.setLargeFont(UI.APPLICATION_THEME_FONT_16_PLAIN); //axis-title
	    theme.setRegularFont(UI.APPLICATION_THEME_FONT_10_PLAIN);
	    theme.setRangeGridlinePaint( UI.APPLICATION_THEME_SECONDARY_COLOR);
	    theme.setPlotBackgroundPaint(UI.APPLICATION_THEME_TERTIARY_COLOR);
	    theme.setChartBackgroundPaint(UI.APPLICATION_THEME_TERTIARY_COLOR);
		theme.setGridBandPaint(Color.red);
		theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
		theme.setBarPainter(new StandardBarPainter());
		theme.setAxisLabelPaint(Color.decode("#666666"));
		theme.apply(chart);
		chart.getCategoryPlot().setOutlineVisible(false);
		chart.getCategoryPlot().getRangeAxis().setAxisLineVisible(false);
		chart.getCategoryPlot().getRangeAxis().setTickMarksVisible(false);
		chart.getCategoryPlot().setRangeGridlineStroke(new BasicStroke());
		chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(Color.decode("#666666"));
		chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(Color.decode("#666666"));
		ValueAxis yAxisRange = chart.getCategoryPlot().getRangeAxis();
		yAxisRange.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		chart.setTextAntiAlias(true);
		chart.setAntiAlias(true);
		chart.getCategoryPlot().getRenderer().setSeriesPaint(0, UI.APPLICATION_THEME_PRIMARY_COLOR);
		BarRenderer rend = (BarRenderer) chart.getCategoryPlot().getRenderer();
		rend.setShadowVisible(true);
		rend.setShadowXOffset(2);
		rend.setShadowYOffset(0);
		rend.setShadowPaint(Color.decode("#C0C0C0"));
		rend.setMaximumBarWidth(0.1);	 
		chart.removeLegend();
	}
}