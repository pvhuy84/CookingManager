package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.bo.UserBO;

/**
 * Servlet implementation class ChangeName
 */
@WebServlet("/ChangeName")
public class ChangeName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String name= request.getParameter("newname");
		String reportChangeName="Đổi tên không thành công";
		if(!(new UserBO().changeName(user, name))) {
			reportChangeName="Đổi tên thành công";
			
//			Cập nhật thông tin user lưu trong session (pvhuy84)
			session.removeAttribute("user");
			System.out.println("username: "+user.getUsername());
			System.out.println("password: "+user.getPassword());
			
			session.setAttribute("user", new UserBO().getUser(user.getUsername()));
		}
		session.setAttribute("reportChangeName", reportChangeName);
		response.sendRedirect("Account");
		
	}

}
