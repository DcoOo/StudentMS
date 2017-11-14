package alpha.studentms.serviceImple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import alpha.studentms.bean.Message;
import alpha.studentms.dao.MessageDAO;
import alpha.studentms.service.MessageService;

public class MessageServiceImple implements MessageService {
	
	private MessageDAO messageDAO = new MessageDAO();

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
		
		return resultSet;
	}
	
	@Override
	public List<Message> getClassMessage(String class_id) {
		List<Message> msgs= new LinkedList<>();
//		messageDAO.g
		return msgs;
	}

}
