package alpha.studentms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Memo;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.StudentServiceImple;

public class ShowMemoController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private StudentService studentService = new StudentServiceImple();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 显示用户的所有事务，并返回
		// 从session中获取用户id
		String id = (String) req.getSession().getAttribute("userId");
		// 必做事务
		List<Memo> mustMemoList = studentService.getMustMemo(id);
		// 选做事务
		List<Memo> optionMemoList = studentService.getOptionMemo(id);
		// 自定义事务
		List<Memo> customMemoList = studentService.getCustomMemo(id);
		req.setAttribute("mustMemoList", mustMemoList);
		req.setAttribute("optionMemoList", optionMemoList);
		req.setAttribute("customMemoList", customMemoList);
		// 该学生所在班级的班主任以及辅导员发的所有通知
//		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		req.getRequestDispatcher("/servlet/showmessagecontroller").forward(req, resp);
	}


}
