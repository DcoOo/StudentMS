package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alpha.studentms.bean.Document;
import alpha.studentms.util.JdbcUtils;

public class DocumentDAO {
	
	private static Connection connection = JdbcUtils.getConnection();
	
	
	/*
	 * 根据文档ID查询
	 */
	public Document findByID(String id){
		
		Document document = null;
		String sql = "SELECT * FROM t_doc WHERE pk_doc=?";
		PreparedStatement preStatement;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			//con = JdbcUtils.getConnection();
			//sql = con.createStatement();
			//rs = sql.executeQuery("SELECT * FROM t_doc WHERE pk_doc='"+id+"'");
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, id);
			ResultSet rs= preStatement.executeQuery();
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
		String sql = "SELECT * FROM t_doc WHERE fk_student=?";
		PreparedStatement preStatement;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			//con = JdbcUtils.getConnection();
			//sql = con.createStatement();
			//rs = sql.executeQuery("SELECT * FROM t_doc WHERE fk_student='"+student+"'");
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, student);
			ResultSet rs= preStatement.executeQuery();
			
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
	 * 根据文档名称查询
	 */
	public Document findByName(String name){
		
		Document document = null;
		String sql = "SELECT * FROM t_doc WHERE name=?";
		PreparedStatement preStatement;
		
		try{
			
			//JdbcUtils jdbcUtils = new JdbcUtils();
			//con = JdbcUtils.getConnection();
			//sql = con.createStatement();
			//rs = sql.executeQuery("SELECT * FROM t_doc WHERE name='"+name+"'");
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, name);
			ResultSet rs= preStatement.executeQuery();
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
		String sql = "insert into t_doc(pk_doc,fk_student,address,name) VALUE(?,?,?,?)";
		PreparedStatement preStatement;
		try{
			//JdbcUtils jdbcUtils = new JdbcUtils();
			//con = JdbcUtils.getConnection();
			//String strSQL =  "insert into t_doc(pk_doc,fk_student,address,name) VALUE('"
			//		+document.getId()+"',"
			//		+"'"+document.getStudent()+"',"
			//		+"'"+document.getAddress()+"',"
			//		+"'"+document.getName()+"')";
			//sql = con.createStatement();
			//sql.executeUpdate(strSQL);
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, document.getId());
			preStatement.setString(2, document.getStudent());
			preStatement.setString(3, document.getAddress());
			preStatement.setString(4, document.getName());
			preStatement.executeUpdate();
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * 删除实体文档信息
	 */
	public void delete(String id){
		String sql = "DELETE FROM t_doc WHERE pk_doc=?";
		PreparedStatement preStatement;
		try{
			//JdbcUtils jdbcUtils = new JdbcUtils();
			//con = JdbcUtils.getConnection();
			//String strSQL =  "DELETE FROM t_doc WHERE pk_doc='"+id+"'";
			//sql = con.createStatement();
			//sql.executeUpdate(strSQL);
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, id);
			preStatement.executeUpdate();
			//con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public List<Document> getDocumentsByModelDocId(String id) {
		List<Document> documents = new ArrayList<>();
		String sql = "select * from t_doc,t_doc_model_doc_rel where pk_doc = fk_doc and fk_model_doc = ?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				Document document = new Document();
				document.setId(set.getString(1));
				document.setStudent(set.getString(2));
				document.setAddress(set.getString(3));
				document.setName(set.getString(4));
				document.setOpTime(set.getTimestamp(5));
				documents.add(document);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documents;
	}
	
	
	 /*
     * 释放链接
     */
    public static void release(){
    	JdbcUtils.releaseConnection(connection);
    }

}
