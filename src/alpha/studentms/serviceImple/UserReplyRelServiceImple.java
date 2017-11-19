package alpha.studentms.serviceImple;

import java.util.List;

import alpha.studentms.dao.ReplyDAO;
import alpha.studentms.dao.UserReplyRelDAO;
import alpha.studentms.service.UserReplyRelService;

public class UserReplyRelServiceImple implements UserReplyRelService{
	
	private UserReplyRelDAO userReplyRelDAO  = new UserReplyRelDAO(); 
	private ReplyDAO replyDAO = new ReplyDAO();

	@Override
	public int addStar(String userId, String replyId) {
		// 首先查询数据库，查看该用户对该回复是否点过反对或者已经点过赞同
		// 如果点过反对，返回已经点过反对
		if (userReplyRelDAO.hasOpposed(userId, replyId)) {
			return UserReplyRelService.HAD_OPPOSED;
		// 如果点过赞同，返回已经赞同
		}else if (userReplyRelDAO.hasStared(userId, replyId)) {
			return UserReplyRelService.HAD_STARTED;
		}else{
		// 否则执行点赞操作
			// user-reply表中添加纪录
			userReplyRelDAO.addStar(replyId, userId);
			// reply star增1
			replyDAO.addReplyStar(replyId);
			return UserReplyRelService.FINISH;
		}

	}

	@Override
	public int addOppose(String userId, String replyId) {
		// 首先查询数据库，查看该用户对该回复是否点过反对或者已经点过赞同
		// 如果点过反对，返回已经点过反对
		if (userReplyRelDAO.hasOpposed(userId, replyId)) {
			return UserReplyRelService.HAD_OPPOSED;
		// 如果点过赞同，返回已经赞同
		}else if (userReplyRelDAO.hasStared(userId, replyId)) {
			return UserReplyRelService.HAD_STARTED;
		}else{
		// 否则执行点反对操作
			// user-reply表中添加纪录
			userReplyRelDAO.addOppose(replyId, userId);
			// reply star增1
			replyDAO.addReplyOppose(replyId);
			return UserReplyRelService.FINISH;
		}
		
	}

	@Override
	public List<String> getAllStaredReply(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllOpposedReply(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
