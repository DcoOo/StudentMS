package alpha.studentms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	public StudentService studentService = new StudentServiceImple();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String studentID = (String) session.getAttribute("studentID");
		float englishGrade = studentService.getEnglishGrade(studentID);
		String classInfo = studentService.getClassId(studentID);
		Map<String, String> map = new HashMap<>();
		map.put("englishGrade", englishGrade + "");
		map.put("classInfo", classInfo);
		request.setAttribute("studentClassAndGrade", map);

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
