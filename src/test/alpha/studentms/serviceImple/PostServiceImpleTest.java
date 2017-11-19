package test.alpha.studentms.serviceImple;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import alpha.studentms.bean.Post;
import alpha.studentms.service.PostService;
import alpha.studentms.serviceImple.PostServiceImple;

public class PostServiceImpleTest {
	
	private PostService postService = new PostServiceImple();

	@Test
	public void test_getAllPost() {
		List<Post> posts = postService.getAllPost();
		System.out.println(posts.size());
	}
	
	@Test
	public void test_search(){
		List<Post> list = postService.searchPost("title1");
		System.out.println(list.size());
	}
	
}
