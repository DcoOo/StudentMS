package alpha.studentms.service;

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
}
