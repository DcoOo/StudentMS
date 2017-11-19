package alpha.studentms.controller;

import java.io.IOException;
import java.util.LinkedList;
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

public class ShowDetailController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private PostService postService = new PostServiceImple(); 
	private ReplyService replyService = new ReplyServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取到被点击的帖子的id
		String postId = req.getParameter("postId");
		// 搜索到该贴子以及其回复，返回到前端
		Post post = postService.getPost(postId);
		// 根据帖子id找到发帖人拿到其名字或者老师的工号
		String name = postService.getNameOrNumberByUserId(post.getUser_id());
		req.setAttribute("postOwnerName", name);
		// 可能是老师发的也可能是学生发的帖子，所以需要根据id做出判断
		List<Reply> replyList = postService.getReplysByPostId(post.getId());
		// 根据reply找到回复人的name或number
		List<String> nameList = new LinkedList<>();
		for(Reply reply : replyList){
			nameList.add(postService.getNameOrNumberByUserId(reply.getUser_id()));
		}
		req.setAttribute("post", post);
		req.setAttribute("replys", replyList);
		req.setAttribute("names", nameList);
		// TODO 跳转到显示帖子详细信息页面
		req.getRequestDispatcher("/detail.jsp").forward(req, resp);
	}
	

}
