package test.alpha.studentms.serviceImple;

import static org.junit.Assert.*;

import org.junit.Test;

import alpha.studentms.serviceImple.LoginServiceImple;

public class LoginServiceImpleTest {
	
	private LoginServiceImple l = new LoginServiceImple();
	
	@Test
	public void test_check() {
		assertTrue(l.check("aaaa", "d"));
	}

}
