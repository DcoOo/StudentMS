package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import alpha.studentms.bean.Document;
import alpha.studentms.util.JdbcUtils;

public class DocumentDAO {
	
	private static Connection con;
	private Statement sql;
	private ResultSet rs;
	
	
	/*
	 * 根据文档ID查询
	 */
	public Document findByID(String id){
		
		Document document = null;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			con = JdbcUtils.getConnection();
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM t_doc WHERE pk_doc='"+id+"'");
			if(rs.next()){
				document = new Document();
				document.setId(rs.getString("pk_doc"));
				document.setStudent(rs.getString("fk_student"));
				document.setAddress(rs.getString("address"));
				document.setName(rs.getString("name"));
				document.setOpTime(rs.getTimestamp("optime"));
			}
			
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return document;
		
	}
	
	
	
	/*
	 * 根据上传文档的学生ID查询
	 */
	public List<Document> findByStudent(String student){
		
		List<Document> listDocument = new ArrayList<Document>();
		Document document = null;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			con = JdbcUtils.getConnection();
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM t_doc WHERE fk_student='"+student+"'");
			
			
			//这里应该使用循环，返回一个list。暂且先不管。！！！！！！！！！！！
			//！！！！！！！！！！！！！！！！！！！
			
			
			while(rs.next()){
				document = new Document();
				document.setId(rs.getString("pk_doc"));
				document.setStudent(rs.getString("fk_student"));
				document.setAddress(rs.getString("address"));
				document.setName(rs.getString("name"));
				document.setOpTime(rs.getTimestamp("optime"));
				listDocument.add(document);
			}
			/*
			if(rs.next()){
				document = new Document();
				document.setId(rs.getString("pk_id"));
				document.setStudent(rs.getString("fk_student"));
				document.setAddress(rs.getString("address"));
				document.setName(rs.getString("name"));
				document.setOpTime(rs.getTimestamp("optime"));
			}*/
			
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return listDocument;
		
	}
	
	
	
	/*
	 * 根据文档ID查询
	 */
	public Document findByName(String name){
		
		Document document = null;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			con = JdbcUtils.getConnection();
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM t_doc WHERE name='"+name+"'");
			if(rs.next()){
				document = new Document();
				document.setId(rs.getString("pk_doc"));
				document.setStudent(rs.getString("fk_student"));
				document.setAddress(rs.getString("address"));
				document.setName(rs.getString("name"));
				document.setOpTime(rs.getTimestamp("optime"));
			}
			
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return document;
		
	}
	
	
	
	/*
	 * 插入实体文档信息
	 */
	public void add(Document document){
		try{
			//JdbcUtils jdbcUtils = new JdbcUtils();
			con = JdbcUtils.getConnection();
			String strSQL =  "insert into t_doc(pk_doc,fk_student,address,name) VALUE('"
					+document.getId()+"',"
					+"'"+document.getStudent()+"',"
					+"'"+document.getAddress()+"',"
					+"'"+document.getName()+"')";
			sql = con.createStatement();
			sql.executeUpdate(strSQL);
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * 删除实体文档信息
	 */
	public void delete(String id){
		try{
			//JdbcUtils jdbcUtils = new JdbcUtils();
			con = JdbcUtils.getConnection();
			String strSQL =  "DELETE FROM t_doc WHERE pk_doc='"+id+"'";
			sql = con.createStatement();
			sql.executeUpdate(strSQL);
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	 /*
     * 释放链接
     */
    public static void release(){
    	JdbcUtils.releaseConnection(con);
    }

}
