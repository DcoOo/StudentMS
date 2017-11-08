package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import alpha.studentms.bean.Assistant;
import alpha.studentms.util.JdbcUtils;

public class AssistantDAO {
	
	private static Connection con;
	private Statement sql;
	private ResultSet rs;
	
	//private JdbcUtils jdbcUtils = new JdbcUtils(); 
	
	
	
	/*
	 * 根据辅导员ID查询
	 */
	public Assistant findByID(String id){
		
		Assistant assistant = null;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			con = JdbcUtils.getConnection();
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM t_teacher WHERE pk_id='"+id+"'");
			if(rs.next()){
				assistant = new Assistant();
				assistant.setId(rs.getString("pk_id"));
				assistant.setClassOfTeacher(rs.getString("fk_class"));
				assistant.setRoleOfTeacher(rs.getInt("role_control"));
				assistant.setNumber(rs.getString("number"));
				assistant.setCollection(rs.getString("collection"));
			}
			
			//con.close();
		}catch(Exception e){
			
		}
		
		return assistant;
		
	}
	
	
	/*
	 * 根据辅导员负责班级查询
	 */
    public Assistant findByClass(String classOfTeacher){
		
        Assistant assistant = null;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			con = JdbcUtils.getConnection();
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM t_teacher WHERE fk_class='"+classOfTeacher+"'");
			if(rs.next()){
				assistant = new Assistant();
				assistant.setId(rs.getString("pk_id"));
				assistant.setClassOfTeacher(rs.getString("fk_class"));
				assistant.setRoleOfTeacher(rs.getInt("role_control"));
				assistant.setNumber(rs.getString("number"));
				assistant.setCollection(rs.getString("collection"));
			}
			//con.close();
		}catch(Exception e){
			
		}
		
		return assistant;
		
	}

    
    
    /*
	 * 根据辅导员工号查询
	 */
    public Assistant findByNumber(String number){
		
        Assistant assistant = null;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			con = JdbcUtils.getConnection();
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM t_teacher WHERE number="+number);
			if(rs.next()){
				assistant = new Assistant();
				assistant.setId(rs.getString("pk_id"));
				assistant.setClassOfTeacher(rs.getString("fk_class"));
				assistant.setRoleOfTeacher(rs.getInt("role_control"));
				assistant.setNumber(rs.getString("number"));
				assistant.setCollection(rs.getString("collection"));
			}
			//con.close();
		}catch(Exception e){
			
		}
		
		return assistant;
		
	}
    
	
	
	
	/*
	 * 修改收藏夹信息
	 */
	public void updateCollection(String collection,String id){
		try{
			//JdbcUtils jdbcUtils = new JdbcUtils();
			con = JdbcUtils.getConnection();
			String strSQL =  "UPDATE t_teacher SET collection='"+collection+"' WHERE pk_id='"+id+"'";
			sql = con.createStatement();
			sql.executeUpdate(strSQL);
			//con.close();
		}catch(Exception e){
			
		}
	}
    
    
    /*
     * 释放链接
     */
    public static void release(){
    	JdbcUtils.releaseConnection(con);
    }

}
