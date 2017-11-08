package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alpha.studentms.util.JdbcUtils;

public class LoginDAO {

	/**
	 * insert
	 */
	private static String INSERT = "INSERT INTO t_login (username, passwd) VALUES (?, ?)";
	
	/**
	 * update
	 */
	private static String UPDATE = "UPDATE t_login SET passwd = ? WHERE username = ?";
	
	/**
	 * select by username
	 */
	private static String SELECT_BY_USERNAME = "SELECT passwd FROM t_login WHERE username = ?";
	
	/**
	 * preparedstatament
	 */
	private PreparedStatement preState_insert;
	private PreparedStatement preState_update;
	private PreparedStatement preState_select_by_id;
	
	/**
	 * connection
	 */
	private Connection connection;
	
	/**
	 * constructor
	 */
	public LoginDAO(){
		connection = JdbcUtils.getConnection();
		// initialize preparedstatement
		try {
			preState_insert = connection.prepareStatement(INSERT);
			preState_update = connection.prepareStatement(UPDATE);
			preState_select_by_id = connection.prepareStatement(SELECT_BY_USERNAME);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * insert
	 * @param username
	 * @param passwd
	 */
	public void insert(String username, String passwd){
		try {
			preState_insert.setString(1, username);
			preState_insert.setString(2, passwd);
			preState_insert.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * update
	 * @param username
	 * @param passwd
	 */
	public void update(String username, String passwd){
		try {
			preState_update.setString(1, passwd);
			preState_update.setString(2, username);
			preState_update.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * update
	 * @param username
	 * @return
	 */
	public String select_by_username(String username){
		try {
			preState_select_by_id.setString(1, username);
			ResultSet set = preState_select_by_id.executeQuery();
			if (set.next()) {
				return set.getString("passwd");
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	
	
	
	
	
	
	
}
