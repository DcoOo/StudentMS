package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.service.PostService;
import alpha.studentms.serviceImple.PostServiceImple;

public class DeletePostController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private static PostService postService = new PostServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 删帖，学生能删除自己的，老师能删除所有帖子
		String userId = (String) req.getSession().getAttribute("userId");
		// 获取帖子id
		String postId = req.getParameter("postId");
		// 删帖的Id 
		postService.deletePost(userId, postId);
		req.getRequestDispatcher("/servlet/showPostController").forward(req, resp);
	}
	
	

}
