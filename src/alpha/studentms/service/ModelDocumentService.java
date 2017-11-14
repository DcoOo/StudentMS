package alpha.studentms.service;

import java.util.List;

import alpha.studentms.bean.ModelDocument;

/**
 * 模板文档服务接口
 * 
 * @author joker
 * @see ModelDocument
 */
public interface ModelDocumentService {

	void uploadFile(ModelDocument modelDocument);
	
	ModelDocument searchModelDocumentByID(String modelDocID);
	
	void deleteModelDocumentByID(String id);
	
	/**
	 * 根据通知id获取到该通知所包含的所有模板文档
	 * @param messageid
	 * @return
	 */
	List<ModelDocument> getModelDocumentByMessageId(String messageid);
}
