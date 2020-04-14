package models;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Activity implements Serializable{

	private int activityId;
	private String activityBrief, triggeredBy;
	Timestamp triggeredOn;
	
	public Activity() {}

	/**
	 * @param activityBrief
	 * @param triggeredBy
	 */
	public Activity(String activityBrief, String triggeredBy) {
		this.activityBrief = activityBrief;
		this.triggeredBy = triggeredBy;
	}

	/**
	 * @param activityId
	 * @param activityBrief
	 * @param triggeredBy
	 * @param triggeredOn
	 */
	public Activity(int activityId, String activityBrief, String triggeredBy, Timestamp triggeredOn) {
		this.activityId = activityId;
		this.activityBrief = activityBrief;
		this.triggeredBy = triggeredBy;
		this.triggeredOn = triggeredOn;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getActivityBrief() {
		return activityBrief;
	}

	public void setActivityBrief(String activityBrief) {
		this.activityBrief = activityBrief;
	}

	public String getTriggeredBy() {
		return triggeredBy;
	}

	public void setTriggeredBy(String triggeredBy) {
		this.triggeredBy = triggeredBy;
	}

	public Timestamp getTriggeredOn() {
		return triggeredOn;
	}

	public void setTriggeredOn(Timestamp triggeredOn) {
		this.triggeredOn = triggeredOn;
	}
	
}
