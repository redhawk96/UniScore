/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.panels;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class ContentPanel extends JPanel {

	/*
	 * Method getContent : used to return the sub elements added to the JPanel. Contains all the elements which is displayed on the right side of the application(JFrame LecturerPanel/StudentPanel)
	 * @returns JPanel which is wrapped by a ContentPanel(JPanel with an extra method)
	 */
	public abstract JPanel getContent();
}
