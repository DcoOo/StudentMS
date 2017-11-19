package test.alpha.studentms.serviceImple;

import static org.junit.Assert.*;

import org.junit.Test;

import alpha.studentms.service.UserReplyRelService;
import alpha.studentms.serviceImple.UserReplyRelServiceImple;

public class UserReplyRelServiceImpleTest {

	private UserReplyRelService userReplyRelService = new UserReplyRelServiceImple();
	
	@Test
	public void test_addStar() {
		int code = userReplyRelService.addOppose("a", "3");
		assertTrue(code == UserReplyRelService.HAD_STARTED);
	}

}
