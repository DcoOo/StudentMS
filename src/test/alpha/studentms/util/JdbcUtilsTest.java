package test.alpha.studentms.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.apache.tomcat.util.security.MD5Encoder;
import org.json.JSONObject;
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
	
	@Test
	public void test1(){
		JSONObject object = new JSONObject(new HashMap<String, String>());
		Map<String, Object> map = object.toMap();
		List<String> collect = new LinkedList<>();
		List<String> delete = new LinkedList<>();
		map.put("collect", collect);
		map.put("delete", delete);
		object = new JSONObject(map);
		System.out.println(object.toString());

	}
	
	@Test
	public void testMd5(){
		
		System.out.println(MD5Encoder.encode("dddd".getBytes()));
		
	}

}
