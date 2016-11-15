package controller;

/**
 * @author pvhuy84
 *
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.bo.WorkTimeBO;

/**
 * Servlet implementation class UpdateFreeTime
 */
@WebServlet("/UpdateFreeTime")
public class UpdateFreeTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFreeTime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String freeTime0[] = request.getParameterValues("time0");
		String freeTime1[] = request.getParameterValues("time1");
		WorkTimeBO workTimeBO = new WorkTimeBO();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		
		workTimeBO.updateFreeTime(user, freeTime0, 0);
		workTimeBO.updateFreeTime(user, freeTime1, 1);
		
		response.sendRedirect("Task");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
