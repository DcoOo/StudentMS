package alpha.studentms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Post;
import alpha.studentms.bean.Reply;
import alpha.studentms.service.PostService;
import alpha.studentms.service.ReplyService;
import alpha.studentms.serviceImple.PostServiceImple;
import alpha.studentms.serviceImple.ReplyServiceImple;

public class DeletePostController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private static PostService postService = new PostServiceImple();
	private static ReplyService replyService = new ReplyServiceImple();
	
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
		// 删帖的Id，同时删除帖子的回复 
		postService.deletePost(userId, postId);
		// 用户的collection字段中收藏的帖子、以及对应的回复都应该删除
		// 删除所有用户collection中收藏的帖子id
		// 找到所有回复，将帖子的拥有者collection字段中的回复id删除
		List<Reply> replys = postService.getReplysByPostId(postId);
		req.getRequestDispatcher("/servlet/showPostController").forward(req, resp);
	}
	
	

}
