package alpha.studentms.service;

public interface LoginService {

	/**
	 * 验证登陆信息
	 * @param username
	 * @param passwd
	 * @return
	 */
	boolean check(String username, String passwd);
	
}
