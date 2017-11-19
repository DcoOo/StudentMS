package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Reply;

public interface ReplyService {
	
	/**
	 * 用户回帖
	 */
	void reply(String replyId, String user, String postId,String content);
	
	
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
	
	/**
	 * 点赞后指定回复的star_num自增1
	 */
	void updateReplyStarNum(String replyId);

	/**
	 * 点赞后指定回复的oppose_num自增1
	 */
	void updateReplyOpposeNum(String replyId);
	
	/**
	 * 得到指定回复的赞同数
	 */
	int getStarNum(String replyId);

	/**
	 * 得到指定回复的反对数
	 */
	int getOpposeNum(String replyId);
	
	/**
	 * 回复时，添加一条记录到被回复人的回复列表中
	 * @param postOwnerId
	 * @param replyId
	 */
	void addReplyIdToPostOwner(String postOwnerId, String replyId);
	
	
	/**
	 * 根据回复id找到回复
	 * @param replyId
	 * @return
	 */
	Reply getReplyByReplyId(String replyId);
	
	/**
	 * 删除用户collection字段中的回复id
	 */
	void deleteReplyIdInCollection(String userId, String replyId);

}
