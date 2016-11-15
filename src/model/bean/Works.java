/**
 * 
 */
package model.bean;

/**
 * @author pvhuy84
 *
 */
public class Works {
	private String taskName;
	private int day;
	private int time;
	public Works() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Works(String taskName, int day, int time) {
		super();
		this.taskName = taskName;
		this.day = day;
		this.time = time;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
