package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Memo;
import alpha.studentms.bean.Message;

public interface MemoService {
	
	public static final int CUSTOM_MEMO_CODE = 0;
	public static final int MUST_AND_OPTION_MEMO_CODE = 1;
	public static final int TEACHER_LOG = 2;
	
	/**
	 * 显示所有该用户的备忘录
	 */
	List<Message> getMemoeByUserId(String userId);
	
	/**
	 * 插入一条备忘录消息， 包括教师日志、必做任务、选做任务、自定义任务
	 * @param userId
	 * 				用户id
	 * @param type
	 * 				删除类型 MemoService.CUSTOM_MEMO_CODE\OPTION_MEMO_CODE\MUST_MEMO_CODE\TEACHER_LOG
	 */
	void deleteMemoByMemoId(String memoId, String userId, int type);
	
	/**
	 * 用户插入一条备忘录
	 */
	void addMemoByUserId(String userId, int type, Memo memo);
	
	/**
	 * 获取所有的选做任务
	 */
	List<Memo> getAllOptionMemos();
	
	

}
