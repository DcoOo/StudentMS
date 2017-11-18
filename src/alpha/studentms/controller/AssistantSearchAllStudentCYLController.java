package alpha.studentms.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import alpha.studentms.bean.Assistant;
import alpha.studentms.bean.Student;
import alpha.studentms.service.StudentService;
import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;

public class AssistantSearchAllStudentCYLController extends HttpServlet{

	public StudentService studentService = new StudentServiceImple();
	public TeacherService teacherService = new TeacherServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String assistantID = (String) request.getSession().getAttribute("userId");
		Assistant assistant = teacherService.selectById(assistantID);
		String whClass = assistant.getClassOfTeacher();
		List<Student> result = teacherService.searchAllClass(whClass);
		
		JSONArray jSONArray = new JSONArray();
		Iterator<Student> iterator = result.iterator();
		while(iterator.hasNext()){
			Student tempStudent = new Student();
			tempStudent = iterator.next();
			JSONObject studentJSON = new JSONObject();
			studentJSON.put("name", tempStudent.getName());
			if(tempStudent.getIs_cyl() == Student.IS_CYL){
				studentJSON.put("isCYL", "是");
			}
			else{
				studentJSON.put("isCYL", "否");
			}
			jSONArray.put(studentJSON);
		}
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.getWriter().print(jSONArray.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
