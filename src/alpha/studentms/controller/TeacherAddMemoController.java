package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.TeacherServiceImple;

public class TeacherAddMemoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TeacherService teacherService = new TeacherServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String assistantID = (String) request.getSession().getAttribute("userId");
		
		request.setCharacterEncoding("utf-8");
		// 获取待添加备忘录信息
		String title = request.getParameter("memoTitle");
		String content = request.getParameter("content");
		
		teacherService.addMemo(assistantID, title, content);
		
		request.getRequestDispatcher("/servlet/TeacherShowMemoController").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
