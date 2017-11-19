package alpha.studentms.controller;

import java.io.IOException;
import java.util.ArrayList;
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

public class TeacherSearchAllStudentsRegisterController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StudentService studentService = new StudentServiceImple();
	public TeacherService teacherService = new TeacherServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String assistantID = (String) request.getSession().getAttribute("userId");
		int role = (int)request.getSession().getAttribute("role");
		//记得改回来
		Assistant assistant = teacherService.selectById(assistantID);
		String whClass = assistant.getClassOfTeacher();
		
        List<Student> result = new ArrayList<Student>();
		
		if(role == 0){
			result = teacherService.searchAllStudnet();
		}
		else{
			result = teacherService.searchAllClass(whClass);
		}
		
		JSONArray jSONArray = new JSONArray();
		Iterator<Student> iterator = result.iterator();
		while(iterator.hasNext()){
			Student tempStudent = new Student();
			tempStudent = iterator.next();
			JSONObject studentJSON = new JSONObject();
			studentJSON.put("name", tempStudent.getName());
			if(tempStudent.getRegister() == 1){
				studentJSON.put("isCheck", "是");
			}
			else{
				studentJSON.put("isCheck", "否");
			}
			jSONArray.put(studentJSON);
		}
		//System.out.println(jSONArray.toString());
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.getWriter().append(jSONArray.toString());
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
