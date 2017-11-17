package alpha.studentms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import alpha.studentms.bean.Document;
import alpha.studentms.bean.Message;
import alpha.studentms.bean.ModelDocument;
import alpha.studentms.service.DocumentService;
import alpha.studentms.service.MessageService;
import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.DocumentServiceImple;
import alpha.studentms.serviceImple.MessageServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;

public class TeacherMessageRecycleController extends HttpServlet{
	
	public TeacherService teacherService = new TeacherServiceImple();
	public MessageService messageService = new MessageServiceImple();
	public DocumentService documentService = new DocumentServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		//TODO 调试
		userId = "1";
		List<Message> list = messageService.getTeacherMessage(userId);
		List<String> json = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			json.add(new JSONObject(list.get(i)).toString());
		}
		System.out.println(json);
		response.getWriter().print(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
