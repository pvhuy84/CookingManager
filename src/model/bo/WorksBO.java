/**
 * 
 */
package model.bo;

import java.util.ArrayList;

import model.bean.User;
import model.bean.Works;
import model.dao.WorksDAO;

/**
 * @author pvhuy84
 *
 */
public class WorksBO {

	public ArrayList<Works> getListWorks(User user) {
		return new WorksDAO().getListWorks(user);
	}

}
