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

import alpha.studentms.bean.Document;
import alpha.studentms.service.ModelDocumentService;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.ModelDocumentServiceImple;
import alpha.studentms.serviceImple.StudentServiceImple;

public class TeacherShowAllDocumentController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModelDocumentService modelDocumentService = new ModelDocumentServiceImple();
	private StudentService studentService = new StudentServiceImple();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String assistantID = (String) request.getSession().getAttribute("userId");
		//参数名
		String modelDocId = (String)request.getParameter("");
		
		List<Document> document = new ArrayList<Document>();
		document = modelDocumentService.getDocumentByModelDocId(modelDocId);
		
		JSONArray jSONArray = new JSONArray();
		Iterator<Document> iterator = document.iterator();
		while(iterator.hasNext()){
			Document tempDocument = new Document();
			tempDocument = iterator.next();
			JSONObject modelJSON = new JSONObject();
			modelJSON.put("student", studentService.getStudentById(tempDocument.getStudent()).getName());
			modelJSON.put("address", tempDocument.getAddress());
			modelJSON.put("name", tempDocument.getName());
			modelJSON.put("opTime", tempDocument.getOpTime());
			
			jSONArray.put(modelJSON);
		}
		
		request.setAttribute("registerJSON", jSONArray);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
