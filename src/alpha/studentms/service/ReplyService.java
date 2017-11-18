package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Reply;

public interface ReplyService {
	
	/**
	 * 用户回帖
	 */
	void reply(String user, String postId,String content);
	
	
	/**
	 * 用户对回帖点赞
	 */
	void like(String replyId);
	
	
	/**
	 * 用户对回帖反对
	 */
	void oppose(String replyId);
	
	
	/**
	 * 用户搜索回帖
	 */
	List<Reply> searchReply(String condition);
	
	
	/**
	 * 用户删除回帖
	 */
	void deleteReply(String user,String replyId);

}
