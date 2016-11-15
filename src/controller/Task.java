package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.bean.UserTask;
import model.bean.WorkTime;
import model.bean.Worker;
import model.bean.Works;
import model.bo.SchedulerBO;
import model.bo.TaskBO;
import model.bo.UserBO;
import model.bo.WorkTimeBO;
import model.bo.WorksBO;

/**
 * Servlet implementation class Task
 */
@WebServlet("/Task")
public class Task extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Task() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		// Kiểm tra quyền hạn.
		HttpSession session = request.getSession();
		User user= (User) session.getAttribute("user");
		
		//Lấy dữ liệu công việc trong tuần (pvhuy84)
		ArrayList<Works> listWorks = new ArrayList<>();
		listWorks = new WorksBO().getListWorks(user);
		request.setAttribute("listWorks", listWorks);
		
		//Lấy dữ liệu danh sách tất cả công việc (pvhuy84)
		TaskBO taskBO = new TaskBO();
		ArrayList<model.bean.Task> listTask = new ArrayList<>();
		listTask = taskBO.getTaskList();
		request.setAttribute("listTask", listTask);
		
		//Lấy dữ liệu công việc đã đăng ký (pvhuy84)
		ArrayList<model.bean.Task> listTaskRegistered = new ArrayList<>();
		listTaskRegistered = taskBO.getListTaskRegistered(user);
		request.setAttribute("listTaskRegistered", listTaskRegistered);
		
		//Lấy dữ liệu thời gian rảnh (pvhuy84)
		ArrayList<WorkTime> freeTime = new ArrayList<>();
		freeTime = new WorkTimeBO().getFreeTime(user);
		request.setAttribute("freeTime", freeTime);
		
		// Trả lại các thông số mà người dùng đã nhập (pvhuy84)
		request.getRequestDispatcher("/WEB-INF/task.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
