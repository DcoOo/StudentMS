package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Student;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.util.StudentInfoUtils;

/**
 * 学生信息修改
 * 
 * @author joker
 * @see StudentService
 * @see StudentServiceImple
 * @see Student
 */
public class StudentInfoUpdateController extends HttpServlet {

	public StudentService studentService = new StudentServiceImple();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student;
		student = StudentInfoUtils.updateStudentInfo(request);
		studentService.gatherAndUpdateInfomation(student);

		// TODO 更改映射
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
