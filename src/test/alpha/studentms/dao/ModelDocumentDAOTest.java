package test.alpha.studentms.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import alpha.studentms.bean.ModelDocument;
import alpha.studentms.dao.ModelDocumentDAO;

/**
 * ModelDocument单元测试类
 * 
 * @author joker
 *
 */
public class ModelDocumentDAOTest {
	ModelDocumentDAO modelDocumentDAO;

	@Before
	public void init() {
		modelDocumentDAO = new ModelDocumentDAO();
	}

	@Test
	public void testInsertModelDocument() {
		ModelDocument modelDocument = new ModelDocument();
		modelDocument.setModelDoc("1");
		modelDocument.setTeacher("2");
		modelDocument.setMessage("2");
		modelDocument.setName("奖学金通知");
		modelDocumentDAO.insertModelDocument(modelDocument);
	}

	@Test
	public void testDeleteModelDocumentById() {
		String id = "1";
		modelDocumentDAO.deleteModelDocumentById(id);
	}

	@Test
	public void testSearchModelDocById() {
		String id = "1";
		ModelDocument modelDocument = modelDocumentDAO.searchModelDocById(id);
		System.out.println(modelDocument);
	}

	@After
	public void close() {
		modelDocumentDAO.realseConenction();
	}
}
