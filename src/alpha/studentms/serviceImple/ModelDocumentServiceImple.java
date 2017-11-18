package alpha.studentms.serviceImple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import alpha.studentms.bean.Document;
import alpha.studentms.bean.ModelDocument;
import alpha.studentms.dao.DocModelRelDAO;
import alpha.studentms.dao.DocumentDAO;
import alpha.studentms.dao.ModelDocumentDAO;
import alpha.studentms.service.ModelDocumentService;

/**
 * 模板文档服务的实现类
 * 
 * @author joker
 * @see ModelDocumentDAO
 */
public class ModelDocumentServiceImple implements ModelDocumentService {

	public ModelDocumentDAO modelDocumentDAO = new ModelDocumentDAO();
	private DocModelRelDAO docModelRelDAO = new DocModelRelDAO();
	private DocumentDAO documentDAO = new DocumentDAO();

	@Override
	public void uploadFile(ModelDocument modelDocument) {
		modelDocumentDAO.insertModelDocument(modelDocument);
	}

	@Override
	public ModelDocument searchModelDocumentByID(String modelDocID) {
		ModelDocument modelDocument;
		modelDocument = modelDocumentDAO.searchModelDocById(modelDocID);
		return modelDocument;
	}

	@Override
	public void deleteModelDocumentByID(String id) {
		modelDocumentDAO.deleteModelDocumentById(id);		
	}
	
	@Override
	public List<ModelDocument> getModelDocumentByMessageId(String messageid) {
		return modelDocumentDAO.searchModelDocumentByMessageID(messageid);
	}

	@Override
	public List<Document> getDocumentByModelDocId(String modelDocId) {
		// TODO Auto-generated method stub
		List<Document> documents = new ArrayList<Document>();
		
		List<String> documentId = new ArrayList<String>();
		documentId = docModelRelDAO.findDocByModel(modelDocId);
		
		Iterator<String> iterator = documentId.iterator();
		while(iterator.hasNext()){
			Document tempDocument = new Document();
			tempDocument = documentDAO.findByID(iterator.next());
			documents.add(tempDocument);
		}
		
		return documents;
	}
}
