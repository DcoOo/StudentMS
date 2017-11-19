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

import alpha.studentms.serviceImple.TeacherServiceImple;

public class ShowAllClassesController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TeacherServiceImple teacherService = new TeacherServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> classes = new ArrayList<String>();
		classes = teacherService.searchAllClassId();
		
		JSONArray jSONArray = new JSONArray();
		Iterator<String> iterator = classes.iterator();
		while(iterator.hasNext()){
			JSONObject classJSON = new JSONObject();
			classJSON.put("class1", iterator.next());
			jSONArray.put(classJSON);
		}
		
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.getWriter().append(jSONArray.toString());
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
