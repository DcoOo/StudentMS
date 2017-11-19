package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.service.PostService;
import alpha.studentms.serviceImple.PostServiceImple;

public class AddPostController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private PostService postService = new PostServiceImple();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 插到t_post表中
		String userId = (String) req.getSession().getAttribute("userId");
		String postTitle = req.getParameter("postTitle");
		String postContent = req.getParameter("postContent");
		postService.addPost(userId, postTitle, postContent);
		req.getRequestDispatcher("/servlet/showPostController").forward(req, resp);
		
	}

}
