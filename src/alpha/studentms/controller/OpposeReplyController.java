package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.service.ReplyService;
import alpha.studentms.service.UserReplyRelService;
import alpha.studentms.serviceImple.ReplyServiceImple;
import alpha.studentms.serviceImple.UserReplyRelServiceImple;

public class OpposeReplyController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ReplyService replyService = new ReplyServiceImple();
	private UserReplyRelService userReplyRelService = new UserReplyRelServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 通过ajax访问
		// 得到用户id及被点反对的id
		String id = (String) req.getSession().getAttribute("userId");
		String replyId = req.getParameter("replyId");
		// 获取点赞后的状态码
		int code = userReplyRelService.addOppose(id, replyId);
		switch (code) {
		case UserReplyRelService.FINISH:
			// 表示点反对成功，向reply表中的oppose_num增1
			userReplyRelService.addOppose(id, replyId);
			// 重新查询回复，返回当前回复的oppose_num数量
			int num = replyService.getOpposeNum(replyId);
			resp.getWriter().print(UserReplyRelService.FINISH+"#"+num);
			break;
		case UserReplyRelService.HAD_STARTED:
			// 表示该用户对该回复已经赞同过，无法进行反对，
			resp.getWriter().print(UserReplyRelService.HAD_STARTED);
			break;
		case UserReplyRelService.HAD_OPPOSED:
			// 表示该用户对该回复已经反对，无法再次点击反对
			resp.getWriter().print(UserReplyRelService.HAD_OPPOSED);
			break;
		default:
			break;
		}
		
	}
	

}
