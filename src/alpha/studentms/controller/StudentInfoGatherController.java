package alpha.studentms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Student;
import alpha.studentms.service.MemoService;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.MemoServiceImple;
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
	public MemoService memoService = new MemoServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		String[] optionMemos = request.getParameterValues("optionMemo");
		List<Memo> memos = memoService.getAllCompulsoryMemos();
		String optionMemoID = "";
		Student student;
		student = StudentInfoUtils.updateStudentInfo(request);
		if(optionMemos != null){
			for (int i = 0; i < optionMemos.length; i++) {
				Memo memo = new Memo();
				optionMemoID = optionMemos[i];
				memo.setUser(userId);
				memo.setId(optionMemoID);
				studentService.insertOptionMemo(memo);
			}
		}
		for (Memo memo : memos) {
			memo.setUser(userId);
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
