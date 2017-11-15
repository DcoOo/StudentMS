package alpha.studentms.controller;

import java.io.IOException;
import java.util.HashMap;
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String messageTitle = (String) request.getParameter("title");
		String messageContext = (String) request.getParameter("content");
		String id = (String) session.getAttribute("userId");
		String hasModelDoc = (String) request.getAttribute("modelDoc");
		String messageID = UUIDGenerater.getUUID();
		ModelDocument modelDocument = null;
		Message message = new Message();
		message.setId(messageID);
		message.setTeacher(id);
		message.setTitle(messageTitle);
		message.setContent(messageContext);

		if ("modelDoc".equals(hasModelDoc)) {
			// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
			String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
			String tempPath = this.getServletContext().getRealPath("/WEB-INF/upload");
			modelDocument = new ModelDocument();
			Map<String, String> map = new HashMap<>();
			map = UploadFileUtils.fileUpload(request, tempPath, savePath, id);
			modelDocument.setName(map.get("saveFileName"));
			modelDocument.setTeacher(id);
			modelDocument.setModelDoc(UUIDGenerater.getUUID());
			modelDocument.setMessage(messageID);
			modelDocumentService.uploadFile(modelDocument);
			modelDocumentService.uploadFile(modelDocument);
		}
		teacherService.releaseMessage(message, modelDocument);
		
		//TODO 路径处理
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
