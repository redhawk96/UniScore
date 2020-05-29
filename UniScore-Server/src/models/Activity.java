/* 
 * Module		: Comparative Integrated Systems(SLIIT) 19-20SEM2OTSLI009-3 
 * Project		: UniScore - Online Examination Management System
 * Group		: 19
 * @author		: Uditha Silva (UOB-1938086)
 */

package models;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Activity implements Serializable{

	// Declaring properties of the model
	private int activityId;
	private String activityBrief, triggeredBy;
	Timestamp triggeredOn;
	
	/*
	 * Activity class constructor, not required to define necessarly but declared if needed for further development
	 */
	public Activity() {}

	/*
	 * Activity method is being overloaded to accept 2 parameters, this method is used to add an activity into the database
	 * @param activityBrief which is the in-detail description of the activity
	 * @param triggeredBy which is the user id of the user who performed the activity
	 */
	public Activity(String activityBrief, String triggeredBy) {
		this.activityBrief = activityBrief;
		this.triggeredBy = triggeredBy;
	}

	/*
	 * @returns activityId which contains an incremental figure hence unique to identify and view specific activity without hassle 
	 */
	public int getActivityId() {
		return activityId;
	}

	/*
	 * @param activityId which is the identifier of the activity
	 */
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	/*
	 * @returns activityBrief which contains a in-detail brief of the activity that occured
	 */
	public String getActivityBrief() {
		return activityBrief;
	}

	/*
	 * @param activityBrief which is the in-detail description of the activity
	 */
	public void setActivityBrief(String activityBrief) {
		this.activityBrief = activityBrief;
	}

	/*
	 * @returns triggeredBy which contains a the user who performed the activity
	 */
	public String getTriggeredBy() {
		return triggeredBy;
	}

	/*
	 * @param triggeredBy which is the user id of the user who performed the activity
	 */
	public void setTriggeredBy(String triggeredBy) {
		this.triggeredBy = triggeredBy;
	}

	/*
	 * @returns triggeredBy which contains a the timestamp of the occured activity
	 */
	public Timestamp getTriggeredOn() {
		return triggeredOn;
	}

	/*
	 * @param triggeredOn which is the timestamp performed the activity
	 */
	public void setTriggeredOn(Timestamp triggeredOn) {
		this.triggeredOn = triggeredOn;
	}
	
}
