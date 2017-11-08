package test.alpha.studentms.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import alpha.studentms.bean.ClassAdvicer;
import alpha.studentms.dao.ClassAdvicerDAO;

public class ClassAdvicerDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByID() {
		ClassAdvicerDAO assistantDAO = new ClassAdvicerDAO();
		ClassAdvicer assistant = assistantDAO.findByID("654321");
		
		System.out.println(assistant.getId()+assistant.getClassOfTeacher()+assistant.getCollection()+assistant.getNumber());
		assertTrue(assistant.getNumber().equals("111"));
	}

	@Test
	public void testFindByClass() {
		ClassAdvicerDAO assistantDAO = new ClassAdvicerDAO();
		ClassAdvicer assistant = assistantDAO.findByClass("12");
		
		System.out.println(assistant.getId()+assistant.getClassOfTeacher()+assistant.getCollection()+assistant.getNumber());
		assertTrue(assistant.getNumber().equals("111"));
	}

	@Test
	public void testFindByNumber() {
		ClassAdvicerDAO assistantDAO = new ClassAdvicerDAO();
		ClassAdvicer assistant = assistantDAO.findByNumber("111");
		
		System.out.println(assistant.getId()+assistant.getClassOfTeacher()+assistant.getCollection()+assistant.getNumber());
		assertTrue(assistant.getNumber().equals("111"));
	}


	@Test
	public void testUpdateCollection() {
		ClassAdvicerDAO assistantDAO = new ClassAdvicerDAO();
		assistantDAO.updateCollection("QQQQQQQ", "654321");
		ClassAdvicer assistant = assistantDAO.findByID("654321");
		
		System.out.println(assistant.getId()+assistant.getClassOfTeacher()+assistant.getCollection()+assistant.getNumber());
		assertTrue(assistant.getCollection().equals("QQQQQQQ"));
	}

}
