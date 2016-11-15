package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.User;
import model.bean.WorkTime;
import model.bean.Worker;

public class WorkTimeDAO extends DatabaseFactory {
	public void getWorkTimeList(int week) {

	}

	public void getWorkTimeListIn(int week, int day) {

	}

	public ArrayList<Worker> getRegisterList() {
		String query = "SELECT user.username, COUNT(worktime.worktimeId) AS numOfFreeTime FROM user LEFT JOIN worktime ON user.username = worktime.username "
				+ "WHERE user.isAdmin = 0 AND worktime.week = ? GROUP BY user.username";
		ArrayList<Worker> workers = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, new SchedulerDAO().getNextWeek());
			System.out.println("Task List: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Worker worker = new Worker();
				worker.setUsername(rs.getString("username"));
				worker.setNumOfFreeTime(rs.getInt("numOfFreeTime"));
				workers.add(worker);
			}
			preparedStatement.close();
			return workers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	//Lấy tuần mới nhất đã đăng ký - tuần đã xếp lich hoặc kế tuần xếp lịch (pvhuy84)
	public int getWeekRegistered() {
		String query = "SELECT MAX(worktime.week) as WeekRegistered from worktime LEFT JOIN scheduler ON scheduler.worktimeId = worktime.worktimeId";
		try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int t= rs.getInt("WeekRegistered");
				preparedStatement.close();
				return t;
			}
			else {
				preparedStatement.close();
				return 1;
			}
		} catch (SQLException e) {
			return 1;
		}
	}

	// Lấy dữ liệu thời gian rảnh (pvhuy84)
	public ArrayList<WorkTime> getFreeTime(User user) {
		ArrayList<WorkTime> freeTime = new ArrayList<>();
		try {
			String sql="SELECT * FROM worktime WHERE worktime.username = ? AND week=? ORDER BY time ASC";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setInt(2, new WorkTimeDAO().getWeekRegistered());
			ResultSet rs =preparedStatement.executeQuery();
			while(rs.next()){
				freeTime.add(new WorkTime(rs.getInt("workTimeId"), rs.getString("username"), rs.getInt("week"), rs.getInt("day"), rs.getInt("time")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return freeTime;
	}

	//Cập nhật thời gian rảnh (pvhuy84)
	public void updateFreeTime(User user, String[] freeTime, int time) {
		try {
			String sql="";
			
			//Kiểm tra tuần đã đăng ký trước đó đã được xếp lịch hay chưa
			//nếu chưa thì sửa lại thời gian rảnh
			//nếu rồi thì tạo mới thời gian rảnh cho tuần kế
			
			if(new WorkTimeDAO().getWeekRegistered() != (new SchedulerDAO().getNextWeek()-1)) {
				sql = "DELETE FROM worktime WHERE username=? AND week=? AND time=?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, user.getUsername());
				preparedStatement.setInt(2, new SchedulerDAO().getNextWeek());
				preparedStatement.setInt(3, time);
				System.out.println("wt: " + preparedStatement.toString());
				preparedStatement.execute();
			} 
			
			//Kiểm tra dữ liệu rỗng, trong trường hợp này thì user k có thời gian rảnh
			if(freeTime!=null){
				sql = "INSERT INTO worktime(username, week, day, time) VALUES(?,?,?,?)";
				int day;
				for(int i = 0; i < freeTime.length; i++) {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, user.getUsername());
					preparedStatement.setInt(2, new SchedulerDAO().getNextWeek());
					day = Integer.parseInt(freeTime[i]);
					preparedStatement.setInt(3, day);
					preparedStatement.setInt(4, time);
					preparedStatement.execute();
				}
			}
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
