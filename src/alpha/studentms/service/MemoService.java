package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Message;

public interface MemoService {
	
	/**
	 * 显示所有该用户的备忘录
	 */
	List<Message> getMemoeByUserId(String userId);
	
	/**
	 * 用户删除一条备忘录
	 */
	void deleteMemoByUserId(String userId);
	
	/**
	 * 用户插入一条备忘录
	 */
	void addMemoByUserId(String userId);

}
