package alpha.studentms.service;

public interface LoginService {

	/**
	 * 验证登陆信息
	 * @param username
	 * @param passwd
	 * @return
	 */
	boolean check(String username, String passwd);
	
	/**
	 * 更新密码
	 * @param new_passwd
	 * @param username
	 */
	void update(String new_passwd, String username);
	
	/**
	 * 根据用户名获取密码
	 * @param username
	 */
	String getPasswdByUsername(String username);
	
}
