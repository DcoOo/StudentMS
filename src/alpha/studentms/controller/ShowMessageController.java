package alpha.studentms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Message;
import alpha.studentms.service.MessageService;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.MessageServiceImple;
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = (String) req.getSession().getAttribute("userId");
		String teacherID = studentService.getTeacherId(userId);
		List<Message> classAdviserMessages = messageService.getTeacherMessage(teacherID);
		req.setAttribute("classAdviserMessages", classAdviserMessages);
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
	

}
