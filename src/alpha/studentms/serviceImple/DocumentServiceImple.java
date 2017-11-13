package alpha.studentms.serviceImple;

import alpha.studentms.bean.Document;
import alpha.studentms.dao.DocModelRelDAO;
import alpha.studentms.dao.DocumentDAO;
import alpha.studentms.service.DocumentService;
import alpha.studentms.util.UUIDGenerater;

public class DocumentServiceImple implements DocumentService{

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

}
