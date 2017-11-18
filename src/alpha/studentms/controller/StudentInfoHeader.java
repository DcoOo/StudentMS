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
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;

public class StudentInfoHeader extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StudentService studentService = new StudentServiceImple();
	public TeacherServiceImple teacherService = new TeacherServiceImple();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String assistantID = (String) request.getSession().getAttribute("");
		
		Assistant assistant = teacherService.selectById(assistantID);
		String whClass = assistant.getClassOfTeacher();
		List<Student> result = teacherService.searchAllClass(whClass);
		JSONArray jSONArray = new JSONArray();
			
		Iterator<Student> iterator = result.iterator();
		if(iterator.hasNext()){
			
		}	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
