package alpha.studentms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import alpha.studentms.bean.Post;
import alpha.studentms.service.PostService;
import alpha.studentms.serviceImple.PostServiceImple;

public class ShowPostController extends HttpServlet{
	
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
		// 从数据库中取到所有的帖子
		List<Post> list = postService.getAllPost();
		req.setAttribute("posts", list);
		List<String> nameList = new LinkedList<>();
		for(Post post : list){
			String name = postService.getNameOrNumberByUserId(post.getUser_id());
			nameList.add(name);
		}
		req.setAttribute("names", nameList);
		// 跳转到论坛主页
		req.getRequestDispatcher("/post.jsp").forward(req, resp);
		
	}

}
