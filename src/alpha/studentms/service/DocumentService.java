package alpha.studentms.service;

import alpha.studentms.bean.Document;

public interface DocumentService {

	
	/**
	 * upload document
	 */
	void studentUploadDocument(Document document);
	
	/**
	 * download document
	 */
	Document studentDownloadDocument(String path);
}
