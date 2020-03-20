package com.utils;

import java.awt.Color;
import java.awt.Font;

public class UI {

	// Common Ui class implemented to reduce complexity and to avoid various
	// properties been implemented for the identical components

	/*
	 * Common properties
	 */
	public static Color APPLICATION_THEME_COLOR = new Color(249, 168, 37);
	public static Font APPLICATION_THEME_FONT = new Font("Roboto", Font.BOLD, 14);
	public static int APPLICATION_WIDTH = 1091;
	public static int APPLICATION_HEIGHT = 672;

	/*
	 * Loginin panel properties
	 */
	public static Color LOGIN_PANEL_BACKGROUND_COLOR = Color.WHITE;
	public static Color LOGIN_PANEL_DEFAULT_TEXT_FIELD_COLOR = Color.BLACK;
	public static Font LOGIN_PANEL_DEFAULT_TEXT_FIELD_FONT = new Font("Roboto", Font.PLAIN, 14);
	
	public static Color LOGIN_PANEL_LOGIN_BUTTON_COLOR = Color.WHITE;
	public static Color LOGIN_PANEL_LOGIN_BUTTON_TEXT_COLOR = new Color(249, 168, 37);
	public static Color LOGIN_PANEL_LOGIN_BUTTON_BORDER_COLOR = new Color(249, 168, 37);
	public static Font LOGIN_PANEL_LOGIN_BUTTON_FONT = new Font("Roboto", Font.PLAIN, 17);
	
	public static Color LOGIN_PANEL_SELECTED_LOGIN_BUTTON_COLOR = new Color(249, 168, 37);
	public static Color LOGIN_PANEL_SELECTED_LOGIN_BUTTON_TEXT_COLOR = Color.WHITE;
	public static Color LOGIN_PANEL_SELECTED_LOGIN_BUTTON_BORDER_COLOR = new Color(249, 168, 37);

	
	/*
	 * Navigation panel properties
	 */
	public static Color NAVIGATION_PANEL_COLOR = Color.DARK_GRAY;
	public static Color NAVIGATION_PANEL_ICON_COLOR = new Color(235, 240, 247);

	public static int NAVIGATION_PANEL_BUTTON_WIDTH = 223;
	public static int NAVIGATION_PANEL_BUTTON_HEIGHT = 62;
	public static Color NAVIGATION_PANEL_BUTTON_COLOR = Color.DARK_GRAY;
	public static Color NAVIGATION_PANEL_BUTTON_TEXT_COLOR = new Color(249, 168, 37);
	public static Font NAVIGATION_PANEL_BUTTON_FONT = new Font("Roboto", Font.BOLD, 14);

	public static int NAVIGATION_PANEL_BUTTON_TEXT_X_AXIS = 89;
	public static int NAVIGATION_PANEL_BUTTON_TEXT_Y_AXIS = 14;
	public static int NAVIGATION_PANEL_BUTTON_TEXT_WIDTH = 95;
	public static int NAVIGATION_PANEL_BUTTON_TEXT_HEIGHT = 37;

	public static int NAVIGATION_PANEL_BUTTON_ICON_X_AXIS = 27;
	public static int NAVIGATION_PANEL_BUTTON_ICON_Y_AXIS = 18;
	public static int NAVIGATION_PANEL_BUTTON_ICON_WIDTH = 27;
	public static int NAVIGATION_PANEL_BUTTON_ICON_HEIGHT = 24;

	public static Color NAVIGATION_PANEL_SELECTED_BUTTON_COLOR = new Color(51, 50, 47);
	public static Color NAVIGATION_PANEL_SELECTED_BUTTON_TEXT_COLOR = new Color(249, 168, 37);

	/*
	 * Content panel properties
	 */
	public static Color CONTENT_PANEL_BACKGROUND_COLOR = new Color(235, 240, 247);

	public static int CONTENT_PANEL__X_AXIS = 0;
	public static int CONTENT_PANEL__Y_AXIS = 0;
	public static int CONTENT_PANEL__WIDTH = 852;
	public static int CONTENT_PANEL__HEIGHT = 633;
}
