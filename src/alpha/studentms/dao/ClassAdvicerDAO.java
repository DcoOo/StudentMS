package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import alpha.studentms.bean.ClassAdvicer;
import alpha.studentms.util.JdbcUtils;

public class ClassAdvicerDAO {
	
	private static Connection connection = JdbcUtils.getConnection();
	
	/*
	 * 根据班主任ID查询
	 */
	public ClassAdvicer findByID(String id){
		
		ClassAdvicer classAdvicer = null;
		String sql = "SELECT * FROM t_teacher WHERE pk_id=?";
		PreparedStatement preStatement;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			//con = JdbcUtils.getConnection();
			//sql = con.createStatement();
			//rs = sql.executeQuery("SELECT * FROM t_teacher WHERE pk_id='"+id+"'");
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, id);
			ResultSet rs= preStatement.executeQuery();
			if(rs.next()){
				classAdvicer = new ClassAdvicer();
				classAdvicer.setId(rs.getString("pk_id"));
				classAdvicer.setClassOfTeacher(rs.getString("fk_class"));
				classAdvicer.setRoleOfTeacher(rs.getInt("role_control"));
				classAdvicer.setNumber(rs.getString("number"));
				classAdvicer.setCollection(rs.getString("collection"));
			}
			
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return classAdvicer;
		
	}
	
	
	/*
	 * 根据班主任负责班级查询
	 */
    public ClassAdvicer findByClass(String classOfTeacher){
		
    	ClassAdvicer classAdvicer = null;
    	String sql = "SELECT * FROM t_teacher WHERE fk_class=?";
		PreparedStatement preStatement;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			//con = JdbcUtils.getConnection();
			//sql = con.createStatement();
			//rs = sql.executeQuery("SELECT * FROM t_teacher WHERE fk_class='"+classOfTeacher+"'");
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, classOfTeacher);
			ResultSet rs= preStatement.executeQuery();
			if(rs.next()){
				classAdvicer = new ClassAdvicer();
				classAdvicer.setId(rs.getString("pk_id"));
				classAdvicer.setClassOfTeacher(rs.getString("fk_class"));
				classAdvicer.setRoleOfTeacher(rs.getInt("role_control"));
				classAdvicer.setNumber(rs.getString("number"));
				classAdvicer.setCollection(rs.getString("collection"));
			}
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return classAdvicer;
		
	}

    
    
    /*
	 * 根据班主任工号查询
	 */
    public ClassAdvicer findByNumber(String number){
		
    	ClassAdvicer classAdvicer = null;
    	String sql = "SELECT * FROM t_teacher WHERE number=?";
		PreparedStatement preStatement;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			//con = JdbcUtils.getConnection();
			//sql = con.createStatement();
			//rs = sql.executeQuery("SELECT * FROM t_teacher WHERE number="+number);
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, number);
			ResultSet rs= preStatement.executeQuery();
			if(rs.next()){
				classAdvicer = new ClassAdvicer();
				classAdvicer.setId(rs.getString("pk_id"));
				classAdvicer.setClassOfTeacher(rs.getString("fk_class"));
				classAdvicer.setRoleOfTeacher(rs.getInt("role_control"));
				classAdvicer.setNumber(rs.getString("number"));
				classAdvicer.setCollection(rs.getString("collection"));
			}
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return classAdvicer;
		
	}
    
    
    /*
	 * 修改收藏夹信息
	 */
	public void updateCollection(String collection,String id){
		String sql = "UPDATE t_teacher SET collection=? WHERE pk_id=?";
		PreparedStatement preStatement;
		try{
			//JdbcUtils jdbcUtils = new JdbcUtils();
			//con = JdbcUtils.getConnection();
			//String strSQL =  "UPDATE t_teacher SET collection='"+collection+"' WHERE pk_id='"+id+"'";
			//sql = con.createStatement();
			//sql.executeUpdate(strSQL);
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, collection);
			preStatement.setString(2, id);
			preStatement.executeUpdate();
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
    
    
    /*
     * 释放链接
     */
    public static void release(){
    	JdbcUtils.releaseConnection(connection);
    }

}
