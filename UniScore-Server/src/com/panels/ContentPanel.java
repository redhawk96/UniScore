package com.panels;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class ContentPanel extends JPanel {

	public abstract JPanel getContent();
}
