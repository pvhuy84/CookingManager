package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.bean.UserTask;
import model.bo.SchedulerBO;
import model.bo.UserBO;

/**
 * Servlet implementation class Statistic
 */
@WebServlet("/Statistic")
public class Statistic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBO userBO = new UserBO();
		SchedulerBO schedulerBO = new SchedulerBO();
		
		//Trả về dữ liệu thống kê (pvhuy84)
		ArrayList<User> users = userBO.getListUser();
		ArrayList<UserTask> userTasks2= schedulerBO.getListTaskAmount();
		request.setAttribute("users", users);
		request.setAttribute("userTasks2", userTasks2);
		
		// Trả lại các thông số mà người dùng đã nhập (pvhuy84)
		request.getRequestDispatcher("/WEB-INF/statistic.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
