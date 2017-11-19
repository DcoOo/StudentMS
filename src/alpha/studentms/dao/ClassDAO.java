package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alpha.studentms.util.JdbcUtils;

public class ClassDAO {
	private static Connection connection = JdbcUtils.getConnection();
	
	public List<String> searchAllClassId(){
		List<String> result = new ArrayList<String>();
		String sql = "SELECT * FROM t_class";
		PreparedStatement preStatement;
		
		try{
			preStatement = connection.prepareStatement(sql);
			ResultSet rs= preStatement.executeQuery();
			while(rs.next()){
				result.add(rs.getString("pk_id"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/*
     * 释放链接
     */
    public static void release(){
    	JdbcUtils.releaseConnection(connection);
    }

}
