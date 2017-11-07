package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import alpha.studentms.bean.Reply;
import alpha.studentms.util.JdbcUtils;

public class ReplyDAO {
	/**
	 * select reply by id controlled by CODE 
	 */
	public static int PRIMARY_ID_CODE = 0;
	public static int USER_ID_CODE = 1;
	public static int POST_ID_CODE = 2;

	/**
	 * select by id
	 */
	private static String SELECT_BY_ID = "SELECT * FROM t_reply WHERE %s = ?";
	
	/**
	 * preparedstatement
	 */
	private PreparedStatement preState_select_by_id;
	
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
//		try {
//			preState_select_by_id = connection.prepareStatement(SELECT_BY_ID);
//		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
				case 0:
					SELECT_BY_ID = String.format(SELECT_BY_ID, "pk_id");
					break;
				case 1:
					SELECT_BY_ID = String.format(SELECT_BY_ID, "fk_user");
					break;
				case 2:
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
