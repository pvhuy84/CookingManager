/**
 * 
 */
package model.bo;

import model.bean.User;
import model.dao.AbilityDAO;

/**
 * @author pvhuy84
 *
 */
public class AbilityBO {

	public void updateTaskRegistered(User user, String[] listTaskRegister) {
		new AbilityDAO().updateTaskRegistered(user, listTaskRegister);
	}

}
