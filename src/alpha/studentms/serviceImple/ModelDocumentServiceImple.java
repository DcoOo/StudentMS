package alpha.studentms.serviceImple;

import alpha.studentms.bean.ModelDocument;
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

	@Override
	public void uploadFile(ModelDocument modelDocument) {
		modelDocumentDAO.insertModelDocument(modelDocument);
	}
}
