package alpha.studentms.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import alpha.studentms.bean.Student;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.StudentServiceImple;

/**
 * 学生信息的更新
 * 
 * @author joker
 * @see StudentService
 * @see StudentServiceImple
 */
public class StudentInfoUtils {

	public static Student updateStudentInfo(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StudentService studentService = new StudentServiceImple();
		Student student;
		HttpSession session = request.getSession();
		String studentID = (String) session.getAttribute("userId");
		student = studentService.getStudentByUsername(studentID);
		String studentNation = (String) request.getParameter("studentNation");
		String sex = (String) request.getParameter("studentSex");
		int studentSex = "male".equals(sex)?Student.MAN:Student.WOMAN; 
		String age = (String) request.getParameter("studentAge");
		int studentAge = Integer.valueOf(age).intValue();
		String studentEmail = (String) request.getParameter("studentEmail");
		String studentWeChat = (String) request.getParameter("studentWechat");
		String studentPhone = (String) request.getParameter("studentPhone");
		String studentQQ = (String) request.getParameter("studentQQ");
		String isCYL = (String) request.getParameter("studentCYL");
		int studentCYL = "yes".equals(isCYL)?Student.IS_CYL:Student.NOT_CYL;
		String studentAddress = (String) request.getParameter("studentAddress");

		student.setNation(studentNation);
		student.setAge(studentAge);
		student.setSex(studentSex);
		student.setAddress(studentAddress);
		student.setEmail(studentEmail);
		student.setId_num(studentID);
		student.setPhone(studentPhone);
		student.setWechat(studentWeChat);
		student.setQq(studentQQ);
		student.setIs_cyl(studentCYL);
		return student;
	}
}
