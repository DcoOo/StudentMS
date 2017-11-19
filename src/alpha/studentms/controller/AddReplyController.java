package alpha.studentms.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.Post;
import alpha.studentms.service.PostService;
import alpha.studentms.service.ReplyService;
import alpha.studentms.serviceImple.PostServiceImple;
import alpha.studentms.serviceImple.ReplyServiceImple;
import alpha.studentms.util.UUIDGenerater;


public class AddReplyController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private ReplyService replyService = new ReplyServiceImple();
	private PostService postService = new PostServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// 获取回复人id
		String userId = (String) req.getSession().getAttribute("userId");
		// 获取回复的帖子id
		String postId = req.getParameter("postId");
		// 获取回复的内容
		String replyContent = req.getParameter("replyContent");
		// 数据库中插入一条回复
		String replyId = UUIDGenerater.getUUID();
		replyService.reply(replyId, userId, postId, replyContent);
		// TODO 发帖人的被回复列表中添加一条记录
		// 根据postID得到发帖人id
		Post post = postService.getPost(postId);
		replyService.addReplyIdToPostOwner(post.getUser_id(), replyId);
		// 返回到该帖子的详情页面
		req.getRequestDispatcher(String.format("/servlet/showDetailController?postId=%s", postId)).forward(req, resp);
	}
	
}
