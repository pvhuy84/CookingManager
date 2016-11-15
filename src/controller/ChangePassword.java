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
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String renewpassword = request.getParameter("renewpassword");
		
		String reportChangePassword="Đổi mật khẩu không thành công";
		
		if(!newpassword.equals(renewpassword)) {
			reportChangePassword = "Mật khẩu mới không trùng nhau";
		} else if((new UserBO().getUser(user.getUsername(), oldpassword))==null) {
			reportChangePassword = "Mật khẩu cũ không đúng";
		} else {
			if(!(new UserBO().changePassword(user.getUsername(), newpassword))) {
				reportChangePassword="Đổi mật khẩu thành công";
				
				//Cập nhật thông tin user lưu trong session (pvhuy84)
				session.removeAttribute("user");
				session.setAttribute("user", new UserBO().getUser(user.getUsername(), newpassword));
			} else {
				reportChangePassword="Đổi mật khẩu không thành công";
			}
		}
		session.setAttribute("reportChangePassword", reportChangePassword);
		response.sendRedirect("Account");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
