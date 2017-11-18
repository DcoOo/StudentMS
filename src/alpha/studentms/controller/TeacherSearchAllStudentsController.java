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

public class TeacherSearchAllStudentsController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentService studentService = new StudentServiceImple();
	public TeacherServiceImple teacherService = new TeacherServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String assistantID = (String) request.getSession().getAttribute("userId");
		//记得改过来id
		Assistant assistant = teacherService.selectById(assistantID);
		String whClass = assistant.getClassOfTeacher();
		List<Student> result = teacherService.searchAllClass(whClass);
		
		
		
		JSONArray jSONArray = new JSONArray();
		Iterator<Student> iterator = result.iterator();
		while(iterator.hasNext()){
			Student tempStudent = new Student();
			tempStudent = iterator.next();
			
			JSONObject studentJSON = new JSONObject();
			studentJSON.put("id", tempStudent.getId());
			studentJSON.put("class_id", tempStudent.getClass_id());
			studentJSON.put("id_num", tempStudent.getId_num());
			studentJSON.put("nation", tempStudent.getNation());
			studentJSON.put("number", tempStudent.getNumber());
			studentJSON.put("name", tempStudent.getName());
			if(tempStudent.getSex() == 0){
				studentJSON.put("sex", "男");
			}
			else{
				studentJSON.put("sex", "女");
			}
			studentJSON.put("age", tempStudent.getAge());
			studentJSON.put("email", tempStudent.getEmail());
			studentJSON.put("wechat", tempStudent.getWechat());
			studentJSON.put("qq", tempStudent.getQq());
			studentJSON.put("phone", tempStudent.getPhone());
			studentJSON.put("address", tempStudent.getAddress());
			if(tempStudent.getIs_cyl() == 0){
				studentJSON.put("is_cyl","是" );
			}
			else{
				studentJSON.put("is_cyl","否" );
			}
			if(tempStudent.getRegister() == 1){
				studentJSON.put("register","是" );
			}
			else{
				studentJSON.put("register", "否");
			}
			studentJSON.put("english_grade", tempStudent.getEnglish_grade());
			studentJSON.put("other_info", tempStudent.getOther_info());
			
			jSONArray.put(studentJSON);
		}
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.getWriter().append(jSONArray.toString());
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
