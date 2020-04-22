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

	JFreeChart chart;

	public BarChartPanel(String chartTitle, String xAxis, String yAxis, CategoryDataset dataset) {
		// Create chart
		chart = ChartFactory.createBarChart(chartTitle, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true, true, false);

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

	public ImageIcon getChart() {
		 return new ImageIcon(chart.createBufferedImage(1197,657));
	}
}
