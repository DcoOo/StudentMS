package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.ClassAdvicer;
import alpha.studentms.bean.Student;
import alpha.studentms.service.LoginService;
import alpha.studentms.service.MemoService;
import alpha.studentms.service.StudentService;
import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.LoginServiceImple;
import alpha.studentms.serviceImple.MemoServiceImple;
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;
import alpha.studentms.util.EncryptUtils;

public class LoginController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginService loginService = new LoginServiceImple();
	private StudentService studentService = new StudentServiceImple();
	private MemoService memoService = new MemoServiceImple();
	private TeacherService teacherService = new TeacherServiceImple();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String username = (String) req.getParameter("username");
		String passwd = (String) req.getParameter("passwd");
		String role = (String) req.getParameter("role");
		String encryptPassword = EncryptUtils.encoderByMd5(passwd);
		boolean isLegel = loginService.check(username, encryptPassword);
		if (isLegel) {
			// 用户名，密码正确
			if (role.equals("student")) {
				// 学生登陆
				// 用户id放入session
				Student student = studentService.getStudentByUsername(username);
				if (student == null) {
					req.setAttribute("login_flag", "0");
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				} else {
					req.getSession().setAttribute("userId", student.getId());
					req.getSession().setAttribute("username", username);
					req.getSession().setAttribute("classId", student.getClass_id());
					req.getSession().setAttribute("passwd", encryptPassword);
					// 根据是否已经注册决定跳转到信息采集或者直接进入个人中心
					if (student.getRegister() == Student.IS_REIGSTER) {
						// 已经注册
						// 跳转到学生个人中心
						req.getRequestDispatcher("/servlet/showmemocontroller").forward(req, resp);
					} else {
						// 未注册,则跳转到信息采集页面
						// 将所有的选座事务查询，并提交
						req.setAttribute("optionMemos", memoService.getAllOptionMemos());
						req.getRequestDispatcher("/message.jsp").forward(req, resp);
					}
				}
			} else {
				// 教师登陆
				ClassAdvicer classAdvicer = teacherService.selectByNum(username);
				if(classAdvicer != null){
					int roleControl = classAdvicer.getRoleOfTeacher();
					if ( roleControl == 1) {
						req.getSession().setAttribute("userId", classAdvicer.getId());
						req.getSession().setAttribute("role", 1);
						req.getRequestDispatcher("/servlet/TeacherShowMemoController").forward(req, resp);
					} else if (roleControl == 0) {
						req.getSession().setAttribute("userId", classAdvicer.getId());
						req.getSession().setAttribute("role", 0);
						req.getRequestDispatcher("/servlet/TeacherShowMemoController").forward(req, resp);
					} 
				}else {
					req.setAttribute("login_flag", "0");
					req.getRequestDispatcher("/login.jsp").forward(req, resp);
				}
				
			}
		}else{
			// 用户名或密码错误
			req.setAttribute("login_flag", "0");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
