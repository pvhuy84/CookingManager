package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
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
		
		//Kiểm tra khi người dùng đổi mật khẩu (pvhuy84)
		if(session.getAttribute("reportChangePassword")!=null) {
			String reportChangePassword = (String) session.getAttribute("reportChangePassword");
			session.removeAttribute("reportChangePassword");
			request.setAttribute("reportChangePassword", reportChangePassword);
		}
		
		//Kiểm tra khi người dùng đổi tên (pvhuy84)
		if(session.getAttribute("reportChangeName")!=null) {
			String reportChangeName = (String) session.getAttribute("reportChangeName");
			session.removeAttribute("reportChangeName");
			request.setAttribute("reportChangeName", reportChangeName);
		}

		// Trả lại các thông số mà người dùng đã nhập
		request.getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
