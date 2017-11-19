package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Post;
import alpha.studentms.bean.Reply;

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
	
	/**
	 * 获取所有的帖子，返回到前端
	 */
	List<Post> getAllPost();
	
	/**
	 * 根据id获取Post
	 * @return 
	 */
	Post getPost(String id);
	
	/**
	 * 根据postid获取所有属于该post的回复
	 * @param id
	 * @return
	 */
	List<Reply> getReplysByPostId(String id);
	
	/**
	 * 根据帖子的id，找到发帖人的姓名或者是工号
	 * @param id
	 * @return
	 */
	String getNameOrNumberByUserId(String id);

	/**
	 * 根据用户id获得该用户发的所有贴子
	 * @param userId
	 * @return
	 */
	List<Post> getPostsByUserId(String userId);
	
	
	
}
