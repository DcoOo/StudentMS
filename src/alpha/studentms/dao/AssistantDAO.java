package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import alpha.studentms.bean.Assistant;
import alpha.studentms.util.JdbcUtils;

public class AssistantDAO {
	
	
	private static Connection connection = JdbcUtils.getConnection();
	
	//private JdbcUtils jdbcUtils = new JdbcUtils(); 
	
	
	
	/*
	 * 根据辅导员ID查询
	 */
	public Assistant findByID(String id){
		
		Assistant assistant = null;
		String sql = "SELECT * FROM t_teacher WHERE pk_id=?";
		PreparedStatement preStatement;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			//con = JdbcUtils.getConnection();
			//sql = con.prepareStatement();
			//rs = sql.executeQuery("SELECT * FROM t_teacher WHERE pk_id='"+id+"'");
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, id);
			ResultSet rs= preStatement.executeQuery();
			if(rs.next()){
				assistant = new Assistant();
				assistant.setId(rs.getString("pk_id"));
				assistant.setClassOfTeacher(rs.getString("fk_class"));
				assistant.setRoleOfTeacher(rs.getInt("role_control"));
				assistant.setNumber(rs.getString("number"));
				assistant.setCollection(rs.getString("collection"));
			}
			
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return assistant;
		
	}
	
	
	/*
	 * 根据辅导员负责班级查询
	 */
    public Assistant findByClass(String classOfTeacher){
		
        Assistant assistant = null;
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
				assistant = new Assistant();
				assistant.setId(rs.getString("pk_id"));
				assistant.setClassOfTeacher(rs.getString("fk_class"));
				assistant.setRoleOfTeacher(rs.getInt("role_control"));
				assistant.setNumber(rs.getString("number"));
				assistant.setCollection(rs.getString("collection"));
			}
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return assistant;
		
	}

    
    
    /*
	 * 根据辅导员工号查询
	 */
    public Assistant findByNumber(String number){
		
        Assistant assistant = null;
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
				assistant = new Assistant();
				assistant.setId(rs.getString("pk_id"));
				assistant.setClassOfTeacher(rs.getString("fk_class"));
				assistant.setRoleOfTeacher(rs.getInt("role_control"));
				assistant.setNumber(rs.getString("number"));
				assistant.setCollection(rs.getString("collection"));
			}
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return assistant;
		
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
	
	/**
	 * 获取所有的辅导员
	 * @return
	 */
	public List<Assistant> getAssistants(){
		List<Assistant> list = new LinkedList<>();
		String sql = "SELECT * FROM t_teacher WHERE role_control = 0";
		PreparedStatement preState;
		try {
			preState = connection.prepareStatement(sql);
			ResultSet set = preState.executeQuery();
			while (set.next()) {
				Assistant assistant = new Assistant();
				assistant = new Assistant();
				assistant.setId(set.getString("pk_id"));
				assistant.setClassOfTeacher(set.getString("fk_class"));
				assistant.setRoleOfTeacher(set.getInt("role_control"));
				assistant.setNumber(set.getString("number"));
				assistant.setCollection(set.getString("collection"));
				list.add(assistant);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
    
    
    /*
     * 释放链接
     */
    public static void release(){
    	JdbcUtils.releaseConnection(connection);
    }

}
