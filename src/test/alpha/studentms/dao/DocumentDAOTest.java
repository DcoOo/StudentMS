package test.alpha.studentms.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import alpha.studentms.bean.Document;
import alpha.studentms.dao.DocumentDAO;

public class DocumentDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByID() {
		DocumentDAO documentDAO = new DocumentDAO();
		Document document = documentDAO.findByID("13579");
		
		System.out.println(document.getId()+document.getAddress()+document.getName()+document.getStudent()+document.getOpTime());
		assertTrue(document.getAddress().equals("www1"));
	}

	@Test
	public void testFindByStudent() {
		DocumentDAO documentDAO = new DocumentDAO();
		Document document = documentDAO.findByStudent("zhang").get(0);
		
		System.out.println(document.getId()+document.getAddress()+document.getName()+document.getStudent()+document.getOpTime());
		assertTrue(document.getAddress().equals("www1"));
	}

	@Test
	public void testFindByName() {
		DocumentDAO documentDAO = new DocumentDAO();
		Document document = documentDAO.findByName("doc1");
		
		System.out.println(document.getId()+document.getAddress()+document.getName()+document.getStudent()+document.getOpTime());
		assertTrue(document.getAddress().equals("www1"));
	}

	@Test
	public void testAdd() {
		DocumentDAO documentDAO = new DocumentDAO();
		Document document1 = new Document("24680","wang","www2","doc2");
		documentDAO.add(document1);
		
		Document document = documentDAO.findByID("24680");
		
		System.out.println(document.getId()+document.getAddress()+document.getName()+document.getStudent()+document.getOpTime());
		assertTrue(document.getAddress().equals("www2"));
	}

	@Test
	public void testDelete() {
		DocumentDAO documentDAO = new DocumentDAO();
		documentDAO.delete("34567");
		
		assertTrue(true);
	}


}
