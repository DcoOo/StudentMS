package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.service.MemoService;
import alpha.studentms.serviceImple.MemoServiceImple;

public class TeacherDeleteMemoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MemoService memoService = new MemoServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String assistantID = (String) request.getSession().getAttribute("userId");
		
		String memoId = request.getParameter("id");
		memoService.deleteMemoById(memoId);
		
		request.getRequestDispatcher("/servlet/TeacherShowMemoController").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
