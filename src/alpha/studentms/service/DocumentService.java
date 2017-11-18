package alpha.studentms.service;

import java.util.List;

import javax.print.Doc;

import alpha.studentms.bean.Document;

public interface DocumentService {

	
	/**
	 * upload document
	 */
	void studentUploadDocument(Document document, String docID, String modelID);
	
	/**
	 * download document
	 */
	Document studentDownloadDocument(String path);
	
	List<Document> getDocumentsByModelDocId(String modelId);
}
