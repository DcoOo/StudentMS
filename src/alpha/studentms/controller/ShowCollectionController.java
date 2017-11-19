package alpha.studentms.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import alpha.studentms.bean.Post;
import alpha.studentms.service.PostService;
import alpha.studentms.service.StudentService;
import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.PostServiceImple;
import alpha.studentms.serviceImple.StudentServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;

public class ShowCollectionController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Service
	 */
	private PostService postService = new PostServiceImple();
	private StudentService studentService = new StudentServiceImple();
	private TeacherService teacherService = new TeacherServiceImple(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 得到当前用户id
		String userId = (String) req.getSession().getAttribute("userId");
		if (req.getSession().getAttribute("part").equals("student")) {
			// 登陆角色为学生
			// 得到所有收藏的帖子
			LinkedList<Post> list = (LinkedList<Post>) studentService.getCollectPost(userId);
			System.out.println(list.size()+"ShowCollection");
			LinkedList<String> authorList = new LinkedList<>();
			req.setAttribute("collectedPosts", list);
			// 将每个帖子的作者加到request中，其键为帖子id
			for(Post post : list){
				String author = postService.getNameOrNumberByUserId(post.getUser_id());
				authorList.add(author);
			}
			req.setAttribute("authorList", authorList);
		}else{
			// 登陆角色为教职工
			// 得到所有收藏的帖子
			LinkedList<Post> list = (LinkedList<Post>) teacherService.getCollectPost(userId);
			System.out.println(list.size()+"ShowCollection");
			LinkedList<String> authorList = new LinkedList<>();
			req.setAttribute("collectedPosts", list);
			// 将每个帖子的作者加到request中，其键为帖子id
			for(Post post : list){
				String author = postService.getNameOrNumberByUserId(post.getUser_id());
				authorList.add(author);
			}
			req.setAttribute("authorList", authorList);
		}
		req.getRequestDispatcher("/collection.jsp").forward(req, resp);
	}
	

}
