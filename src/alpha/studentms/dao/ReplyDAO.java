package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import alpha.studentms.bean.Reply;
import alpha.studentms.util.JdbcUtils;
import alpha.studentms.util.UUIDGenerater;

public class ReplyDAO {
	/**
	 * select reply by id controlled by CODE 
	 */
	public static final int PRIMARY_ID_CODE = 0;
	public static final int USER_ID_CODE = 1;
	public static final int POST_ID_CODE = 2;

	/**
	 * select by id sorted by star number
	 */
	private static String SELECT_BY_ID = "SELECT * FROM t_reply WHERE %s = ? ORDER BY star_num DESC";
	
	/**
	 * select all the reply sorted by star number desc
	 */
	private static String SELECT_ALL = "SELECT * FROM t_reply ORDER BY star_num DESC";
	
	/**
	 * delete reply by id controlled by CODE
	 */
	private static String DELETE_BY_ID = "DELETE FROM t_reply WHERE %s = ?";
	
	/**
	 * delete all the reply
	 */
	private static String DELETE_ALL = "DELETE FROM t_reply";
	/**
	 * insert reply with default star_num and oppose_num 0
	 */
	private static String INSERT = "INSERT INTO t_reply "
			+ "(pk_id, fk_user, fk_post, star_num, oppose_num, content) "
			+ "VALUES (?, ?, ?, ?, ?, ?) ";
	
	
	
	
	/**
	 * preparedstatement
	 */
	private PreparedStatement preState_select_by_id;
	private PreparedStatement preState_select_all;
	private PreparedStatement preState_delete_by_id;
	private PreparedStatement preState_delete_all;
	private PreparedStatement preState_insert;
	
	
	/**
	 * connection
	 */
	private Connection connection;
	
	/**
	 * constructor of ReplyDAO
	 */
	public ReplyDAO(){
		// get db connection
		try {
			connection = JdbcUtils.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// initialize preparedstatement
		try {
			preState_select_all = connection.prepareStatement(SELECT_ALL);
			preState_delete_all = connection.prepareStatement(DELETE_ALL);
			preState_insert = connection.prepareStatement(INSERT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * select reply by id pk_id, user_id, post_id controlled by code
	 * code 0 -> pk_id
	 * code 1 -> user_id
	 * code 2 -> post_id
	 * @param id
	 * @param code
	 * @return
	 */
	public LinkedList<Reply> select_by_id(String id, int code){
			try {
				LinkedList<Reply> list = new LinkedList<>();
				// format SQL with id types
				switch (code) {
				case ReplyDAO.PRIMARY_ID_CODE:
					SELECT_BY_ID = String.format(SELECT_BY_ID, "pk_id");
					break;
				case ReplyDAO.USER_ID_CODE:
					SELECT_BY_ID = String.format(SELECT_BY_ID, "fk_user");
					break;
				case ReplyDAO.POST_ID_CODE:
					SELECT_BY_ID = String.format(SELECT_BY_ID, "fk_post");
					break;
				}
				// initialize selece by id preparedstatement
				preState_select_by_id = connection.prepareStatement(SELECT_BY_ID);
				preState_select_by_id.setString(1, id);
				ResultSet set = preState_select_by_id.executeQuery();
				while (set.next()) {
					Reply reply = resultset2reply(set);
					list.add(reply);
				}
				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	/**
	 * select all the reply
	 * @return
	 */
	public LinkedList<Reply> select_all(){
		try {
			LinkedList<Reply> list = new LinkedList<>();
			ResultSet set = preState_select_all.executeQuery();
			while (set.next()) {
				list.add(resultset2reply(set));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * delete reply by id pk_id, user_id, post_id controlled by code
	 * code 0 -> pk_id
	 * code 1 -> user_id
	 * code 2 -> post_id
	 * @param id
	 * @param code
	 */
	public void delete_by_id(String id ,int code){
		try {
			switch (code) {
			case ReplyDAO.PRIMARY_ID_CODE:
				DELETE_BY_ID = String.format(DELETE_BY_ID, "pk_id");
				break;
			case ReplyDAO.USER_ID_CODE:
				DELETE_BY_ID = String.format(DELETE_BY_ID, "fk_user");
				break;
			case ReplyDAO.POST_ID_CODE:
				DELETE_BY_ID = String.format(DELETE_BY_ID, "fk_post");
				break;
			}
			preState_delete_by_id = connection.prepareStatement(DELETE_BY_ID);
			preState_delete_by_id.setString(1, id);
			preState_delete_by_id.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * delete all the reply
	 */
	public void delete_all(){
		try {
			preState_delete_all.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * insert a new reply
	 * @param reply
	 */
	public void insert(Reply reply){
		if (reply.getId() == null || reply.getId().equals("")) {
			reply.setId(UUIDGenerater.getUUID());
		}
		try {
			preState_insert.setString(1, reply.getId());
			preState_insert.setString(2, reply.getUser_id());
			preState_insert.setString(3, reply.getPost_id());
			preState_insert.setInt(4,reply.getStar_num());
			preState_insert.setInt(5,reply.getOppose_num());
			preState_insert.setString(6, reply.getContent());
			preState_insert.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * convert from resultset to reply bean
	 * @param set
	 * @return
	 */
	public Reply resultset2reply(ResultSet set){
		try {
			String id = set.getString("pk_id");
			String user_id = set.getString("fk_user");
			String post_id = set.getString("fk_post");
			int star_num = set.getInt("star_num");
			int oppose_num = set.getInt("oppose_num");
			String content = set.getString("content");
			Reply reply = new Reply(id, user_id, post_id, star_num, oppose_num, content);
			return reply;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
