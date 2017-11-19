package alpha.studentms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import alpha.studentms.bean.Message;
import alpha.studentms.service.MessageService;
import alpha.studentms.service.ModelDocumentService;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.MessageServiceImple;
import alpha.studentms.serviceImple.ModelDocumentServiceImple;
import alpha.studentms.serviceImple.StudentServiceImple;

public class ShowMessageController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private MessageService messageService = new MessageServiceImple();
	private StudentService studentService = new StudentServiceImple();
	private ModelDocumentService modelDocumentService = new ModelDocumentServiceImple();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = (String) req.getSession().getAttribute("userId");
		String teacherID = studentService.getTeacherId(userId);
		// 获取到班主任的所有通知，并返回到前端
		List<Message> classAdviserAndAssistantMessages = messageService.getTeacherMessage(teacherID);
		req.setAttribute("classAdviserAndAssistantMessages", classAdviserAndAssistantMessages);
		System.out.println(classAdviserAndAssistantMessages.size()+"---");
		// 根据通知id，查询到通知对应的模板文档的下载地址，返回到前端
		List<String> docList = new ArrayList<>();
		// 每一个通知都放入一个表示List<ModelDocument>的Json字符串
		JSONArray modelDocListJson;
		for (Message msg : classAdviserAndAssistantMessages){
			// 由于json串含有大量     " , ' 所以用 @ 替代    " , 用  ^ 替代 ' 
			modelDocListJson = new JSONArray(modelDocumentService.getModelDocumentByMessageId(msg.getId()));
			String json = modelDocListJson.toString().replace('\"', '@');
			json = json.replace('\'', '^');
			docList.add(json);
		}
		req.setAttribute("modelDocument", docList);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
	

}
