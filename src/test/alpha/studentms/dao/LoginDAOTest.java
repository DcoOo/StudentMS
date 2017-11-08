package test.alpha.studentms.dao;

import org.junit.Test;

import alpha.studentms.dao.LoginDAO;

public class LoginDAOTest {
	
	LoginDAO loginDao = new LoginDAO();

	@Test
	public void test_insert() {
		loginDao.insert("adfasd", "aaa");

	}

	@Test
	public void test_update() {
		loginDao.update("aaaa", "ddd");
	}

	@Test
	public void test_select_by_username() {
		System.out.println(loginDao.select_by_username("aaaa"));
	}

}
