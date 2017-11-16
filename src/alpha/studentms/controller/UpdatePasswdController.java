package alpha.studentms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alpha.studentms.service.LoginService;
import alpha.studentms.serviceImple.LoginServiceImple;

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
		System.out.println(new_passwd+"update");
		System.out.println(username+"update passwd username");
		
		loginService.update(new_passwd, username);
		// 更新session中信息
		req.getSession().setAttribute("passwd", new_passwd);
		req.getRequestDispatcher("/servlet/showmemocontroller").forward(req, resp);
		
	}

}
