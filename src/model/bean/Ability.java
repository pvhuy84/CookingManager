/**
 * 
 */
package model.bean;

/**
 * @author pvhuy84
 *
 */
public class Ability {
	private String username;
	private String taskId;
	public Ability() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ability(String username, String taskId) {
		super();
		this.username = username;
		this.taskId = taskId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	

}
