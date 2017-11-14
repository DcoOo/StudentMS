package alpha.studentms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Student;
import alpha.studentms.serviceImple.LoginServiceImple;
import alpha.studentms.serviceImple.StudentServiceImple;

public class LoginController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginServiceImple loginService = new LoginServiceImple(); 
	private StudentServiceImple studentService = new StudentServiceImple();
//	private MemoServiceImple MemoService = new MemoServiceImple();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentServiceImple studentService = new StudentServiceImple();
		String username = (String) req.getParameter("username");
		String passwd = (String) req.getParameter("passwd");
		String role = (String)req.getParameter("role");
		boolean isLegel = loginService.check(username, passwd);
		if (isLegel) {
			// 用户名，密码正确
			if (role.equals("student")) {
				req.getSession().setAttribute("userId", username);
				// 学生登陆
				// 按照登陆名查询用户所有信息
				Student student = studentService.getStudentByUsername(username);
				// 按照用户登陆名搜索学生代办事物
				// 必做事务
				List<Memo> mustMemoList = studentService.getMustMemo(student.getId());
				// 选做事务
				List<Memo> optionMemoList = studentService.getOptionMemo(student.getId());
				// 自定义事务
				List<Memo> customMemoList = studentService.getCustomMemo(student.getId());
				req.setAttribute("mustMemoList", mustMemoList);
				req.setAttribute("optionMemoList", optionMemoList);
				req.setAttribute("customMemoList", customMemoList);
				// 该学生所在班级的班主任以及辅导员发的所有通知
//				resp.sendRedirect("../index.jsp");
				this.getServletContext().getRequestDispatcher("/message.html").forward(req, resp);
			}
		}else{
			// 用户名，密码错误
			req.setAttribute("login_flag", "0");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
