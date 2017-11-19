package alpha.studentms.serviceImple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import alpha.studentms.bean.Assistant;
import alpha.studentms.bean.Message;
import alpha.studentms.dao.MessageDAO;
import alpha.studentms.service.MessageService;
import alpha.studentms.service.TeacherService;

public class MessageServiceImple implements MessageService {
	
	private MessageDAO messageDAO = new MessageDAO();
	private TeacherService teacherService = new TeacherServiceImple();

	@Override
	public List<Message> getAllMesasge() {
		// TODO Auto-generated method stub
		
		List<Message> resultSet = new ArrayList<Message>();
		
		resultSet = messageDAO.getAllMessage();
		
		return resultSet;
	}

	@Override
	public List<Message> getTeacherMessage(String teacher_id) {
		// TODO Auto-generated method stub
		
		List<Message> resultSet = new ArrayList<Message>();
		
		resultSet = messageDAO.getMessage(teacher_id);
		List<Assistant> assistants = teacherService.getAllAssistants();
		for(Assistant assistant : assistants){
			List<Message> assistantMessageList = messageDAO.getMessage(assistant.getId());
			resultSet.addAll(assistantMessageList);
		}
		return resultSet;
	}
	
	@Override
	public List<Message> getClassMessage(String class_id) {
		List<Message> msgs= new LinkedList<>();
//		messageDAO.g
		return msgs;
	}

}
