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
import model.bo.AbilityBO;

/**
 * @author pvhuy84
 *
 */

//Controller to update task which user registered (pvhuy84)
@WebServlet("/UpdateTaskRegisterd")
public class UpdateTaskRegisterd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateTaskRegisterd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
 		String[] listTaskRegister=request.getParameterValues("task");
		new AbilityBO().updateTaskRegistered(user, listTaskRegister);
		
		response.sendRedirect("Task");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
