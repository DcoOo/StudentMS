package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Post;

public interface PostService {
	
	/**
	 * 用户发帖
	 */
	void addPost(String user,String title,String content);
	
	/**
	 * 用户删帖
	 * 注意判断user的身份（学生只能删除自己的帖子，教师可以删除所有帖子）
	 */
	void deletePost(String user,String postId);
	
	/**
	 * 用户搜贴
	 */
	List<Post> searchPost(String condition);
	
	
	
}
