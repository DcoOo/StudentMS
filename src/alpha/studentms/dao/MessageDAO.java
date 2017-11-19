package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alpha.studentms.bean.Message;
import alpha.studentms.util.JdbcUtils;

/**
 * 
 * @author joker 通知的数据访问操作
 * @see JdbcUtils
 * @see Message
 */
public class MessageDAO {
	/**
	 * 获得数据库的连接
	 */
	private static Connection connection = JdbcUtils.getConnection();
	
	/**
	 * 查询所有通知的方法
	 */
	public List<Message> getAllMessage(){
		
		List<Message> result = new ArrayList<Message>();
		
		String sql = "select * from t_message";
		PreparedStatement selectAlltatement;
		try{
			selectAlltatement = connection.prepareStatement(sql);
			ResultSet rs= selectAlltatement.executeQuery();
			while(rs.next()){
				Message message = new Message();
				
				message.setId(rs.getString("pk_id"));
				message.setTeacher(rs.getString("fk_teacher"));
				message.setTitle(rs.getString("title"));
				message.setContent(rs.getString("content"));
				message.setOptime(rs.getTimestamp("optime"));
				
				result.add(message);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
	/**
	 * 查询所有通知的方法该老师发的通知
	 */
	public List<Message> getMessage(String teacher){
        List<Message> result = new ArrayList<Message>();
		
		String sql = "select * from t_message where fk_teacher = ?";
		PreparedStatement selectAlltatement;
		try{
			selectAlltatement = connection.prepareStatement(sql);
			selectAlltatement.setString(1, teacher);
			ResultSet rs= selectAlltatement.executeQuery();
			while(rs.next()){
				Message message = new Message();
				
				message.setId(rs.getString("pk_id"));
				message.setTeacher(rs.getString("fk_teacher"));
				message.setTitle(rs.getString("title"));
				message.setContent(rs.getString("content"));
				message.setOptime(rs.getTimestamp("optime"));
				
				result.add(message);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	

	/**
	 * 添加一个通知
	 * 
	 * @param message
	 *            通知
	 */
	public void insertMessage(Message message) {
		String sql = "insert into t_message values(?,?,?,?)";
		PreparedStatement insertStatement;
		try {
			insertStatement = connection.prepareStatement(sql);
			insertStatement.setString(1, message.getId());
			insertStatement.setString(2, message.getTeacher());
			insertStatement.setString(3, message.getTitle());
			insertStatement.setString(4, message.getContent());
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过通知的id删除通知
	 * 
	 * @param id
	 *            通知的id
	 */
	public void deleteMessageById(String id) {
		String sql = "delete from t_message where pk_id = ?";
		PreparedStatement deleteStatement;
		try {
			deleteStatement = connection.prepareStatement(sql);
			deleteStatement.setString(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 释放数据库的连接
	 */
	public void releaseConnection() {
		JdbcUtils.releaseConnection(connection);
	}
	
}
