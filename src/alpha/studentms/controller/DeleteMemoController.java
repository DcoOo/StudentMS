package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import alpha.studentms.service.MemoService;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.MemoServiceImple;
import alpha.studentms.serviceImple.StudentServiceImple;

public class DeleteMemoController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private StudentService studentService = new StudentServiceImple();
	private MemoService memoService = new MemoServiceImple(); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 用户id
		String user_id = (String) req.getSession().getAttribute("userId");
		// 获取待删除Memo的id
		String memo_id = (String)req.getParameter("id");
		// Memo类型
		int type = Integer.parseInt(req.getParameter("type"));
		switch (type) {
		case 0:
			// 0表示删除选做任务和必做任务
			memoService.deleteMemoByMemoId(memo_id, user_id, MemoService.MUST_AND_OPTION_MEMO_CODE);
			break;
		case 1:
			// 1表示删除自定义任务
			break;
		}
		req.getRequestDispatcher("/index.jsp").forward(req, resp);

	}

}
