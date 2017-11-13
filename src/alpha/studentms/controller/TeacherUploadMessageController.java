package alpha.studentms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.bean.ModelDocument;
import alpha.studentms.dao.ModelDocumentDAO;
import alpha.studentms.service.ModelDocumentService;
import alpha.studentms.serviceImple.ModelDocumentServiceImple;
import alpha.studentms.util.UUIDGenerater;
import alpha.studentms.util.UploadFileUtils;

public class TeacherUploadMessageController extends HttpServlet {

	public ModelDocumentService modelDocumentService = new ModelDocumentServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
		
		
		
		
		
		
		
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/upload");
		String id = (String)request.getAttribute("id");
		ModelDocument modelDocument = new ModelDocument();
		Map<String, String> map = new HashMap<>();
		map = UploadFileUtils.fileUpload(request, tempPath, savePath, id);
		modelDocument.setName(map.get("saveFileName"));
		modelDocument.setTeacher(id);
		modelDocument.setModelDoc(UUIDGenerater.getUUID());
		String messageID = UUIDGenerater.getUUID();
		modelDocument.setMessage(messageID);
		modelDocumentService.uploadFile(modelDocument);
		//TODO 通知的处理
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
