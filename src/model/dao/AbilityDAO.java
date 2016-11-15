/**
 * 
 */
package model.dao;

import model.bean.User;

/**
 * @author pvhuy84
 *
 */
public class AbilityDAO extends DatabaseFactory {

	//Cập nhật dữ liệu đăng ký công việc
	public void updateTaskRegistered(User user, String[] listTaskRegister) {
		try {
			String sql="";
			
			//Xóa dữ liệu đăng ký trước đó
			sql = "DELETE FROM ability WHERE ability.username=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.execute();
			
			//Chèn dữ liệu mới
			sql = "INSERT INTO ability VALUES(?,?)";
			for(int i = 0; i < listTaskRegister.length; i++) {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, user.getUsername());
				preparedStatement.setString(2, listTaskRegister[i]);
				preparedStatement.execute();
			}
			
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
