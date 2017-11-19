package alpha.studentms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import alpha.studentms.bean.Document;
import alpha.studentms.bean.ModelDocument;
import alpha.studentms.service.DocumentService;
import alpha.studentms.service.MessageService;
import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.DocumentServiceImple;
import alpha.studentms.serviceImple.MessageServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;

/**
 * 教师文档的下载
 * 
 * @author joker
 * @see TeacherService
 * @see TeacherServiceImple
 * @see MessageService
 * @see MessageServiceImple
 * @see DocumentService
 * @see DocumentServiceImple
 */
public class TeacherDocumentRecycleController extends HttpServlet {

	public TeacherService teacherService = new TeacherServiceImple();
	public MessageService messageService = new MessageServiceImple();
	public DocumentService documentService = new DocumentServiceImple();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		List<ModelDocument> modelDocuments;
		List<String> json = new ArrayList<>();
		String messageId = (String) request.getParameter("id");
		modelDocuments = messageService.getModelDocumentByMessageId(messageId);
		for (int j = 0; j < modelDocuments.size(); j++) {
			String modelId = modelDocuments.get(j).getModelDoc();
			List<Document> documents = documentService.getDocumentsByModelDocId(modelId);
			for (int k = 0; k < documents.size(); k++) {
				json.add(new JSONObject(documents.get(k)).toString());
			}
		}
		response.getWriter().print(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
