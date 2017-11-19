package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import alpha.studentms.bean.Memo;
import alpha.studentms.service.MemoService;
import alpha.studentms.serviceImple.MemoServiceImple;

public class TeacherShowOneMemoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MemoService memoService = new MemoServiceImple();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = (String) request.getSession().getAttribute("userId");
		
		String memoId = request.getParameter("memoid");
		
		Memo memo = new Memo();
		memo = memoService.getOneMemoById(memoId);
		
		JSONObject memoJSON = new JSONObject();
		memoJSON.put("id", memo.getId());
		memoJSON.put("title", memo.getTitle());
		memoJSON.put("content", memo.getContent());
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.getWriter().append(memoJSON.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
