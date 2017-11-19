package alpha.studentms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alpha.studentms.bean.Message;
import alpha.studentms.bean.ModelDocument;
import alpha.studentms.service.ModelDocumentService;
import alpha.studentms.service.TeacherService;
import alpha.studentms.serviceImple.ModelDocumentServiceImple;
import alpha.studentms.serviceImple.TeacherServiceImple;
import alpha.studentms.util.UUIDGenerater;
import alpha.studentms.util.UploadFileUtils;

/**
 * 老师上传通知
 * 
 * @author joker
 * @see ModelDocumentService
 * @see ModelDocumentServiceImple
 * @see TeacherService
 * @see TeacherServiceImple
 */
public class TeacherUploadMessageController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ModelDocumentService modelDocumentService = new ModelDocumentServiceImple();
	public TeacherService teacherService = new TeacherServiceImple();
	public ModelDocument modelDocument = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String messageTitle;
		String messageContext;
		String id = (String) session.getAttribute("userId");
		String messageID = UUIDGenerater.getUUID();

		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
		List<Map<String, String>> mapList = new ArrayList<>();
		mapList = UploadFileUtils.fileUpload(request, tempPath, savePath, id);
		System.out.println(mapList);
		messageTitle = mapList.get(0).get("title");
		messageContext = mapList.get(0).get("content");
		Message message = new Message();
		message.setId(messageID);
		message.setTeacher(id);
		message.setTitle(messageTitle);
		message.setContent(messageContext);
		
		List<ModelDocument> list = new ArrayList<>();
		if ("1".equals(mapList.get(0).get("flag"))) {

			for (Map<String, String> map : mapList) {
				modelDocument = new ModelDocument();
				modelDocument.setName(map.get("saveFileName"));
				modelDocument.setTeacher(id);
				modelDocument.setModelDoc(UUIDGenerater.getUUID());
				modelDocument.setMessage(messageID);
				modelDocument.setAddress(map.get("realSavePath").replace("\\", "/"));
				list.add(modelDocument);
			}
		}
				
		if (list.isEmpty()) {
			teacherService.releaseMessage(message, modelDocument);
		} else {
			teacherService.releaseMessage(message, list.get(0));
			for (int i = 1; i < list.size(); i++) {
				teacherService.releaseMessage(null, modelDocument);				
			}
		}
		// TODO 路径处理
		request.getRequestDispatcher("/servlet/TeacherShowMemoController").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
