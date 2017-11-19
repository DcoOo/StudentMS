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

import alpha.studentms.bean.ModelDocument;
import alpha.studentms.service.ModelDocumentService;
import alpha.studentms.serviceImple.ModelDocumentServiceImple;

public class TeacherShowAllModelDocumentController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModelDocumentService modelDocumentService = new ModelDocumentServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String assistantID = (String) request.getSession().getAttribute("userId");
		//参数名
		String messageid = (String)request.getParameter("");
		
		List<ModelDocument> models = new ArrayList<ModelDocument>();
		models = modelDocumentService.getModelDocumentByMessageId(messageid);
		
		JSONArray jSONArray = new JSONArray();
		Iterator<ModelDocument> iterator = models.iterator();
		while(iterator.hasNext()){
			ModelDocument modelDocument = new ModelDocument();
			modelDocument = iterator.next();
			JSONObject modelJSON = new JSONObject();
			modelJSON.put("modelDoc", modelDocument.getModelDoc());
			modelJSON.put("name", modelDocument.getName());
			modelJSON.put("address", modelDocument.getModelDoc());
			
			jSONArray.put(modelJSON);
		}
		
		request.setAttribute("registerJSON", jSONArray);
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
