/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package com.utils;

/*
 * ExceptionList class is defined to keep a unique number to identify the exceptions thrown and to establish a common pattern to handling exceptions in all cases
 */
public class ExceptionList {
	public static int REMOTE = 100;
	public static int CLASS_NOT_FOUND = 200;
	public static int SQL = 300;
	public static int SQL_FAILED_EXECUTION = 301;
	public static int NO_SUCH_ALGORITHM = 400;
	public static int NO_SUCH_PROVIDER = 500;
	public static int NOT_BOUND = 600;
	public static int IO = 700;
	public static int JASPER_REPORT = 800;
	public static int ADDRESS = 900;
	public static int MESSAGING = 1000;
}
