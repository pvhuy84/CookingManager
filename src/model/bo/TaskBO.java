package model.bo;

import java.util.ArrayList;

import model.bean.Task;
import model.bean.User;
import model.dao.TaskDAO;

public class TaskBO {
	private TaskDAO taskDAO = new TaskDAO();
	public ArrayList<Task> getTaskList() {
		return taskDAO.getTaskList();
	}
	public boolean updateTask(Task task) {
		return taskDAO.updateTask(task);
	}
	
	//Lấy dữ liệu công việc đã đăng ký (pvhuy84)
	public ArrayList<model.bean.Task> getListTaskRegistered(User user) {
		return taskDAO.getListTaskRegistered(user);
	}
	

}
