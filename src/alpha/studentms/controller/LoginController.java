package alpha.studentms.controller;

import java.awt.ItemSelectable;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Student;
import alpha.studentms.service.MemoService;
import alpha.studentms.serviceImple.LoginServiceImple;
import alpha.studentms.serviceImple.MemoServiceImple;
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
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		StudentServiceImple studentService = new StudentServiceImple();
		String username = (String) req.getParameter("username");
		String passwd = (String) req.getParameter("passwd");
		String role = (String)req.getParameter("role");
		System.out.println(role+"----");
		boolean isLegel = loginService.check(username, passwd);
		if (isLegel) {
			// 用户名，密码正确
			if (role.equals("student")) {
				// 学生登陆
				// 按照登陆名查询用户所有信息
				Student student = studentService.getStudentByUsername(username);
				System.out.println(student.getId());
				// 按照用户登陆名搜索学生代办事物
				// 必做事务
				List<Memo> mustMemoList = studentService.getMustMemo(student.getId());
				// 选做事务
				List<Memo> optionMemoList = studentService.getOptionMemo(student.getId());
				// 自定义事务
				List<Memo> customMemoList = studentService.getCustomMemo(student.getId());
				System.out.println(mustMemoList.get(0).getTitle());
				System.out.println(optionMemoList.get(0).getTitle());
				System.out.println(customMemoList.get(0).getTitle());
				req.setAttribute("mustMemoList", mustMemoList.get(0));
				req.setAttribute("optionMemoList", optionMemoList.get(0));
				req.setAttribute("customMemoList", customMemoList.get(0));
				// 该学生所在班级的班主任以及辅导员发的所有通知
//				resp.sendRedirect("../index.jsp");
				req.getRequestDispatcher("../index.jsp").forward(req, resp);
				
			}
		}else{
			// 用户名，密码错误
			req.setAttribute("login_flag", "0");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
