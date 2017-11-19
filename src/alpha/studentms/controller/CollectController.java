package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.service.StudentService;
import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;

public class CollectController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StudentService studentService = new StudentServiceImple();
	private TeacherService teacherService = new TeacherServiceImple();
	
	public static int HAS_COLLECTED = 0;
	public static int DIDNOT_COLLECTED = 1;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 采用ajax形式访问
		// 获取到用户id
		String userId = (String) req.getSession().getAttribute("userId");
		// 获取到所收集的帖子的id
		String postId = req.getParameter("postId");
		// 检查是否已经收藏过，如果收藏过则返回HAS_COLLECTED
		if (req.getSession().getAttribute("part").equals("student")){
			// 检查学生是否已经收藏
			if (studentService.addCollectPost(userId, postId)) {
				// 未收藏过该帖子
				// 以经添加到数据库中
				resp.getWriter().print(DIDNOT_COLLECTED);
			}else{
				// 已经收藏过该帖子
				resp.getWriter().println(HAS_COLLECTED);
			}
			
		}else{
			// 教职工进行收藏
			// 检查是否已经收藏
			if (teacherService.addCollectPost(userId, postId)) {
				// 未收藏过该帖子
				// 以经添加到数据库中
				resp.getWriter().print(DIDNOT_COLLECTED);
			}else{
				resp.getWriter().println(HAS_COLLECTED);
			}
		}

		// 更新collect内容
		// 更新用户的collect字段
		// 不跳转
	}
	
	

}
