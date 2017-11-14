package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Student;
import alpha.studentms.dao.StudentDAO;
import alpha.studentms.service.DocumentService;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.DocumentServiceImple;
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.util.StudentInfoUtils;

/**
 * 学生信息收集
 * 
 * @author joker
 * @see StudentService
 * @see StudentServiceImple
 * @see Student
 * @see Memo
 */
public class StudentInfoGatherController extends HttpServlet {

	public StudentService studentService = new StudentServiceImple();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO ID怎么来？
		String optionMemoID = "";
		Student student;
		Memo memo = new Memo();
		student = StudentInfoUtils.updateStudentInfo(request);
		memo.setUser(student.getId_num());
		memo.setId(optionMemoID);
		studentService.gatherAndUpdateInfomation(student);
		studentService.insertOptionMemo(memo);
		// TODO 更改映射
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}