/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.border.BevelBorder;

public class UI {

	// Common Ui class implemented to reduce complexity and to avoid different values been set for the identical components
	
	/*
	 * Primary Application properties
	 */
	public static int 	APPLICATION_PRIMARY_FRAME_WIDTH = 1510;
	public static int 	APPLICATION_PRIMARY_FRAME_HEIGHT = 940;
	
	public static Color APPLICATION_THEME_PRIMARY_COLOR = new Color(249, 168, 37); // Yellow color
	public static Color APPLICATION_THEME_SECONDARY_COLOR = Color.DARK_GRAY;
	public static Color APPLICATION_THEME_TERTIARY_COLOR = Color.WHITE;
	public static Color APPLICATION_THEME_QUATERNARY_COLOR = Color.GRAY;
	
	private static String APPLICATION_THEME_FONT = "Roboto";
	public static Font 	APPLICATION_THEME_FONT_10_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 10);
	public static Font 	APPLICATION_THEME_FONT_12_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 12);
	public static Font 	APPLICATION_THEME_FONT_13_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 13);
	public static Font 	APPLICATION_THEME_FONT_14_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 14);
	public static Font 	APPLICATION_THEME_FONT_14_BOLD 	= new Font(APPLICATION_THEME_FONT, Font.BOLD, 14);
	public static Font 	APPLICATION_THEME_FONT_15_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 15);
	public static Font 	APPLICATION_THEME_FONT_16_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 16);
	public static Font 	APPLICATION_THEME_FONT_17_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 17);
	public static Font 	APPLICATION_THEME_FONT_17_BOLD 	= new Font(APPLICATION_THEME_FONT, Font.BOLD, 17);
	public static Font 	APPLICATION_THEME_FONT_18_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 18);
	public static Font 	APPLICATION_THEME_FONT_18_BOLD 	= new Font(APPLICATION_THEME_FONT, Font.BOLD, 18);
	public static Font 	APPLICATION_THEME_FONT_20_PLAIN = new Font(APPLICATION_THEME_FONT, Font.PLAIN, 20);
	
	public static int APPPLICATION_THEME_SELECT_CURSOR = Cursor.HAND_CURSOR;
	
	/*
	 * Login Application Properties
	 */
	public static int LOGIN_FRAME_WIDTH = 1091;
	public static int LOGIN_FRAME_HEIGHT = 672;
	
	/*
	 * Navigation Panel Properties
	 */
	public static int NAVIGATION_PANEL_X_AXIS = 0;
	public static int NAVIGATION_PANEL_Y_AXIS = 0;
	public static int NAVIGATION_PANEL_WIDTH = 255;
	public static int NAVIGATION_PANEL_HEIGHT = 911;
	
	public static int NAVIGATION_PANEL_BUTTON_HEIGHT = 60;

	public static int NAVIGATION_PANEL_BUTTON_TEXT_X_AXIS = 100;
	public static int NAVIGATION_PANEL_BUTTON_TEXT_Y_AXIS = 14;
	public static int NAVIGATION_PANEL_BUTTON_TEXT_WIDTH = 95;
	public static int NAVIGATION_PANEL_BUTTON_TEXT_HEIGHT = 37;

	public static int NAVIGATION_PANEL_BUTTON_ICON_X_AXIS = 35;
	public static int NAVIGATION_PANEL_BUTTON_ICON_Y_AXIS = 18;
	public static int NAVIGATION_PANEL_BUTTON_ICON_WIDTH = 27;
	public static int NAVIGATION_PANEL_BUTTON_ICON_HEIGHT = 24;

	public static Color NAVIGATION_PANEL_SELECTED_BUTTON_COLOR = new Color(51, 50, 47); // Extra dark-gray color

	/*
	 * Content Panel Properties
	 */
	public static int CONTENT_PANEL_X_AXIS = 0;
	public static int CONTENT_PANEL_Y_AXIS = 0;
	public static int CONTENT_PANEL_WIDTH = 1281;
	public static int CONTENT_PANEL_HEIGHT = 911;
	
	/*
	 * Card Panel Properties
	 */
	public static int 	CARD_WIDTH = 270;
	public static int 	CARD_HEIGHT = 90;
	public static int 	CARD_Y_AXIS = 58;
	
	public static int 	CARD_LABEL_TEXT_WIDTH = 188;
	public static int 	CARD_LABEL_NUMBER_WIDTH = 81;
	
	/*
	 * Navigation Indicator Panel Properties
	 */
	public static BevelBorder NAVIGATION_INDICATOR_PANEL_BORDER =  new BevelBorder(BevelBorder.RAISED, APPLICATION_THEME_TERTIARY_COLOR, APPLICATION_THEME_TERTIARY_COLOR, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
	
	public static int 	NAVIGATION_INDICATOR_PANEL_HEIGHT = 17;
	public static int 	NAVIGATION_INDICATOR_PANEL_Y_AXIS = 8;
}
