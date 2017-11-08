package test.alpha.studentms.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import alpha.studentms.bean.Assistant;
import alpha.studentms.dao.AssistantDAO;

public class AssistantDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByID() {
		AssistantDAO assistantDAO = new AssistantDAO();
		Assistant assistant = assistantDAO.findByID("123456");
		
		System.out.println(assistant.getId()+assistant.getClassOfTeacher()+assistant.getCollection()+assistant.getNumber());
		assertTrue(assistant.getNumber().equals("110"));
	}

	@Test
	public void testFindByClass() {
		AssistantDAO assistantDAO = new AssistantDAO();
		Assistant assistant = assistantDAO.findByClass("0");
		
		System.out.println(assistant.getId()+assistant.getClassOfTeacher()+assistant.getCollection()+assistant.getNumber());
		assertTrue(assistant.getNumber().equals("110"));
	}

	@Test
	public void testFindByNumber() {
		AssistantDAO assistantDAO = new AssistantDAO();
		Assistant assistant = assistantDAO.findByNumber("110");
		
		System.out.println(assistant.getId()+assistant.getClassOfTeacher()+assistant.getCollection()+assistant.getNumber());
		assertTrue(assistant.getNumber().equals("110"));
	}


	@Test
	public void testUpdateCollection() {
		AssistantDAO assistantDAO = new AssistantDAO();
		assistantDAO.updateCollection("XXXXX", "123456");
		Assistant assistant = assistantDAO.findByID("123456");
		
		System.out.println(assistant.getId()+assistant.getClassOfTeacher()+assistant.getCollection()+assistant.getNumber());
		assertTrue(assistant.getCollection().equals("XXXXX"));
	}


}
