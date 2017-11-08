package test.alpha.studentms.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import alpha.studentms.bean.Message;
import alpha.studentms.dao.MessageDAO;

/**
 * MessageDAO单元测试类
 * 
 * @author joker
 *
 */
public class MessageDAOTest {
	MessageDAO messageDAO;

	@Before
	public void init() {
		messageDAO = new MessageDAO();
	}

	@Test
	public void testInsertMessage() {
		Message message = new Message();
		message.setId("1");
		message.setTeacher("1");
		message.setTitle("Java");
		message.setContent("Java");
		messageDAO.insertMessage(message);
	}

	@Test
	public void testDeleteMessageById() {
		String id = "1";
		messageDAO.deleteMessageById(id);
	}

	@After
	public void close() {
		messageDAO.releaseConnection();
	}

}
