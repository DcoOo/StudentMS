package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.service.LoginService;
import alpha.studentms.serviceImple.LoginServiceImple;
import alpha.studentms.util.EncryptUtils;

public class UpdatePasswdController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginService loginService = new LoginServiceImple(); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = (String) req.getSession().getAttribute("username");
		String new_passwd = req.getParameter("newpasswd");
		loginService.update(EncryptUtils.encoderByMd5(new_passwd), username);
		// 更新session中信息
		req.getSession().setAttribute("passwd", EncryptUtils.encoderByMd5(new_passwd));
		req.getRequestDispatcher("/servlet/showmemocontroller").forward(req, resp);
		
	}


}
