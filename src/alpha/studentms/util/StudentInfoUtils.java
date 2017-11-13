package alpha.studentms.util;

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
		StudentService studentService = new StudentServiceImple();
		Student student;
		HttpSession session = request.getSession();
		String studentID = (String) session.getAttribute("studentID");
		student = studentService.getStudentByUsername(studentID);
		String studentNation = (String) request.getAttribute("studentNation");
		String studentName = (String) request.getAttribute("studentName");
		String sex = (String) request.getAttribute("studentSex");
		int studentSex = Integer.valueOf(sex).intValue();
		String age = (String) request.getAttribute("studentAge");
		int studentAge = Integer.valueOf(age).intValue();
		String studentEmail = (String) request.getAttribute("studentEmail");
		String studentWeChat = (String) request.getAttribute("studentWeChat");
		String studentPhone = (String) request.getAttribute("studentPhone");
		String studentQQ = (String) request.getAttribute("studentQQ");
		String isCYL = (String) request.getAttribute("studentCYL");
		int studentCYL = Integer.valueOf(isCYL).intValue();
		String studentAddress = (String) request.getAttribute("studentAddress");

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

		return student;
	}
}
