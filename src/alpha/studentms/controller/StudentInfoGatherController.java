package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Student;
import alpha.studentms.service.StudentService;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StudentService studentService = new StudentServiceImple();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String[] optionMemos = request.getParameterValues("optionMemo");
		String optionMemoID = "";
		Student student;
		student = StudentInfoUtils.updateStudentInfo(request);
		for (int i = 0; i < optionMemos.length; i++) {
			Memo memo = new Memo();
			optionMemoID = optionMemos[i];
			memo.setUser(student.getId_num());
			memo.setId(optionMemoID);
			studentService.insertOptionMemo(memo);
		}
		//表示学生已经注册
		student.setRegister(Student.IS_REIGSTER);
		studentService.gatherAndUpdateInfomation(student);
		
		request.getRequestDispatcher("/servlet/showmemocontroller").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
