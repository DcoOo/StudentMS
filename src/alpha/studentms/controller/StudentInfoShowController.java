package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import alpha.studentms.bean.Student;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.StudentServiceImple;

/**
 * 学生信息展示
 * 
 * @author joker
 * @see StudentService
 * @see StudentServiceImple
 * @see Student
 * @see JSONObject
 */
public class StudentInfoShowController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StudentService studentService = new StudentServiceImple();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		Student student = studentService.getStudentByUsername(userName);
		JSONObject studentJSON = new JSONObject(student);
		response.getWriter().write(studentJSON.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
