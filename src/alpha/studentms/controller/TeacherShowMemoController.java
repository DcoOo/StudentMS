package alpha.studentms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Memo;
import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.TeacherServiceImple;

public class TeacherShowMemoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TeacherService teacherService = new TeacherServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = (String) request.getSession().getAttribute("userId");
		
		List<Memo> memos = new ArrayList<Memo>();
		memos = teacherService.searchAllMemoById(id);
		
		request.setAttribute("memoList", memos);
		request.getRequestDispatcher("/teacher.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
