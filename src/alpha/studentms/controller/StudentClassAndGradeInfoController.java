package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alpha.studentms.bean.Student;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.StudentServiceImple;

/**
 * 学生查询分班情况和英语成绩
 * 
 * @author joker
 * @see StudentService
 * @see StudentServiceImple
 */
public class StudentClassAndGradeInfoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StudentService studentService = new StudentServiceImple();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Student student;
		HttpSession session = request.getSession();
		String studentID = (String) session.getAttribute("userId");
		String username = (String)session.getAttribute("username");
		student = studentService.getStudentByUsername(username);
		float englishGrade = studentService.getEnglishGrade(studentID);
		String classInfo = studentService.getClassId(studentID);
		String studentName = student.getName();
		String json = "{\"englishGrade\":" + englishGrade + ",\"classInfo\":\"" + classInfo + "\",\"studentName\":\"" + studentName + "\"}";
		response.getWriter().print(json);		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
