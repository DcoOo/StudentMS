package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.Message;
import alpha.studentms.bean.ModelDocument;

public interface MessageService {

	/**
	 * show messages
	 */
	List<Message> getAllMesasge();
	
	/**
	 * show class message
	 */
	List<Message> getTeacherMessage(String teacher_id);
	
	/**
	 * 根据班级ID获取通知
	 */
	List<Message> getClassMessage(String class_id);
	
	List<ModelDocument> getModelDocumentByMessageId(String messageId);
}
