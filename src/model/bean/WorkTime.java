package model.bean;

import java.util.ArrayList;

public class WorkTime {
	private int workTimeId;
	private String username;
	private int week;
	private int day;
	private int time;
	public WorkTime(int workTimeId, String username, int week, int day, int time) {
		super();
		this.workTimeId = workTimeId;
		this.username = username;
		this.week = week;
		this.day = day;
		this.time = time;
	}
	public int getWorkTimeId() {
		return workTimeId;
	}
	public void setWorkTimeId(int workTimeId) {
		this.workTimeId = workTimeId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
}
