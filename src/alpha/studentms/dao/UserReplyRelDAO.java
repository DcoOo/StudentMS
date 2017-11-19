package alpha.studentms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import alpha.studentms.util.JdbcUtils;
import alpha.studentms.util.UUIDGenerater;

public class UserReplyRelDAO {
	
	/**
	 * 用户的评论的关系表，保存用于对该评论点过赞或者反对
	 */
	
	private static final String SELECT_STAR_BY_ID = "SELECT * FROM t_user_reply_rel WHERE fk_user = ? AND star = 1";
	private static final String SELECT_OPPOSE_BY_ID = "SELECT * FROM t_user_reply_rel WHERE fk_user = ? AND oppose = 1";
	private static final String INPUT_STAR = "INSERT INTO t_user_reply_rel (pk_id, fk_user, fk_reply, star) VALUES (?, ?, ?, 1)";
	private static final String INPUT_OPPOSE = "INSERT INTO t_user_reply_rel (pk_id, fk_user, fk_reply, oppose) VALUES (?, ?, ?, 1)";
	private static final String HAS_STARED = "SELECT * FROM t_user_reply_rel WHERE fk_user = ? AND fk_reply = ? AND star = 1";
	private static final String HAS_OPPOSED = "SELECT * FROM t_user_reply_rel WHERE fk_user = ? AND fk_reply = ? AND oppose = 1";

	private PreparedStatement preState_select_star_by_id;
	private PreparedStatement preState_select_oppose_by_id;
	private PreparedStatement preState_input_star;
	private PreparedStatement preState_input_oppose;
	private PreparedStatement preState_has_stared;
	private PreparedStatement preState_has_opposed;
	private Connection connection;

	public UserReplyRelDAO() {
		// TODO Auto-generated constructor stub
		connection = JdbcUtils.getConnection();
		try {
			preState_select_star_by_id = connection.prepareStatement(SELECT_STAR_BY_ID);
			preState_select_oppose_by_id = connection.prepareStatement(SELECT_OPPOSE_BY_ID);
			preState_input_star = connection.prepareStatement(INPUT_STAR);
			preState_input_oppose = connection.prepareStatement(INPUT_OPPOSE);
			preState_has_stared = connection.prepareStatement(HAS_STARED);
			preState_has_opposed = connection.prepareStatement(HAS_OPPOSED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 按照用户id，找到所有点过赞的回复
	 */
	public List<String> getStarReplyIdByUserId(String userId){ 
		try {
			List<String> list = new LinkedList<>();
			preState_select_star_by_id.setString(1, userId);
			ResultSet set = preState_select_star_by_id.executeQuery();
			while (set.next()) {
				list.add(set.getString("fk_reply"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 按照用户id，找到所有反对的回复
	 */
	public List<String> getOpposeReplyIdByUserId(String userId){ 
		try {
			List<String> list = new LinkedList<>();
			preState_select_oppose_by_id.setString(1, userId);
			ResultSet set = preState_select_oppose_by_id.executeQuery();
			while (set.next()) {
				list.add(set.getString("fk_reply"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 添加赞同
	 */
	public void addStar(String replyId, String userId){
		try {
			preState_input_star.setString(1, UUIDGenerater.getUUID());
			preState_input_star.setString(2, userId);
			preState_input_star.setString(3, replyId);
			preState_input_star.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 添加反对
	 */
	public void addOppose(String replyId, String userId){
		try {
			preState_input_oppose.setString(1, UUIDGenerater.getUUID());
			preState_input_oppose.setString(2, userId);
			preState_input_oppose.setString(3, replyId);
			preState_input_oppose.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 是否对该条回复点赞
	 * @param userId
	 * @param replyId
	 * @return
	 */
	public boolean  hasStared(String userId, String replyId) {
		try {
			preState_has_stared.setString(1, userId);
			preState_has_stared.setString(2, replyId);
			ResultSet set = preState_has_stared.executeQuery();
			return set.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 用户是否对该条回复点过反对
	 * @param userId
	 * @param replyId
	 * @return
	 */
	public boolean hasOpposed(String userId, String replyId){
		try {
			preState_has_opposed.setString(1, userId);
			preState_has_opposed.setString(2, replyId);
			ResultSet set = preState_has_opposed.executeQuery();
			return set.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

}
