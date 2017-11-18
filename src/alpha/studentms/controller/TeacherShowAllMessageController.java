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

import alpha.studentms.bean.Message;
import alpha.studentms.service.MessageService;
import alpha.studentms.serviceImple.MessageServiceImple;

public class TeacherShowAllMessageController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MessageService messageService = new MessageServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String assistantID = (String) request.getSession().getAttribute("userId");
		
		List<Message> messages = new ArrayList<Message>();
		messages = messageService.getTeacherMessage(assistantID);
		
		JSONArray jSONArray = new JSONArray();
		Iterator<Message> iterator = messages.iterator();
		while(iterator.hasNext()){
			Message message = new Message();
			message = iterator.next();
			JSONObject messageJSON = new JSONObject();
			messageJSON.put("id", message.getId());
			messageJSON.put("teacher", message.getTeacher());
			messageJSON.put("title", message.getTitle());
			messageJSON.put("content", message.getContent());
			messageJSON.put("optime", message.getOptime());
			
			jSONArray.put(messageJSON);
		}
		
		request.setAttribute("messagesJSON", jSONArray);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
