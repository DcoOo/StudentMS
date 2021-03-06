package alpha.studentms.serviceImple;

import alpha.studentms.dao.LoginDAO;
import alpha.studentms.service.LoginService;
import alpha.studentms.util.EncryptUtils;

public class LoginServiceImple implements LoginService{
	
	/**
	 * DAO
	 */
	private LoginDAO loginDao;
	
	/**
	 * constructor
	 */
	public LoginServiceImple() {
		loginDao = new LoginDAO();
	}

	@Override
	public boolean check(String username, String passwd) {
		String db_passwd = loginDao.select_by_username(username);
		if (db_passwd == null) {
			// 用户名不存在
			return false;
		}
		
		if (!passwd.equals(db_passwd)) {
			// 密码错误
			return false;
		}
		
		if (passwd.equals(db_passwd))
			return true;
		return false;
	}

	@Override
	public void update(String new_passwd, String username) {
		loginDao.update(username, new_passwd);
	}
	
	@Override
	public String getPasswdByUsername(String username) {
		return loginDao.select_by_username(username);
	}
	
}
