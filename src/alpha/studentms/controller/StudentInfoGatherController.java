package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alpha.studentms.bean.Student;
import alpha.studentms.dao.StudentDAO;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.StudentServiceImple;

public class StudentInfoGatherController extends HttpServlet{

	public StudentService studentService = new StudentServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student;
		HttpSession session = request.getSession();
		String studentID = (String)session.getAttribute("studentID");
		student = studentService.getStudentByUsername(studentID);
		String studentNation = (String)request.getAttribute("studentNation");
		String studentName = (String)request.getAttribute("studentName");
		String sex = (String)request.getAttribute("studentSex");
		int studentSex = Integer.valueOf(sex).intValue();
		String age = (String)request.getAttribute("studentAge");
		int studentAge = Integer.valueOf(age).intValue();
		String studentEmail = (String)request.getAttribute("studentEmail");
		String studentWeChat = (String)request.getAttribute("studentWeChat");
		String studentPhone = (String)request.getAttribute("studentPhone");
		String studentQQ = (String)request.getAttribute("studentQQ");
		String isCYL = (String)request.getAttribute("studentCYL");
		int studentCYL = Integer.valueOf(isCYL).intValue();
		String studentAddress = (String)request.getAttribute("studentAddress");
		
		student.setNation(studentNation);
		student.setAge(studentAge);
		student.setSex(studentSex);
		student.setName(studentName);
		student.setAddress(studentAddress);
		student.setEmail(studentEmail);
		student.setId_num(studentID);
		student.setPhone(studentPhone);
		student.setWechat(studentWeChat);
		student.setQq(studentQQ);
		student.setIs_cyl(studentCYL);
		studentService.gatherAndUpdateInfomation(student);
		
		//TODO 更改映射
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
