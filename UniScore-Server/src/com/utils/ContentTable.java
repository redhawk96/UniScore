/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package com.utils;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class ContentTable extends JTable{

	/*
	 * isCellEditable : This is to override the default cell editable method on a Jtable to return false;
	 * @params {int, int}
	 * @returns {false}
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
        return false;
    }
}
