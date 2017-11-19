package alpha.studentms.service;

import java.util.List;

public interface UserReplyRelService {
	
	public int FINISH = 0;
	public int HAD_STARTED = 1;
	public int HAD_OPPOSED = 2;
	
	/**
	 * 用户对回复点赞
	 * @param userId
	 * @param replyId
	 */
	int addStar(String userId, String replyId);
	
	/**
	 * 用户对回复反对
	 * @param userId
	 * @param replyId
	 */
	int addOppose(String userId, String replyId);
	
	/**
	 * 获取所有该用户点过赞的回复
	 * @param userId
	 */
	List<String> getAllStaredReply(String userId);
	
	/**
	 * 获取所有该用户反对的回复
	 * @param userId
	 */
	List<String> getAllOpposedReply(String userId);

}
