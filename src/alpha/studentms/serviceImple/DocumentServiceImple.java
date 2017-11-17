package alpha.studentms.serviceImple;

import java.util.List;

import javax.print.Doc;

import alpha.studentms.bean.Document;
import alpha.studentms.dao.DocModelRelDAO;
import alpha.studentms.dao.DocumentDAO;
import alpha.studentms.service.DocumentService;
import alpha.studentms.util.UUIDGenerater;

/**
 * 文档服务实现类
 * 
 * @author joker
 * @see DocumentDAO
 * @see DocModelRelDAO
 */
public class DocumentServiceImple implements DocumentService {

	public DocumentDAO documentDAO = new DocumentDAO();
	public DocModelRelDAO docModelRelDAO = new DocModelRelDAO();

	@Override
	public void studentUploadDocument(Document document, String docId, String modelId) {
		documentDAO.add(document);
		docModelRelDAO.addRel(UUIDGenerater.getUUID(), docId, modelId);
	}

	@Override
	public Document studentDownloadDocument(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> getDocumentsByModelDocId(String modelId) {
		List<Document> documents;
		documents = documentDAO.getDocumentsByModelDocId(modelId);
		return documents;
	}

}
