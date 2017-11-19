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

public class SearchPostController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PostService postService = new PostServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// 获取用户输入的关键字
		String keyWords = req.getParameter("searchText");
		if (keyWords.equals("") || keyWords == null) {
			// 输入为空，显示所有帖子，跳转到ShowPostController
			req.getRequestDispatcher("/servlet/showPostController").forward(req, resp);
		}else{
			List<Post> list = postService.searchPost(keyWords);
			req.setAttribute("posts", list);
			req.getRequestDispatcher("/post.jsp").forward(req, resp);
		}
	}
	

}
