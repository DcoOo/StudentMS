package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alpha.studentms.util.JdbcUtils;

public class DocModelRelDAO {
	
	private static Connection connection = JdbcUtils.getConnection();
	
	/**
	 * 添加一条实体文档与模板文档的关系
	 */
	public void addRel(String id,String docId,String modelId){
		String sql = "insert into t_doc_model_doc_rel(pk_rel,fk_doc,fk_model_doc) VALUE(?,?,?)";
		PreparedStatement preStatement;
		try{
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, id);
			preStatement.setString(2, docId);
			preStatement.setString(3, modelId);
			preStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据模板文档查询实体文档
	 */
	public List<String> findDocByModel(String model){
		List<String> result = new ArrayList<String>();
		String sql = "SELECT fk_doc FROM t_doc_model_doc_rel WHERE fk_model_doc=?";
		PreparedStatement preStatement;
		
		try{
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, model);
			ResultSet rs= preStatement.executeQuery();
			
			while(rs.next()){
				result.add(rs.getString("fk_doc"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 根据实体文档查询模板文档
	 */
	public String findModelByDoc(String doc){
		//List<String> result = new ArrayList<String>();
		String result = "";
		String sql = "SELECT fk_model_doc FROM t_doc_model_doc_rel WHERE fk_doc=?";
		PreparedStatement preStatement;
		
		try{
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, doc);
			ResultSet rs= preStatement.executeQuery();
			
			if(rs.next()){
				result = rs.getString("fk_doc");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 根据模板文档删除记录
	 */
	public void deleteByModel(String model){
		String sql = "DELETE FROM t_doc_model_doc_rel WHERE fk_model_doc=?";
		PreparedStatement preStatement;
		try{
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, model);
			preStatement.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据实体文档删除记录
	 */
	public void deleteByDoc(String doc){
		String sql = "DELETE FROM t_doc_model_doc_rel WHERE fk_doc=?";
		PreparedStatement preStatement;
		try{
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, doc);
			preStatement.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
