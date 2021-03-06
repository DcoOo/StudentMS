package alpha.studentms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alpha.studentms.bean.Document;
import alpha.studentms.service.DocumentService;
import alpha.studentms.serviceImple.DocumentServiceImple;
import alpha.studentms.util.UUIDGenerater;
import alpha.studentms.util.UploadFileUtils;

/**
 * 学生上传
 * 
 * @author joker
 * @see DocumentService
 * @see DocumentServiceImple
 */
public class StudentUploadHandleController extends HttpServlet {

	public DocumentService documentService = new DocumentServiceImple();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
		String studentID = (String) session.getAttribute("userId");
		String modelDocID;
		Document document = new Document();
		List<Map<String, String>> mapList = new ArrayList<>();
		mapList = UploadFileUtils.fileUpload(request, tempPath, savePath, studentID);
		for (Map<String, String> map : mapList) {
			String docID = UUIDGenerater.getUUID();
			modelDocID = map.get("modelDocID");
			document.setName(map.get("saveFileName"));
			document.setStudent(studentID);
			document.setAddress(map.get("realSavePath"));
			document.setId(docID);
			documentService.studentUploadDocument(document, docID, modelDocID);
		}
		request.getRequestDispatcher("/servlet/showmemocontroller").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
