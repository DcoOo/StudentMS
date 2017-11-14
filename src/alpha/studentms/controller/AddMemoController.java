package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import alpha.studentms.bean.Memo;
import alpha.studentms.service.StudentService;
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.util.UUIDGenerater;

public class AddMemoController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StudentService studentService = new StudentServiceImple();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取用户id
		String user_id = (String)req.getSession().getAttribute("userId");
		// 获取待添加备忘录信息
		String[] titles = req.getParameterValues("newTask");
		System.out.println(titles.length+"***");
		Memo memo;
		for (String title : titles) {
			memo = new Memo();
			memo.setUser(user_id);
			memo.setTitle(title);
			memo.setId(UUIDGenerater.getUUID());
			studentService.insertCustomMemo(memo);
		}
		req.getRequestDispatcher("/servlet/showmemocontroller").forward(req, resp);
	}
	
}
