package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Message;

public interface MessageService {

	/**
	 * show messages
	 */
	List<Message> getAllMesasge();
	
	/**
	 * show class message
	 */
	List<Message> getTeacherMessage(String teacher_id);
	
}
