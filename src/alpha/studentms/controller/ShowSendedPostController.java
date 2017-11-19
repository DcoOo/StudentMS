package alpha.studentms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Post;
import alpha.studentms.service.PostService;
import alpha.studentms.serviceImple.PostServiceImple;

public class ShowSendedPostController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private PostService postService = new PostServiceImple();
	
	/**
	 * 查看当前用户已经发的帖子
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = (String) req.getSession().getAttribute("userId");
		List<Post> posts = postService.getPostsByUserId(userId);
		req.setAttribute("sendedPosts", posts);
		req.getRequestDispatcher("/sendedposts.jsp").forward(req, resp);
	}

}
