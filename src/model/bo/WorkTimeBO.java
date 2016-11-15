package model.bo;

import java.util.ArrayList;

import model.bean.User;
import model.bean.WorkTime;
import model.bean.Worker;
import model.dao.WorkTimeDAO;

public class WorkTimeBO {
	private WorkTimeDAO workTimeDAO = new WorkTimeDAO();
	
	public ArrayList<Worker> getRegisterList(){
		return workTimeDAO.getRegisterList();
	}
	
	//Lấy dữ liệu thời gian rảnh (pvhuy84)
	public ArrayList<WorkTime> getFreeTime(User user) {
		return new WorkTimeDAO().getFreeTime(user);
	}

	//Cập nhật thời gian rảnh (pvhuy84)
	public void updateFreeTime(User user, String[] freeTime, int time) {
		new WorkTimeDAO().updateFreeTime(user, freeTime, time);
		
	}
}
