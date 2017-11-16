package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import alpha.studentms.service.LoginService;
import alpha.studentms.serviceImple.LoginServiceImple;

public class ShowPasswdController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginService loginService = new LoginServiceImple(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = (String)req.getSession().getAttribute("username");
		String old_passwd = loginService.getPasswdByUsername(username);
		req.setAttribute("old_passwd", old_passwd);
		
		//TODO 页面跳转

	}
	

}
