package test.alpha.studentms.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;


import alpha.studentms.util.JdbcUtils;

public class JdbcUtilsTest {

	@Test
	public void test() throws SQLException {

		Connection connection = JdbcUtils.getConnection();
		String s = "SELECT * FROM t_student";
		ResultSet set = connection.createStatement().executeQuery(s);
		assertSame(set.next(), true);

	}

}
