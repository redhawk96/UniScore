/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.utils;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class ContentTable extends JTable{

	/*
	 * Method isCellEditable : This is to override the default cell editable method on a Jtable to return false;
	 * @params row		 required but not used
	 * @params col		 required but not used
	 * @returns boolean  will always return false
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
        return false;
    }
}
