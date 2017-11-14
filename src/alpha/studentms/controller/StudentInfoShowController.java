package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	public StudentService studentService = new StudentServiceImple();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String studentID = (String) request.getAttribute("studentID");
		Student student = studentService.getStudentByUsername(studentID);
		JSONObject studentJSON = new JSONObject(student);
		request.setAttribute("studentJSON", studentJSON);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
