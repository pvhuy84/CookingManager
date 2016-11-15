package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Task;
import model.bean.User;

public class TaskDAO extends DatabaseFactory{

	public ArrayList<Task> getTaskList() {
			ArrayList<Task> tasks = new ArrayList<>();
			String query = "select * from task";
			try {
				preparedStatement = connection.prepareStatement(query);
				System.out.println(preparedStatement.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Task task = new Task();
					task.setTaskId(rs.getString("taskId"));
					task.setTaskName(rs.getString("taskName"));
					task.setTaskAmount(rs.getInt("taskAmount"));
					tasks.add(task);
				}
				preparedStatement.close();
				return tasks;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	public boolean updateTask(Task task) {
		String query = "Update Task SET taskAmount = ? WHERE taskId = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, task.getTaskAmount());
			preparedStatement.setString(2, task.getTaskId());
			System.out.println(preparedStatement.toString());
			if(preparedStatement.execute()){
				preparedStatement.close();
				return true;
			}
			else{
				preparedStatement.close();
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean deleteTask(Task task) {
		String query = "DELETE FROM `task` WHERE taskId = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, task.getTaskId());
			System.out.println(preparedStatement.toString());
			if(preparedStatement.execute()){
				preparedStatement.close();
				return true;
			}
			else{
				preparedStatement.close();
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean addTask(Task task) {
		String query = "INSERT INTO `task` (`taskId`, `taskName`, `taskAmount`) VALUES ('?', '?', '?');";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, task.getTaskId());
			preparedStatement.setString(2, task.getTaskName());
			preparedStatement.setInt(3, task.getTaskAmount());
			System.out.println(preparedStatement.toString());
			if(preparedStatement.execute()){
				preparedStatement.close();
				return true;
			}
			else{
				preparedStatement.close();
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Lấy dữ liệu công việc đã đăng ký (pvhuy84)
	public ArrayList<Task> getListTaskRegistered(User user) {
		ArrayList<Task> listTaskRegistered = new ArrayList<>();
		String query = "SELECT task.taskId, task.taskName, task.taskAmount FROM ability JOIN task ON ability.taskId = task.taskId  WHERE ability.username=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getUsername());
			System.out.println(preparedStatement.toString());
			System.out.println("user: " + user.getUsername());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Task task = new Task();
				task.setTaskId(rs.getString("taskId"));
				task.setTaskName(rs.getString("taskName"));
				task.setTaskAmount(rs.getInt("taskAmount"));
				listTaskRegistered.add(task);
			}
			preparedStatement.close();
			return listTaskRegistered;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
