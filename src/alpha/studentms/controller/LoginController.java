package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Student;
import alpha.studentms.service.LoginService;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.LoginServiceImple;
import alpha.studentms.serviceImple.StudentServiceImple;

public class LoginController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginService loginService = new LoginServiceImple(); 
	private StudentService studentService = new StudentServiceImple();
//	private MemoServiceImple MemoService = new MemoServiceImple();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = (String) req.getParameter("username");
		String passwd = (String) req.getParameter("passwd");
		String role = (String)req.getParameter("role");
		boolean isLegel = loginService.check(username, passwd);
		if (isLegel) {
			// 用户名，密码正确
			if (role.equals("student")) {
				// 学生登陆
				// 用户id放入session
				Student student = studentService.getStudentByUsername(username);
				req.getSession().setAttribute("userId", student.getId());
				req.getSession().setAttribute("username", username);
				req.getSession().setAttribute("classId", student.getClass_id());
				// 跳转到学生个人中心
				req.getRequestDispatcher("/servlet/showmemocontroller").forward(req, resp);
			}
		}else{
			// 用户名，密码错误
			req.setAttribute("login_flag", "0");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
