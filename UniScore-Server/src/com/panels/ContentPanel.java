/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package com.panels;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class ContentPanel extends JPanel {

	public abstract JPanel getContent();
}
