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
import alpha.studentms.service.StudentService;
import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.PostServiceImple;
import alpha.studentms.serviceImple.ReplyServiceImple;
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;

public class ShowReplyedController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private StudentService studentService = new StudentServiceImple();
	private PostService postService = new PostServiceImple();
	private ReplyService replyService = new ReplyServiceImple();
	private TeacherService teacherService = new TeacherServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = (String) req.getSession().getAttribute("userId");
		// 根据角色来决定显示
		if (req.getSession().getAttribute("part").equals("student")) {
			// 获取该帖子所有的回复的id
			List<String> replyIdList = studentService.getReplyInCollection(userId);
			
			// 回复list
			List<Reply> replyList = new LinkedList<>();
			// 根据reply的id找到对应的Post
			LinkedList<Post> postList = new LinkedList<>();
			// 根据reply的userId找到回复的用户，取到其名字或者工号
			LinkedList<String> nameList = new LinkedList<>();
			for(String replyId : replyIdList){
				Reply reply = replyService.getReplyByReplyId(replyId); 
				Post post = postService.getPost(reply.getPost_id());
				replyList.add(reply);
				postList.add(post);
				String name = postService.getNameOrNumberByUserId(reply.getUser_id());
				nameList.add(name);
			}
			req.setAttribute("replyList", replyList);
			req.setAttribute("postList", postList);
			req.setAttribute("nameList", nameList);
		}else{
			// 获取该所有帖子的回复的id
			List<String> replyIdList = teacherService.getReplyInCollection(userId);
			// 回复list
			List<Reply> replyList = new LinkedList<>();
			// 根据reply的id找到对应的Post
			LinkedList<Post> postList = new LinkedList<>();
			// 根据reply的userId找到回复的用户，取到其名字或者工号
			LinkedList<String> nameList = new LinkedList<>();
			for(String replyId : replyIdList){
				Reply reply = replyService.getReplyByReplyId(replyId); 
				Post post = postService.getPost(reply.getPost_id());
				replyList.add(reply);
				postList.add(post);
				String name = postService.getNameOrNumberByUserId(reply.getUser_id());
				nameList.add(name);
			}
			req.setAttribute("replyList", replyList);
			req.setAttribute("postList", postList);
			req.setAttribute("nameList", nameList);
			
		}
		req.getRequestDispatcher("/reply.jsp").forward(req, resp);
	}
	

}
