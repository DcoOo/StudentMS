package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


import alpha.studentms.bean.Post;
import alpha.studentms.util.JdbcUtils;
import alpha.studentms.util.UUIDGenerater;

public class PostDAO {

	/**
	 * select by id
	 */
	private static String SELECT_BY_ID = "SELECT * FROM t_post WHERE pk_id = ?"; 
	
	/**
	 * select by user_id
	 */
	private static String SELECT_BY_USER_ID = "SELECT * FROM t_post WHERE fk_user = ?";
	
	/**
	 * select all post
	 */
	private static String SELECT_ALL = "SELECT * FROM t_post";
	
	/**
	 * update by id
	 */
	private static String UPDATE_BY_ID = "UPDATE t_post SET title = ?, content = ?, "
			+ "reply_num = ? where pk_id = ?";
	
	/**
	 * delete by id
	 */
	private static String DELETE_BY_ID = "DELETE FROM t_post WHERE pk_id = ? ";
	
	/**
	 * delete all
	 */
	private static String DELETE_ALL = "DELETE FROM t_post";
	
	/**
	 * insert
	 */
	private static String INSERT = "INSERT INTO t_post "
			+ "(pk_id, fk_user, title, content, reply_num) "
			+ "VALUES (?, ?, ?, ?, ?) ";
	
	/**
	 * connection
	 */
	private Connection connection;
	
	/**
	 * preparedstatement
	 */
	private PreparedStatement preState_select_by_id;
	private PreparedStatement preState_delete_by_id;
	private PreparedStatement preState_update_by_id;
	private PreparedStatement preState_insert;
	private PreparedStatement preState_select_all;
	private PreparedStatement preState_delete_all;
	private PreparedStatement preState_select_by_userid;

	/**
	 * 初始化DAO
	 */
	public PostDAO() {
		// get db connection
		try {
			connection = JdbcUtils.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// initialize preparedstatement
		try {
			preState_select_by_id = connection.prepareStatement(SELECT_BY_ID);
			preState_insert = connection.prepareStatement(INSERT);
			preState_update_by_id = connection.prepareStatement(UPDATE_BY_ID);
			preState_delete_by_id = connection.prepareStatement(DELETE_BY_ID);
			preState_select_all = connection.prepareStatement(SELECT_ALL);
			preState_delete_all = connection.prepareStatement(DELETE_ALL);
			preState_select_by_userid = connection.prepareStatement(SELECT_BY_USER_ID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * select post by id
	 * @param id
	 * @return if cannot find return null 
	 */
	public Post select_by_id(String id){
		try {
			preState_select_by_id.setString(1, id);
			ResultSet set = preState_select_by_id.executeQuery();
			if(set.next()){
				return resultset2post(set);
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * select all posts
	 * @return
	 */
	public LinkedList<Post> select_all(){
		LinkedList<Post> list_posts = new LinkedList<>();
		try {
			ResultSet set = preState_select_all.executeQuery();
			while (set.next()) {
				list_posts.add(resultset2post(set));
			}
			return list_posts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * delete by id
	 * @param id
	 */
	public boolean delete_by_id(String id){
		try {
			preState_delete_by_id.setString(1, id);
			return preState_delete_by_id.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete_all(){
		try {
			return preState_delete_all.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * insert post
	 * @param post
	 */
	public void insert(Post post){
		// if the id of the post id null, allocate one
		if (post.getId() == null || post.getId().equals("")) {
			post.setId(UUIDGenerater.getUUID());
		}
		try {
			preState_insert.setString(1, post.getId());
			preState_insert.setString(2, post.getUser_id());
			preState_insert.setString(3, post.getTitle());
			preState_insert.setString(4, post.getContent());
			preState_insert.setInt(5, post.getReply_num());
			preState_insert.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * update by id 
	 * @param post
	 */
	public int update_by_Id(Post post){
		try {
			preState_update_by_id.setString(1, post.getTitle());
			preState_update_by_id.setString(2, post.getContent());
			preState_update_by_id.setInt(3, post.getReply_num());
			preState_update_by_id.setString(4, post.getId());
			return preState_update_by_id.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// when error occur
		return -1;
	}
	
	/**
	 * select posts by user id
	 * @param userid
	 * @return
	 */
	public LinkedList<Post> select_by_userid(String userid){
		try {
			preState_select_by_userid.setString(1, userid);
			ResultSet set = preState_select_by_userid.executeQuery();
			LinkedList<Post> list = new LinkedList<>();
			while (set.next()) {
				list.add(resultset2post(set));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * convert from select resultset to post bean
	 * @param set
	 * @return
	 */
	private Post resultset2post(ResultSet set){
		try {
			String id = set.getString("pk_id");
			String user_id = set.getString("fk_user");
			String title = set.getString("title");
			String content = set.getString("content");
			int reply_num = set.getInt("reply_num");
			Post post = new Post(id, user_id, title, content, reply_num);
			return post;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * release connection
	 */
	public void release(){
		JdbcUtils.releaseConnection(connection);
	}
}
