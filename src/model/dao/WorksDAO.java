/**
 * 
 */
package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.bean.User;
import model.bean.Works;

/**
 * @author pvhuy84
 *
 */ 
public class WorksDAO  extends DatabaseFactory {

	public ArrayList<Works> getListWorks(User user) {
		ArrayList<Works> listWorks = new ArrayList<>();
		try {
			String sql ="SELECT  task.taskName, worktime.day, worktime.time FROM worktime JOIN scheduler ON worktime.worktimeId=scheduler.worktimeId JOIN task ON scheduler.taskId= task.taskId WHERE worktime.username=? ORDER BY worktime.time ASC";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			System.out.println("works:"+preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				listWorks.add(new Works(rs.getString(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listWorks;
	}

}

