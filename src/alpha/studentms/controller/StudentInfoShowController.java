package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.setAttribute("userId", "joker");
		String studentID = (String) session.getAttribute("userId");
		Student student = studentService.getStudentByUsername(studentID);
		JSONObject studentJSON = new JSONObject(student);
		response.getWriter().print(studentJSON.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
