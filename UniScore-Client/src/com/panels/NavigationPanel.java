/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.panels;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class NavigationPanel extends JPanel{

	/*
	 * Method getNavigation : used to return the sub elements added to the JPanel. Contains all the elements which is displayed on the left side of the application(JFrame LecturerPanel/StudentPanel)
	 * @returns JPanel which is wrapped by a NavigationPanel(JPanel with an extra method)
	 */
	public abstract JPanel getNavigation();
}
